import sys

for line in sys.stdin:
    mensaje = line.strip().lower()
    if mensaje == "hola":
        print("¡Hola! ¿Cómo estás?")
    elif mensaje == "adios":
        print("¡Hasta luego!")
        break
    else:
        print("No entiendo tu mensaje.")
    sys.stdout.flush()