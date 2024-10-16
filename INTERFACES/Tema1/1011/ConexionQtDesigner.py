import sys,os,time,threading
from PyQt6 import QtWidgets, uic

script_dir = os.path.dirname(os.path.abspath(__file__))
Dui_file_path = os.path.join(script_dir, "2.ui")
conexui_file_path = os.path.join(script_dir, "conex.ui")

class VentanaSecundaria(QtWidgets.QDialog):  
    def __init__(self, parent=None):
        super(VentanaSecundaria, self).__init__(parent)
        uic.loadUi(Dui_file_path, self) 
        self.pushButton.clicked.connect(self.cerrar)
        self.progressBar

        self.progreso.clicked.connect(self.automatico)
        self.contador = 0
        self.is_running = False  # Variable para controlar si el thread está en ejecución

    def barra_progreso(self):
        for i in range(20):
            time.sleep(0.5)  # Simula el trabajo

            # Actualiza la barra de progreso
            if self.progressBar.value() < self.progressBar.maximum():
                self.label.setText("Cargando Tabla...")
                self.contador += 5
                self.progressBar.setValue(self.contador)

            # Verifica si la barra de progreso ha llegado a 100
            if self.progressBar.value() == 100:
                self.label.setText("Barra Completada")
                time.sleep(2)  # Espera 2 segundos antes de finalizar 
                self.is_running = False  # Marca como no en ejecución
                self.progressBar.setValue(0) 
                self.cerrar()
                return  # Termina el thread

    def cerrar(self):
        self.close()

    def automatico(self):
        if not self.is_running:  # Verifica si el thread no está en ejecución
            self.is_running = True  # Marca como en ejecución
            self.contador = 0  # Reinicia el contador
             # Reinicia el progress bar
            self.label.setText("Cargando...")  # Cambia el texto de la etiqueta
            self.x = threading.Thread(target=self.barra_progreso)
            self.x.start()


class Ventana(QtWidgets.QMainWindow):
    '''Esta es la clase principal''' 
    def __init__(self, padre=None):
        QtWidgets.QMainWindow.__init__(self, padre)
        uic.loadUi(conexui_file_path, self) 
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
