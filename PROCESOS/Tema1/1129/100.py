import threading


class Contador:
    def __init__(self):
        self.contador = 0
        self.lock = threading.Lock()  

    def incrementar(self):
        with self.lock:  
            self.contador += 1

def tarea(contador):
    for _ in range(100):
        contador.incrementar()

contador = Contador()

hilos = []
for i in range(3):
    hilo = threading.Thread(target=tarea, args=(contador,))
    hilos.append(hilo)

for hilo in hilos:
    hilo.start()

for hilo in hilos:
    hilo.join()

print(f"El valor final del contador es: {contador.contador}")
