import cv2
import mediapipe as mp

# Inicializamos MediaPipe para la detección de manos
mp_hands = mp.solutions.hands
hands = mp_hands.Hands(min_detection_confidence=0.7, min_tracking_confidence=0.5)
mp_drawing = mp.solutions.drawing_utils

# Coordenadas de la barra (horizontal)
bar_start = 100  # Inicio de la barra (en píxeles)
bar_end = 500    # Fin de la barra (en píxeles)
bar_y = 300      # Posición vertical de la barra (en píxeles)
circle_x = (bar_start + bar_end) // 2  # Posición inicial del círculo

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

# Función que dibuja la barra y el círculo sobre la imagen
def draw_bar(frame, circle_position):
    # Dibujar la barra horizontal
    cv2.line(frame, (bar_start, bar_y), (bar_end, bar_y), (255, 255, 255), 4)
    # Dibujar el círculo
    cv2.circle(frame, (circle_position, bar_y), 15, (0, 255, 0), -1)

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

            # Si los dedos están tocando
            if fingers_touched:
                # Obtener la posición del índice
                index_tip = hand_landmarks.landmark[mp_hands.HandLandmark.INDEX_FINGER_TIP]
                index_x = int(index_tip.x * frame.shape[1])

                # Verificar si el índice está dentro de la barra
                if bar_start <= index_x <= bar_end:
                    # Actualizar la posición del círculo
                    circle_x = index_x

    # Dibujar la barra y el círculo
    draw_bar(frame, circle_x)

    # Mostrar la imagen con la barra y el círculo
    cv2.imshow("Control de Barra con Manos", frame)

    # Romper el bucle si se presiona 'q'
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

# Liberar la cámara y cerrar las ventanas
cap.release()
cv2.destroyAllWindows()
