import sys, time
from PyQt6 import QtWidgets, uic
from PyQt6.QtCore import QThread, pyqtSignal

# Clase que maneja la barra de progreso en un hilo separado
class ProgresoThread(QThread):
    progreso_actualizado = pyqtSignal(int)  # Señal que enviará el progreso
    completado = pyqtSignal()  # Señal para indicar que la barra de progreso se completó

    def __init__(self):
        super().__init__()
        self.contador = 0
        self.running = True  # Bandera para controlar el ciclo

    def run(self):
        while self.running and self.contador < 100:
            time.sleep(0.5)  # Simula una tarea larga
            self.contador += 20
            self.progreso_actualizado.emit(self.contador)  # Enviar el valor del progreso
        if self.contador >= 100:
            self.completado.emit()  # Enviar señal cuando el progreso se complete

    def stop(self):
        self.running = False  # Detener el hilo


# Ventana secundaria con barra de progreso
class VentanaSecundaria(QtWidgets.QDialog):
    def __init__(self, parent=None):
        super(VentanaSecundaria, self).__init__(parent)
        uic.loadUi("2.ui", self)
        self.pushButton.clicked.connect(self.cerrar)

        self.progreso.clicked.connect(self.automatico)
        self.progressBar.setValue(0)

        self.progreso_thread = None

    def automatico(self):
        # Inicia el hilo de la barra de progreso
        if self.progreso_thread is None or not self.progreso_thread.isRunning():
            self.progreso_thread = ProgresoThread()
            self.progreso_thread.progreso_actualizado.connect(self.actualizar_barra)
            self.progreso_thread.completado.connect(self.completado)
            self.progreso_thread.start()

    def actualizar_barra(self, valor):
        self.progressBar.setValue(valor)
        self.label.setText(f"Cargando... {valor}%")

    def completado(self):
        self.label.setText("Barra Completada")
        self.progreso_thread.stop()
        self.close()  # Detiene el hilo

    def cerrar(self):
        if self.progreso_thread is not None:
            self.progreso_thread.stop()  # Asegurarse de detener el hilo
        self.close()


# Ventana principal
class Ventana(QtWidgets.QMainWindow):
    def __init__(self, padre=None):
        QtWidgets.QMainWindow.__init__(self, padre)
        uic.loadUi("conex.ui", self)
        self.setWindowTitle("Ejemplo")
        self.abrir.clicked.connect(self.abrirVentana)
        self.cambiarmensaje.clicked.connect(self.CambiarMensaje)
        self.cerrarVentana.clicked.connect(self.cerrar)
        self.ventana_secundaria = None

    def cerrar(self):
        self.close()

    def CambiarMensaje(self):
        if self.label.text() == "":
            self.label.setText("Hola clase")
        else:
            self.label.setText("")

    def abrirVentana(self):
        if self.ventana_secundaria is None:
            self.ventana_secundaria = VentanaSecundaria()

        self.ventana_secundaria.show()


app = QtWidgets.QApplication(sys.argv)
miVentana = Ventana()
miVentana.show()
sys.exit(app.exec())
