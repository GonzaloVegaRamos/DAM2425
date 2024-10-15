import sys,time, threading

from PyQt6 import QtWidgets, uic

class VentanaSecundaria(QtWidgets.QDialog):  
    def __init__(self, parent=None):
        super(VentanaSecundaria, self).__init__(parent)
        uic.loadUi("2.ui",self) 
        self.pushButton.clicked.connect(self.cerrar)
        self.progressBar

        self.progreso.clicked.connect(self.automatico)
        self.contador = 0

        self.x = threading.Thread(target=self.barra_progreso)
       
    def barra_progreso(self):
         
         for i in range (10000):
            time.sleep(0.5)
           
            if self.progressBar.value() < self.progressBar.maximum():
                self.label.setText("Cargando Tabla...")
                self.contador +=20
                self.progressBar.setValue(self.contador)
           
            elif self.progressBar.value() == 100:
                self.label.setText("Barra Completada")
                time.sleep(2)
                self.contador=0
                self.progressBar.reset()


    def cerrar(self):
        self.close()

    def automatico (self):
        self.x.start()
        # for i in range (20):
        #     time.sleep(1)
        #     self.current_value += 5
        #     self.progressBar.setValue(self.current_value) 
        #     if(self.progressBar.value() == 100):
        #         self.cerrar()




class Ventana(QtWidgets.QMainWindow):
    '''Esta es la clase principal''' 
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


        



