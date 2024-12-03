from googletrans import Translator

# Crear un objeto traductor
translator = Translator()

# Pedir al usuario que ingrese el texto
texto = input("Introduce el texto que quieres traducir: ")

# Detectar el idioma del texto
detected_lang = translator.detect(texto)

# Traducir el texto al español
traduccion = translator.translate(texto, src=detected_lang.lang, dest='es')

# Mostrar el idioma detectado y la traducción
print(f"Idioma detectado: {detected_lang.lang}")
print(f"Traducción al español: {traduccion.text}")

