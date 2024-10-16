import sys
from PyQt6 import QtWidgets, uic
from PyQt6.QtGui import QIntValidator

# Ventana principal
class Ventana(QtWidgets.QMainWindow):
    def __init__(self, padre=None):
        QtWidgets.QMainWindow.__init__(self, padre)
        uic.loadUi("Conversor.ui", self)
        self.setWindowTitle("Ejemplo")

        self.cerrarVentana.clicked.connect(self.close)
        self.ventana_secundaria = None
        self.input.setValidator(QIntValidator())

        self.moneda1 = 1.0
        self.moneda2 = 1.0

        
        self.input.textChanged.connect(self.actualizar_conversion)
        self.limpiar.clicked.connect(self.limpiarValores)
        self.monedaEntrante.currentIndexChanged.connect(self.ValorMoneda1)
        self.monedaSaliente.currentIndexChanged.connect(self.ValorMoneda2)
        self.monedaEntrante.currentIndexChanged.connect(self.actualizar_conversion)
        self.monedaSaliente.currentIndexChanged.connect(self.actualizar_conversion)

        self.moneda = [1.0, 0.85, 110.0, 0.75, 0.94]  # USD, EUR, JPY, GBP, CHF
        self.simbolo = ["$", "€", "¥", "£", "₣"]      # USD, EUR, JPY, GBP, CHF

    def ValorMoneda1(self, index):
        self.moneda1 = self.moneda[index]
        self.simboloEntrante.setText(self.simbolo[index])

    def ValorMoneda2(self, index):
        self.moneda2 = self.moneda[index]
  

    def limpiarValores(self):
        self.label.clear()
        self.input.clear()

    def actualizar_conversion(self):

        texto = self.input.text()
        exponente = self.moneda1 / self.moneda2


        # Realizar la conversión
        if texto:  
                valor = float(texto) 
                resultado = valor / exponente
                self.label.setText(f"{resultado:.2f} {self.simbolo[self.monedaSaliente.currentIndex()]}")

        else:
            self.label.clear()

app = QtWidgets.QApplication(sys.argv)
miVentana = Ventana()
miVentana.show()
sys.exit(app.exec())
