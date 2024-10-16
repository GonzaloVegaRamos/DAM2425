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

        self.aniadir.clicked.connect(self.abrirVentana)

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
        if texto:
            valor = float(texto)
            resultado = valor / exponente
            self.label.setText(f"{resultado:.2f} {self.simbolo[self.monedaSaliente.currentIndex()]}")
        else:
            self.label.clear()

    def eliminar_moneda(self, index):
    # Verificar que el índice sea válido
     if 0 <= index < len(self.moneda):
        # Eliminar la moneda de las listas
        del self.moneda[index]
        del self.simbolo[index]

        # Eliminar la moneda de los ComboBox
        self.monedaEntrante.removeItem(index)
        self.monedaSaliente.removeItem(index)

        # Mensaje de confirmación
        QtWidgets.QMessageBox.information(self, "Éxito", "Moneda eliminada con éxito.")
     else:
        # Mensaje de error si el índice no es válido
        QtWidgets.QMessageBox.warning(self, "Error", "Índice no válido para eliminar la moneda.")        

    def abrirVentana(self):
        if self.ventana_secundaria is None:
            self.ventana_secundaria = AgregarMoneda(self)  
        self.ventana_secundaria.show()

    def anadir_moneda(self, nombre, simbolo, valor): 
        self.moneda.append(valor)
        self.simbolo.append(simbolo)
        self.monedaEntrante.addItem(nombre)
        self.monedaSaliente.addItem(nombre)
        

class AgregarMoneda(QtWidgets.QDialog):
    def __init__(self, parent=None):
        super(AgregarMoneda, self).__init__(parent)
        uic.loadUi("añadirMoneda.ui", self)

        self.cerrar.clicked.connect(self.close)
        self.agregar.clicked.connect(self.enviar_datos) 

    def enviar_datos(self):
       
        nombre = self.introducirNom.text().strip()
        simbolo = self.introducirSim.text().strip()
        try:
            valor = float(self.introducirValor.text().strip())
        except ValueError:
            valor = None

        # Verificar que todos los campos estén llenos y el valor sea numérico
        if not nombre or not simbolo or valor is None:
            QtWidgets.QMessageBox.warning(self, "Error", "Por favor, completa todos los campos correctamente.")
            return

        # Enviar los datos a la ventana principal
        parent = self.parent()
        if isinstance(parent, Ventana):  # Verificar que el padre es una instancia de Ventana
            parent.anadir_moneda(nombre, simbolo, valor)  # Cambiado aquí
        self.accept()  # Cerrar la ventana secundaria










app = QtWidgets.QApplication(sys.argv)
miVentana = Ventana()
miVentana.show()
sys.exit(app.exec())
