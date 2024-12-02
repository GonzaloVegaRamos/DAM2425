import spotipy
from spotipy.oauth2 import SpotifyOAuth
import speech_recognition as sr
import sys
import time
import winsound

CLIENT_ID = '0293f317349a49ebbc79c744c5fcffc8'
CLIENT_SECRET = '85aaafa857344ef587884ab983b42dce'
REDIRECT_URI = 'http://localhost:8888/callback'

scope = "user-read-playback-state user-modify-playback-state user-library-modify"
spotify = spotipy.Spotify(auth_manager=SpotifyOAuth(client_id=CLIENT_ID,
                                                    client_secret=CLIENT_SECRET,
                                                    redirect_uri=REDIRECT_URI,
                                                    scope=scope))

r = sr.Recognizer()

def obtener_cancion_actual():
    playback = spotify.current_playback()
    if playback and playback.get('item'):
        song_name = playback['item']['name']
        artist_name = playback['item']['artists'][0]['name']
        return f"Reproduciendo ahora: {song_name} de {artist_name}"
    return "No se está reproduciendo ninguna canción."

def reproducir_pausa():
    playback = spotify.current_playback()
    if playback and playback.get('is_playing'):
        spotify.pause_playback()
        print("Música pausada.")
    else:
        spotify.start_playback()
        print("Reproduciendo música.")
        print(obtener_cancion_actual())
    time.sleep(1)  
    

def siguiente_cancion():
    spotify.next_track()
    print("Saltando a la siguiente canción.")
    time.sleep(1)  
    print(obtener_cancion_actual())

def cancion_anterior():
    spotify.previous_track()
    print("Volviendo a la canción anterior.")
    time.sleep(1)  
    print(obtener_cancion_actual())

def agregar_a_favoritos():
    playback = spotify.current_playback()
    if playback and playback.get('item'):
        track_id = playback['item']['id']  
        spotify.current_user_saved_tracks_add([track_id]) 
        print(f"La canción '{playback['item']['name']}' ha sido añadida a tus favoritos.")
        winsound.Beep(1000, 500)  
    else:
        print("No se está reproduciendo ninguna canción para añadir a favoritos.")

def reproducir_artista(artista):
    results = spotify.search(q=f'artist:{artista}', type='artist')
    if results['artists']['items']:
        artist_id = results['artists']['items'][0]['id']
        albums = spotify.artist_albums(artist_id, album_type='album')
        if albums['items']:
            spotify.start_playback(context_uri=albums['items'][0]['uri'])
            print(f"Reproduciendo canciones de {artista}.")
            time.sleep(1) 
            print(obtener_cancion_actual())
        else:
            print(f"No se encontraron álbumes de {artista}.")
    else:
        print(f"No se encontró al artista {artista}.")

def escuchar_comandos():
    with sr.Microphone() as source:
        print("Escuchando comandos...")
        while True:
            try: 
                audio = r.listen(source)  
                text = r.recognize_google(audio, language="es-ES")  
                print(f"Reconocido: {text}")
                if "salir" in text.lower():
                    print("Cerrando la aplicación...")
                    sys.exit()
                elif "pausa" in text.lower() or "reproducir" in text.lower():
                    reproducir_pausa()
                elif "siguiente" in text.lower():
                    siguiente_cancion()
                elif "anterior" in text.lower():
                    cancion_anterior()
                elif "me gusta" in text.lower():
                    agregar_a_favoritos()  
                elif "play" in text.lower():
                    artista = text.lower().replace("play", "").replace("reproduce", "").strip()
                    if artista:
                        reproducir_artista(artista)
                    else:
                        print("No se detectó un artista o canción después de 'play'.")
            except sr.UnknownValueError:
                continue
            except sr.RequestError:
                print("Hubo un problema con el servicio de reconocimiento de voz.")
                break



escuchar_comandos()
