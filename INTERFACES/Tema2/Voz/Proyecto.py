import spotipy
from spotipy.oauth2 import SpotifyOAuth
import speech_recognition as sr
import sys
import time
import winsound  # Para reproducir el sonido de confirmación en Windows

# Credenciales de Spotify
CLIENT_ID = '0293f317349a49ebbc79c744c5fcffc8'  # Reemplaza con tu client_id
CLIENT_SECRET = '85aaafa857344ef587884ab983b42dce'  # Reemplaza con tu client_secret
REDIRECT_URI = 'http://localhost:8888/callback'  # URI de redirección

# Autenticación de Spotify
scope = "user-read-playback-state user-modify-playback-state user-library-modify"  # Incluimos el permiso para modificar la biblioteca
spotify = spotipy.Spotify(auth_manager=SpotifyOAuth(client_id=CLIENT_ID,
                                                    client_secret=CLIENT_SECRET,
                                                    redirect_uri=REDIRECT_URI,
                                                    scope=scope))

# Configuración del reconocimiento de voz
r = sr.Recognizer()

# Función para obtener el nombre de la canción actual
def obtener_cancion_actual():
    playback = spotify.current_playback()
    if playback and playback.get('item'):  # Comprobamos si 'item' está presente
        song_name = playback['item']['name']  # Accedemos correctamente a la canción
        artist_name = playback['item']['artists'][0]['name']
        return f"Reproduciendo ahora: {song_name} de {artist_name}"
    return "No se está reproduciendo ninguna canción."

# Función para reproducir o pausar la música
def reproducir_pausa():
    """Reproduce o pausa la música actual."""
    playback = spotify.current_playback()
    if playback and playback.get('is_playing'):  # Comprobamos si 'is_playing' está presente
        spotify.pause_playback()
        print("Música pausada.")
    else:
        spotify.start_playback()
        print("Reproduciendo música.")
    
    # Después de la acción, obtenemos la canción actual
    time.sleep(1)  # Esperamos un momento para que Spotify actualice la canción
    print(obtener_cancion_actual())

# Función para saltar a la siguiente canción
def siguiente_cancion():
    """Salta a la siguiente canción."""
    spotify.next_track()
    print("Saltando a la siguiente canción.")
    
    # Después de saltar, obtenemos la canción actual
    time.sleep(1)  # Esperamos un momento para que Spotify actualice la canción
    print(obtener_cancion_actual())

# Función para volver a la canción anterior
def cancion_anterior():
    """Vuelve a la canción anterior."""
    spotify.previous_track()
    print("Volviendo a la canción anterior.")
    
    # Después de retroceder, obtenemos la canción actual
    time.sleep(1)  # Esperamos un momento para que Spotify actualice la canción
    print(obtener_cancion_actual())

# Función para agregar la canción actual a la biblioteca del usuario (favoritos)
def agregar_a_favoritos():
    """Añade la canción actual a los favoritos (biblioteca)."""
    playback = spotify.current_playback()
    if playback and playback.get('item'):
        track_id = playback['item']['id']  # ID de la canción actual
        spotify.current_user_saved_tracks_add([track_id])  # Añadir a favoritos
        print(f"La canción '{playback['item']['name']}' ha sido añadida a tus favoritos.")

        # Reproducir un sonido de confirmación
        winsound.Beep(1000, 500)  # Sonido de confirmación (frecuencia, duración en ms)
    else:
        print("No se está reproduciendo ninguna canción para añadir a favoritos.")

# Función para escuchar comandos de voz
def escuchar_comandos():
    with sr.Microphone() as source:
        print("Escuchando comandos...")

        while True:
            try:
                audio = r.listen(source)  # Escucha constantemente
                text = r.recognize_google(audio, language="es-ES")  # Reconocimiento de voz
                print(f"Reconocido: {text}")

                # Comandos de Spotify
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
                    agregar_a_favoritos()  # Llamamos a la función para agregar a favoritos
                elif "play" in text.lower():
                    artista = text.lower().replace("play", "").replace("reproduce", "").strip()
                    if artista:
                        reproducir_artista(artista)
                    else:
                        print("No se detectó un artista o canción después de 'play'.")

            except sr.UnknownValueError:
                continue  # Si no entiende, vuelve a escuchar
            except sr.RequestError:
                print("Hubo un problema con el servicio de reconocimiento de voz.")
                break

# Función para reproducir canciones de un artista
def reproducir_artista(artista):
    """Busca y reproduce canciones de un artista."""
    results = spotify.search(q=f'artist:{artista}', type='artist')
    if results['artists']['items']:
        artist_id = results['artists']['items'][0]['id']
        albums = spotify.artist_albums(artist_id, album_type='album')
        if albums['items']:
            spotify.start_playback(context_uri=albums['items'][0]['uri'])
            print(f"Reproduciendo canciones de {artista}.")
        else:
            print(f"No se encontraron álbumes de {artista}.")
    else:
        print(f"No se encontró al artista {artista}.")

# Inicia la escucha de comandos
escuchar_comandos()
