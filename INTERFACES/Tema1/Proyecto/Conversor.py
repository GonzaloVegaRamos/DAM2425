import sys
import os
from PyQt6 import QtWidgets, uic
from PyQt6.QtGui import QIntValidator,QFont
from PyQt6.QtCore import QTimer
from datetime import datetime

script_dir = os.path.dirname(os.path.abspath(__file__))
Conversorui_file_path = os.path.join(script_dir, "Conversor.ui")
añadirMonedaui_file_path = os.path.join(script_dir, "añadirMoneda.ui")
monedas_txt = os.path.join(script_dir, "monedas.txt")

class Ventana(QtWidgets.QMainWindow):
    def __init__(self, padre=None):
        QtWidgets.QMainWindow.__init__(self, padre)
        uic.loadUi(Conversorui_file_path, self)
        self.setWindowTitle("Conversor de Monedas")
        

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

        self.btn_aniadir.clicked.connect(self.abrirVentana)

        self.moneda = [1.0, 0.85, 110.0, 0.75, 0.94]  # USD, EUR, JPY, GBP, CHF
        self.simbolo = ["$", "€", "¥", "£", "₣"]      # USD, EUR, JPY, GBP, CHF
        self.cargarMoneda()

        fuente = QFont()
        fuente.setPointSize(34) 
        self.hora.setFont(fuente)
        
        self.timer = QTimer(self)
        self.timer.timeout.connect(self.actualizar_hora)
        self.timer.start(1000) 

        
        self.actualizar_hora()

    def actualizar_hora(self):
       
        hora_actual = datetime.now().strftime("%H:%M")
        self.hora.setText(hora_actual)  

    def cargarMoneda(self):
      
      if not os.path.exists(monedas_txt):
        with open(monedas_txt, 'w') as file:
            pass 
       
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



    def abrirVentana(self):
        if self.ventana_secundaria is None:
            self.ventana_secundaria = AgregarMoneda(self)  
        self.ventana_secundaria.show()

    def verificar_moneda(self, nombre, simbolo, valor): 
     
        with open(monedas_txt, 'r') as file:
          
          for line in file:
            nombreTxt, simboloTxt, valorTxt = line.strip().split(',')

            if nombreTxt.upper() == nombre.upper(): 
                alerta = QtWidgets.QMessageBox()
                alerta.setText("Esa moneda ya existe")  
                alerta.setWindowTitle("Advertencia")  
                alerta.setStandardButtons(QtWidgets.QMessageBox.StandardButton.Ok) 
                alerta.exec()  
                return  
            
        self.aniadir(nombre, simbolo, valor)

    def aniadir(self, nombre, simbolo, valor):
          with open(monedas_txt, 'a') as file:
              file.write(f"{nombre},{simbolo},{valor}\n") 
          self.cargarMoneda()
        

class AgregarMoneda(QtWidgets.QDialog):
    def __init__(self, parent=None):
        super(AgregarMoneda, self).__init__(parent)
        uic.loadUi(añadirMonedaui_file_path, self)

        self.introducirValor.setValidator(QIntValidator())
        self.cerrar.clicked.connect(self.close)
        self.agregar.clicked.connect(self.enviar_datos) 

    def enviar_datos(self):
       
       
        nombre = self.introducirNom.text().strip()
        simbolo = self.introducirSim.text().strip()
        try:
            valor = float(self.introducirValor.text().strip())
        except ValueError:
            valor = None

        if not nombre or valor is None:
            QtWidgets.QMessageBox.warning(self, "Error", "Por favor, completa todos los campos correctamente.")
            return
        
        parent = self.parent()
        if isinstance(parent, Ventana):
          if simbolo is None:
             simbolo = nombre[:3].upper()  
          parent.verificar_moneda(nombre, simbolo, valor)  
          
        self.introducirNom.clear()
        self.introducirSim.clear()
        self.introducirValor.clear()
        self.accept()  



app = QtWidgets.QApplication(sys.argv)
miVentana = Ventana()
miVentana.show()
sys.exit(app.exec())
