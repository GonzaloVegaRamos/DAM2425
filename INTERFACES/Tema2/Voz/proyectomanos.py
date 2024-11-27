import cv2
import mediapipe as mp
import tkinter as tk
from tkinter import Canvas
import threading
from pycaw.pycaw import AudioUtilities, IAudioEndpointVolume

# Configuración de MediaPipe (usando modelo más rápido)
mp_hands = mp.solutions.hands
hands = mp_hands.Hands(model_complexity=0, min_detection_confidence=0.5, min_tracking_confidence=0.5)

# Configuración de la cámara
cap = cv2.VideoCapture(0)
cap.set(cv2.CAP_PROP_FRAME_WIDTH, 640)  # Reduce resolución para mejorar rendimiento
cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 480)
cap.set(cv2.CAP_PROP_FPS, 30)  # Limitar la tasa de FPS de la cámara (ajustar según tu sistema)

# Configuración de la interfaz gráfica con Tkinter
root = tk.Tk()
root.title("Control de Volumen y Manos")

# Tamaño de la ventana
width = 600
height = 400

# Crear canvas para dibujar las bolas y barra de volumen
canvas = Canvas(root, width=width, height=height, bg="white")
canvas.pack()

# Crear las bolas (roja para la mano derecha, azul para la mano izquierda)
ball_radius = 15
ball_red = canvas.create_oval(0, 0, ball_radius*2, ball_radius*2, fill="red")  # Para la mano derecha
ball_blue = canvas.create_oval(0, 0, ball_radius*2, ball_radius*2, fill="blue")  # Para la mano izquierda

# Crear barra de volumen en el lateral izquierdo
volume_bar = canvas.create_rectangle(50, height - 50, 70, 50, fill="lightgray", outline="black")  # Vertical
volume_slider = canvas.create_rectangle(50, height - 50, 70, height - 50 - ball_radius*2, fill="green")

# Zona activa de la barra de volumen (en Y, control de la zona de activación)
volume_zone_left = 50   # Coordenada X de la barra (constante)
volume_zone_top = height - 50  # Coordenada Y superior
volume_zone_bottom = 50  # Coordenada Y inferior

# Función para obtener el volumen actual del sistema
def get_volume_control():
    devices = AudioUtilities.GetSpeakers()
    interface = devices.Activate(
        IAudioEndpointVolume._iid_, 1, None)
    volume = interface.QueryInterface(IAudioEndpointVolume)
    return volume

# Función para actualizar el volumen del sistema
def set_system_volume(level):
    volume = get_volume_control()
    volume.SetMasterVolumeLevelScalar(level, None)

# Función para actualizar la posición de las bolas
def update_ball_position(x, y, ball):
    # Actualiza la posición de la bola en el canvas
    canvas.coords(ball, x - ball_radius, y - ball_radius, x + ball_radius, y + ball_radius)

# Función para actualizar la barra de volumen solo cuando la bola entra en la zona
def update_volume_if_in_zone(x_position, y_position):
    # Comprobar si la bola está dentro de la zona de volumen (en el eje X, la X está fija)
    if volume_zone_left <= x_position <= volume_zone_left + 20:  # 20 es el grosor de la barra
        if volume_zone_top >= y_position >= volume_zone_bottom:
            # Si está dentro de la zona activa, actualizamos el volumen
            volume_level = (volume_zone_top - y_position) / (volume_zone_top - volume_zone_bottom)
            canvas.coords(volume_slider, 50, y_position, 70, height - 50)  # Actualiza la barra de volumen
            set_system_volume(volume_level)  # Cambia el volumen del sistema

# Función para procesar la cámara y obtener la posición de los dedos índices
def process_camera():
    frame_count = 0
    process_interval = 2  # Solo procesar cada 2 frames para reducir la carga

    while cap.isOpened():
        ret, frame = cap.read()
        if not ret:
            break
        
        frame_count += 1
        if frame_count % process_interval == 0:  # Solo procesar cada `process_interval` frames
            # Convierte la imagen a RGB
            frame_rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
            results = hands.process(frame_rgb)

            # Si se detectan manos, actualiza la posición de las bolas
            if results.multi_hand_landmarks:
                for hand_landmarks, handedness in zip(results.multi_hand_landmarks, results.multi_handedness):
                    # Obtener la posición del dedo índice
                    index_finger_x = int(hand_landmarks.landmark[mp_hands.HandLandmark.INDEX_FINGER_TIP].x * width)
                    index_finger_y = int(hand_landmarks.landmark[mp_hands.HandLandmark.INDEX_FINGER_TIP].y * height)

                    # Determinar el color de la bola según la mano (izquierda o derecha)
                    if handedness.classification[0].label == 'Left':
                        update_ball_position(index_finger_x, index_finger_y, ball_blue)  # Mano izquierda, bola azul
                        update_volume_if_in_zone(index_finger_x, index_finger_y)  # Actualizar volumen si está en la zona
                    else:
                        update_ball_position(index_finger_x, index_finger_y, ball_red)  # Mano derecha, bola roja
                        update_volume_if_in_zone(index_finger_x, index_finger_y)  # Actualizar volumen si está en la zona

        # Limitar la tasa de FPS (puedes ajustar este valor)
        cv2.waitKey(1)  # Esto limita la velocidad de los cuadros procesados a una tasa razonable

# Función para ejecutar la cámara en un hilo separado
def run_camera():
    threading.Thread(target=process_camera, daemon=True).start()

# Iniciar el hilo de la cámara
run_camera()

# Ejecuta la interfaz gráfica de Tkinter
root.mainloop()

# Libera la cámara al cerrar la ventana
cap.release()
cv2.destroyAllWindows()
