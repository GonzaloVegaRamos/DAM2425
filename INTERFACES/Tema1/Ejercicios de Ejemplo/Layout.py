import sys
from PyQt6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QVBoxLayout, QHBoxLayout, QGridLayout, 
    QFormLayout, QPushButton, QLabel, QLineEdit, QComboBox, QRadioButton, 
    QCheckBox, QGroupBox
)


class LayoutExample(QMainWindow):
    def __init__(self):
        super().__init__()

        # Configuración de la ventana principal
        self.setWindowTitle("Ejemplo de Diferentes Layouts - PyQt6")
        self.setGeometry(100, 100, 600, 400)

        # Contenedor principal
        container = QWidget()
        self.setCentralWidget(container)

        # Layout principal que organiza otros layouts
        main_layout = QVBoxLayout()
        container.setLayout(main_layout)

        # ** QVBoxLayout **
        vbox_layout = QVBoxLayout()
        vbox_group = QGroupBox("Vertical Layout")
        vbox_group.setLayout(vbox_layout)

        vbox_layout.addWidget(QLabel("Botón 1 (VBox)"))
        vbox_layout.addWidget(QPushButton("Botón 2"))
        vbox_layout.addWidget(QPushButton("Botón 3"))

        main_layout.addWidget(vbox_group)

        # ** QHBoxLayout **
        hbox_layout = QHBoxLayout()
        hbox_group = QGroupBox("Horizontal Layout")
        hbox_group.setLayout(hbox_layout)

        hbox_layout.addWidget(QLabel("Botón A (HBox)"))
        hbox_layout.addWidget(QPushButton("Botón B"))
        hbox_layout.addWidget(QPushButton("Botón C"))

        main_layout.addWidget(hbox_group)

        # ** QGridLayout **
        grid_layout = QGridLayout()
        grid_group = QGroupBox("Grid Layout")
        grid_group.setLayout(grid_layout)

        grid_layout.addWidget(QLabel("Fila 0, Columna 0"), 0, 0)
        grid_layout.addWidget(QPushButton("Fila 0, Columna 1"), 0, 1)
        grid_layout.addWidget(QLabel("Fila 1, Columna 0"), 1, 0)
        grid_layout.addWidget(QPushButton("Fila 1, Columna 1"), 1, 1)
        grid_layout.addWidget(QLabel("Fila 2, Columna 0"), 2, 0, 1, 2)  # Col span 2

        main_layout.addWidget(grid_group)

        # ** QFormLayout **
        form_layout = QFormLayout()
        form_group = QGroupBox("Form Layout")
        form_group.setLayout(form_layout)

        form_layout.addRow(QLabel("Nombre:"), QLineEdit())
        form_layout.addRow(QLabel("Contraseña:"), QLineEdit())
        form_layout.addRow(QLabel("Género:"), QComboBox())
        form_layout.addRow(QLabel("Opción 1:"), QRadioButton("Opción A"))
        form_layout.addRow(QLabel("Opción 2:"), QRadioButton("Opción B"))
        form_layout.addRow(QLabel("Recuerdame:"), QCheckBox())

        main_layout.addWidget(form_group)


# Ejecutar la aplicación
if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = LayoutExample()
    window.show()
    sys.exit(app.exec())
