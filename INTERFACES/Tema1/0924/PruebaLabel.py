import sys
from PyQt6.QtCore import QSize, Qt
from PyQt6.QtWidgets import QApplication, QMainWindow, QLabel, QLineEdit, QVBoxLayout, QWidget

class MiVentana(QMainWindow):
    def __init__(self):  # Constructor
        super().__init__()
        self.setWindowTitle("Mi Ventana")  # Nombre de la ventana

        # Crear los widgets
        self.label = QLabel()
        self.input = QLineEdit()

        # Conectar la señal del QLineEdit al QLabel
        self.input.textChanged.connect(self.label.setText)

        # Crear el layout y agregar los widgets
        layout = QVBoxLayout()
        layout.addWidget(self.input)
        layout.addWidget(self.label)

        # Crear un QWidget y establecer el layout
        container = QWidget()
        container.setLayout(layout)

        # Establecer el QWidget como el widget central
        self.setCentralWidget(container)

# Iniciar la aplicación
if __name__ == "__main__":
    app = QApplication(sys.argv)
    ventana = MiVentana()
    ventana.show()
    sys.exit(app.exec())
