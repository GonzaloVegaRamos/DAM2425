import cv2
import mediapipe as mp
import tkinter as tk

# Inicializamos MediaPipe para la detección de manos
mp_hands = mp.solutions.hands
hands = mp_hands.Hands(min_detection_confidence=0.7, min_tracking_confidence=0.5)
mp_drawing = mp.solutions.drawing_utils

# Coordenadas del área del botón (se puede ajustar)
button_area = (150, 250, 300, 350)  # (x1, y1, x2, y2) en píxeles

# Variable global para verificar si los dedos están tocando
fingers_touched = False

# Función que verifica si el índice y el pulgar están juntos
def check_fingers(hand_landmarks):
    global fingers_touched
    
    # Las coordenadas del pulgar (tip) y el índice (tip)
    thumb_tip = hand_landmarks.landmark[mp_hands.HandLandmark.THUMB_TIP]
    index_tip = hand_landmarks.landmark[mp_hands.HandLandmark.INDEX_FINGER_TIP]
    
    # Calculamos la distancia entre el pulgar y el índice
    thumb_index_distance = ((thumb_tip.x - index_tip.x)**2 + (thumb_tip.y - index_tip.y)**2)**0.5
    
    # Si la distancia es pequeña, significa que están tocando
    fingers_touched = thumb_index_distance < 0.05

# Función que dibuja el botón sobre la imagen de la cámara
def draw_button(frame):
    # Dibujar un rectángulo en el área del botón
    cv2.rectangle(frame, (button_area[0], button_area[1]), (button_area[2], button_area[3]), (0, 255, 0), 2)
    # Dibujar el texto dentro del rectángulo
    cv2.putText(frame, "Presiona cuando el indice y pulgar esten juntos", (button_area[0] + 10, button_area[1] + 40), 
                cv2.FONT_HERSHEY_SIMPLEX, 0.7, (255, 255, 255), 2)

# Función que ejecuta la acción al presionar el "botón"
def button_action():
    if fingers_touched:
        print("¡Botón presionado!")
    else:
        print("El índice y el pulgar deben estar juntos sobre el botón para presionar.")

# Inicializamos la cámara
cap = cv2.VideoCapture(0)

# Bucle principal para la detección de manos y la interfaz de la cámara
while True:
    ret, frame = cap.read()
    if not ret:
        break

    # Convertir la imagen a RGB para MediaPipe
    frame_rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    results = hands.process(frame_rgb)

    if results.multi_hand_landmarks:
        for hand_landmarks in results.multi_hand_landmarks:
            # Verificar si los dedos están tocando
            check_fingers(hand_landmarks)

            # Dibujar los landmarks de la mano en la imagen
            mp_drawing.draw_landmarks(frame, hand_landmarks, mp_hands.HAND_CONNECTIONS)
    
    # Dibujar el "botón" en la ventana de la cámara
    draw_button(frame)

    # Verificar si el índice y pulgar están tocando y sobre el área del botón
    if fingers_touched:
        # Obtener la posición del índice
        index_tip = results.multi_hand_landmarks[0].landmark[mp_hands.HandLandmark.INDEX_FINGER_TIP]
        index_x, index_y = int(index_tip.x * frame.shape[1]), int(index_tip.y * frame.shape[0])
        
        # Comprobar si el índice está dentro del área del botón
        if button_area[0] < index_x < button_area[2] and button_area[1] < index_y < button_area[3]:
            button_action()

    # Mostrar la imagen con el "botón"
    cv2.imshow("Detección de Manos con Botón", frame)

    # Romper el bucle si se presiona 'q'
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# Liberar la cámara y cerrar las ventanas
cap.release()
cv2.destroyAllWindows()
