import requests
import speech_recognition as sr
import webbrowser
import sys
import pyttsx3

# Inicializamos pyttsx3 (sintetizador de voz)
engine = pyttsx3.init()
engine.setProperty('rate', 150)  # Velocidad de habla
engine.setProperty('volume', 1)   # Volumen (de 0.0 a 1.0)

# Configuración del reconocimiento de voz
r = sr.Recognizer()

# Configuración de la API
API_KEY = 'tu_clave_api'  # Reemplaza con tu clave API
CITY = 'Madrid'

# Función para que el programa hable
def hablar(texto):
    print(f"Program: {texto}")  # Imprime en consola
    engine.say(texto)           # Sintetiza la voz
    engine.runAndWait()        # Espera a que termine de hablar

# Función para obtener el clima
def obtener_clima():
    url = f"https://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=%3Cspan%20class=d432372e18e29b92defdc42811d49ed4"
    response = requests.get(url)
    data = response.json()
    print("Respuesta de la API:", data)  # Para depuración

    if response.status_code == 200:
        # Extraemos la información del pronóstico para mañana
        mañana = data['list'][1]  # Obtenemos el pronóstico para el segundo día
        temperatura = mañana['main']['temp']  # Temperatura en Celsius
        descripcion = mañana['weather'][0]['description']  # Descripción del clima

        return f"El clima para mañana en {CITY} será de {temperatura:.1f} grados Celsius con {descripcion}."
    else:
        return f"Error: {data.get('message', 'No se pudo obtener el pronóstico del clima.')}"

# Función para escuchar continuamente
def escuchar_continuamente():
    with sr.Microphone() as source:
        print("Escuchando...")
        r.adjust_for_ambient_noise(source)  # Ajusta el ruido ambiental
        
        while True:
            try:
                audio = r.listen(source)  # Escucha constantemente
                text = r.recognize_google(audio, language="es-ES")  # Reconocimiento de voz
                print(f"Reconocido: {text}")  # Muestra el texto reconocido

                # Comandos para cerrar el programa
                if "salir" in text.lower():
                    hablar("Cerrando la aplicación...")
                    print("Cerrando la aplicación...")
                    sys.exit()  # Cierra el programa
                
                # Comando para abrir Amazon
                elif "amazon" in text.lower():
                    hablar("Abriendo Amazon...")
                    print("Abriendo Amazon...")
                    webbrowser.open("https://www.amazon.com")
                
                # Comando para abrir Google
                elif "google" in text.lower():
                    hablar("Abriendo Google...")
                    print("Abriendo Google...")
                    webbrowser.open("https://www.google.com")
                
                # Comando para obtener el clima
                elif "tiempo" in text.lower():
                    clima = obtener_clima()
                    hablar(clima)  # Habla el pronóstico del clima

            except sr.UnknownValueError:
                # Omitimos la excepción si no se puede entender el audio
                continue  # Vuelve a escuchar sin hacer nada
            
            except sr.RequestError:
                hablar("Hubo un problema con el servicio de reconocimiento de voz.")
                break  # Rompe el bucle si hay un problema con el servicio

# Inicia la escucha continua
escuchar_continuamente()
