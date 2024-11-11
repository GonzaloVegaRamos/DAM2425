import cv2
import pyautogui
import mediapipe as mp
import spotipy
from spotipy.oauth2 import SpotifyOAuth

# Configuración de Spotify
sp = spotipy.Spotify(auth_manager=SpotifyOAuth(client_id="c926750a781a4627b9976a7461e270d1z", 
                                               client_secret="ef5ab788961649e886c4d16e30cab83a", 
                                               redirect_uri="google", 
                                               scope="user-library-read user-read-playback-state user-modify-playback-state"))

# Configuración de OpenCV y MediaPipe
cap = cv2.VideoCapture(0)

mp_hands = mp.solutions.hands
hands = mp_hands.Hands(static_image_mode=False, max_num_hands=1,
                       min_detection_confidence=0.5, min_tracking_confidence=0.5)

mp_drawing = mp.solutions.drawing_utils

def is_fist(hand_landmarks):
    """Detecta si la mano está en forma de puño cerrado."""
    thumb_tip = hand_landmarks.landmark[mp_hands.HandLandmark.THUMB_TIP]
    index_tip = hand_landmarks.landmark[mp_hands.HandLandmark.INDEX_FINGER_TIP]
    middle_tip = hand_landmarks.landmark[mp_hands.HandLandmark.MIDDLE_FINGER_TIP]
    ring_tip = hand_landmarks.landmark[mp_hands.HandLandmark.RING_FINGER_TIP]
    pinky_tip = hand_landmarks.landmark[mp_hands.HandLandmark.PINKY_TIP]

    # Comprobar que los dedos están cerca de la palma (para un puño cerrado)
    if (thumb_tip.y < hand_landmarks.landmark[mp_hands.HandLandmark.THUMB_IP].y and
        index_tip.y < hand_landmarks.landmark[mp_hands.HandLandmark.INDEX_FINGER_MCP].y and
        middle_tip.y < hand_landmarks.landmark[mp_hands.HandLandmark.MIDDLE_FINGER_MCP].y and
        ring_tip.y < hand_landmarks.landmark[mp_hands.HandLandmark.RING_FINGER_MCP].y and
        pinky_tip.y < hand_landmarks.landmark[mp_hands.HandLandmark.PINKY_MCP].y):
        return True
    return False

while True:
    ret, frame = cap.read()
    if not ret:
        break

    image_rgb = cv2.cvtColor(frame, cv2.COLOR_BGR2RGB)

    results = hands.process(image_rgb)

    if results.multi_hand_landmarks:
        for hand_landmarks in results.multi_hand_landmarks:
            mp_drawing.draw_landmarks(frame, hand_landmarks, mp_hands.HAND_CONNECTIONS)

            if is_fist(hand_landmarks):
                # Si se detecta un puño cerrado, detener la música en Spotify
                print("Puño cerrado detectado. Pausando música.")
                sp.pause_playback()  # Detener la reproducción de Spotify

    cv2.imshow('Hand Gesture', frame)

    if cv2.waitKey(1) & 0xFF == ord('q'):
        break

cap.release()
cv2.destroyAllWindows()
