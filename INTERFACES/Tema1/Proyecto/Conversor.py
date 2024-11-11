import cv2
import pyautogui
import mediapipe as mp
import time

# Configuración de captura de video y Mediapipe
cap = cv2.VideoCapture(0)
mp_hands = mp.solutions.hands
hands = mp_hands.Hands(static_image_mode=False, max_num_hands=1,
                       min_detection_confidence=0.7, min_tracking_confidence=0.7)
mp_drawing = mp.solutions.drawing_utils

# Variables de temporización
last_action_time = time.time()
gesture_delay = 1  # Tiempo en segundos para evitar activaciones múltiples

def is_finger_extended(tip_y, knuckle_y, threshold=0.02):
    """Determina si un dedo está extendido usando un margen de umbral."""
    return tip_y < (knuckle_y - threshold)

while True:
    ret, frame = cap.read()
    if not ret:
        break

    image_rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)
    results = hands.process(image_rgb)

    if results.multi_hand_landmarks:
        for hand_landmarks in results.multi_hand_landmarks:
            mp_drawing.draw_landmarks(frame, hand_landmarks, mp_hands.HAND_CONNECTIONS)

            # Coordenadas y puntos de referencia de los dedos
            thumb_tip = hand_landmarks.landmark[mp_hands.HandLandmark.THUMB_TIP].y
            index_tip = hand_landmarks.landmark[mp_hands.HandLandmark.INDEX_FINGER_TIP].y
            middle_tip = hand_landmarks.landmark[mp_hands.HandLandmark.MIDDLE_FINGER_TIP].y
            ring_tip = hand_landmarks.landmark[mp_hands.HandLandmark.RING_FINGER_TIP].y
            pinky_tip = hand_landmarks.landmark[mp_hands.HandLandmark.PINKY_TIP].y

            index_knuckle = hand_landmarks.landmark[mp_hands.HandLandmark.INDEX_FINGER_MCP].y
            middle_knuckle = hand_landmarks.landmark[mp_hands.HandLandmark.MIDDLE_FINGER_MCP].y
            ring_knuckle = hand_landmarks.landmark[mp_hands.HandLandmark.RING_FINGER_MCP].y
            pinky_knuckle = hand_landmarks.landmark[mp_hands.HandLandmark.PINKY_MCP].y

            # Detectar si los dedos están extendidos con un margen de umbral
            index_extended = is_finger_extended(index_tip, index_knuckle)
            middle_extended = is_finger_extended(middle_tip, middle_knuckle)
            ring_extended = is_finger_extended(ring_tip, ring_knuckle)
            pinky_extended = is_finger_extended(pinky_tip, pinky_knuckle)

            # Obtener el tiempo actual para el control de temporización
            current_time = time.time()

            # Detectar Puño Cerrado (todos los dedos doblados)
            if not index_extended and not middle_extended and not ring_extended and not pinky_extended:
                if current_time - last_action_time > gesture_delay:
                    print("Puño cerrado: cerrar aplicación")
                    cap.release()
                    cv2.destroyAllWindows()
                    break  # Salir del bucle principal para terminar la aplicación

            # Minimizar ventana activa (solo índice extendido, otros dedos doblados)
            elif index_extended and not middle_extended and not ring_extended and not pinky_extended:
                if current_time - last_action_time > gesture_delay:
                    print("Minimizar ventana activa")
                    pyautogui.hotkey('win', 'down')  # Minimiza la ventana activa en Windows
                    last_action_time = current_time

            # Cambiar de pestaña/Tabular (índice y medio extendidos, otros dedos doblados)
            elif index_extended and middle_extended and not ring_extended and not pinky_extended:
                if current_time - last_action_time > gesture_delay:
                    print("Cambiar de pestaña/Tabular")
                    pyautogui.hotkey('ctrl', 'tab')  # Cambia de pestaña en navegadores
                    last_action_time = current_time

            # Control de volumen con el pulgar arriba/abajo
            elif thumb_tip < index_knuckle:
                if current_time - last_action_time > gesture_delay:
                    pyautogui.press('volumeup')
                    last_action_time = current_time
            elif thumb_tip > index_knuckle:
                if current_time - last_action_time > gesture_delay:
                    pyautogui.press('volumedown')
                    last_action_time = current_time

    cv2.imshow('Hand Gesture', frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
