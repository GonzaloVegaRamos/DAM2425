import sys
from PyQt6.QtWidgets import (QApplication, QWidget,
                             QPushButton, QLineEdit, QGridLayout)

class Calculadora(QWidget):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("La Calculadora")
        self.setGeometry(600, 100, 300, 300)

        self.resultado = QLineEdit()
        layout = QGridLayout()
        layout.addWidget(self.resultado, 0, 0, 1, 5)
        
        nombre_boton = [["1", "2", "3", "+"],
                        ["4", "5", "6", "-"],
                        ["7", "8", "9", "*"],
                        ["0", "/", "C", "="]]
        
        # Añadir componentes.
        for i in range(1, 5):
            for j in range(4): 
                boton = QPushButton(nombre_boton[i-1][j])
                layout.addWidget(boton, i, j)

                # Conectar el botón "C" para limpiar el QLineEdit
                if boton.text() == "C":
                    boton.clicked.connect(self.clear)
                else:
                    boton.clicked.connect(self.press_button)

        self.setLayout(layout)

    def press_button(self):
        sender = self.sender()
        if sender.text() == "=":
            try:
                self.resultado.setText(str(eval(self.resultado.text())))
            except Exception:
                self.resultado.setText("Operación inválida")
        else:
            self.resultado.setText(self.resultado.text() + sender.text())

    def clear(self):
        self.resultado.clear()  # Limpiar el contenido del QLineEdit


if __name__ == "__main__":
    app = QApplication(sys.argv)
    calc = Calculadora()
    calc.show()
    sys.exit(app.exec())
