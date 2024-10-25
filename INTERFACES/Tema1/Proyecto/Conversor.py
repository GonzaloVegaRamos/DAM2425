import sys
import os
from PyQt6 import QtWidgets, uic
from PyQt6.QtGui import QIntValidator

script_dir = os.path.dirname(os.path.abspath(__file__))
Conversorui_file_path = os.path.join(script_dir, "Conversor.ui")
añadirMonedaui_file_path = os.path.join(script_dir, "añadirMoneda.ui")
monedas_txt = os.path.join(script_dir, "monedas.txt")

class Ventana(QtWidgets.QMainWindow):
    def __init__(self, padre=None):
        QtWidgets.QMainWindow.__init__(self, padre)
        uic.loadUi(Conversorui_file_path, self)
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
        self.cargarMoneda()

    def cargarMoneda(self):
       
       try:
         with open(monedas_txt, 'r') as file:
            for line in file:
                nombre, simbolo, valor = line.strip().split(',')  
                valor = float(valor) 
                self.moneda.append(valor)
                self.simbolo.append(simbolo)
                self.monedaEntrante.addItem(nombre+" ("+nombre[:3].upper()+")")
                self.monedaSaliente.addItem(nombre+" ("+nombre[:3].upper()+")")
               
       except FileNotFoundError:
        print("El archivo 'moneda.txt' no existe.")

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

    #TODO: ELIMINAR_MONEDA
    def eliminar_moneda(self, index):
   
     if 0 <= index < len(self.moneda):
       
        del self.moneda[index]
        del self.simbolo[index]

        
        self.monedaEntrante.removeItem(index)
        self.monedaSaliente.removeItem(index)

        QtWidgets.QMessageBox.information(self, "Éxito", "Moneda eliminada con éxito.")
     else:
        QtWidgets.QMessageBox.warning(self, "Error", "Índice no válido para eliminar la moneda.")        

    def abrirVentana(self):
        if self.ventana_secundaria is None:
            self.ventana_secundaria = AgregarMoneda(self)  
        self.ventana_secundaria.show()

    def anadir_moneda(self, nombre, simbolo, valor): 
      # Comprobar si la moneda ya existe
        with open(monedas_txt, 'r') as file:
          for line in file:
            nombreTxt, simboloTxt, valorTxt = line.strip().split(',')

            if nombreTxt.upper() == nombre.upper():  # Comparación correcta
                alerta = QtWidgets.QMessageBox()
                alerta.setText("Esa moneda ya existe")  # Texto del mensaje
                alerta.setWindowTitle("Advertencia")  # Título de la ventana
                alerta.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Ok)  # Botón "Aceptar"
                alerta.exec()  # Mostrar la ventana de alerta
                return  # Salir de la función si la moneda ya existe

              # Si no existe, agregar la nueva moneda
          with open(monedas_txt, 'a') as file:  
             file.write(f"{nombre},{simbolo},{valor}\n") 
             self.cargarMoneda()  # Cargar las monedas nuevamente

        
        

class AgregarMoneda(QtWidgets.QDialog):
    def __init__(self, parent=None):
        super(AgregarMoneda, self).__init__(parent)
        uic.loadUi(añadirMonedaui_file_path, self)

        self.cerrar.clicked.connect(self.close)
        self.agregar.clicked.connect(self.enviar_datos) 

    def enviar_datos(self):
       
        nombre = self.introducirNom.text().strip()
        simbolo = self.introducirSim.text().strip()
        try:
            valor = float(self.introducirValor.text().strip())
        except ValueError:
            valor = None

        if not nombre or not simbolo or valor is None:
            QtWidgets.QMessageBox.warning(self, "Error", "Por favor, completa todos los campos correctamente.")
            return

        parent = self.parent()
        if isinstance(parent, Ventana):  
            parent.anadir_moneda(nombre, simbolo, valor)  
        self.accept()  



app = QtWidgets.QApplication(sys.argv)
miVentana = Ventana()
miVentana.show()
sys.exit(app.exec())
