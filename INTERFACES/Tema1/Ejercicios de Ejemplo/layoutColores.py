import sys
from PyQt6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QVBoxLayout, QHBoxLayout, QGridLayout,
    QFormLayout, QLabel, QGroupBox
)
from PyQt6.QtGui import QColor, QPalette
from PyQt6.QtCore import Qt 


def create_colored_label(text, color):
    label = QLabel(text)
    label.setAutoFillBackground(True)
    
    # Establecer el color de fondo
    palette = label.palette()
    palette.setColor(QPalette.ColorRole.Window, QColor(color))
    label.setPalette(palette)

    # Alinear el texto al centro
    label.setAlignment(Qt.AlignmentFlag.AlignCenter)
    
    return label


class LayoutExample(QMainWindow):
    def __init__(self):
        super().__init__()

        # Configuración de la ventana principal
        self.setWindowTitle("Ejemplo de Layouts con Colores - PyQt6")
        self.setGeometry(100, 100, 600, 400)

        # Contenedor principal
        container = QWidget()
        self.setCentralWidget(container)

        # Layout principal que organiza otros layouts
        main_layout = QVBoxLayout()
        container.setLayout(main_layout)

        # ** QVBoxLayout **
        vbox_layout = QVBoxLayout()
        vbox_group = QGroupBox("Vertical Layout (QVBoxLayout)")
        vbox_group.setLayout(vbox_layout)

        vbox_layout.addWidget(create_colored_label("VBox - Label 1", "lightblue"))
        vbox_layout.addWidget(create_colored_label("VBox - Label 2", "lightgreen"))
        vbox_layout.addWidget(create_colored_label("VBox - Label 3", "lightcoral"))

        main_layout.addWidget(vbox_group)

        # ** QHBoxLayout **
        hbox_layout = QHBoxLayout()
        hbox_group = QGroupBox("Horizontal Layout (QHBoxLayout)")
        hbox_group.setLayout(hbox_layout)

        hbox_layout.addWidget(create_colored_label("HBox - Label 1", "lightyellow"))
        hbox_layout.addWidget(create_colored_label("HBox - Label 2", "lightpink"))
        hbox_layout.addWidget(create_colored_label("HBox - Label 3", "lightgray"))

        main_layout.addWidget(hbox_group)

        # ** QGridLayout **
        grid_layout = QGridLayout()
        grid_group = QGroupBox("Grid Layout (QGridLayout)")
        grid_group.setLayout(grid_layout)

        grid_layout.addWidget(create_colored_label("Grid (0, 0)", "lightblue"), 0, 0)
        grid_layout.addWidget(create_colored_label("Grid (0, 1)", "lightgreen"), 0, 1)
        grid_layout.addWidget(create_colored_label("Grid (1, 0)", "lightcoral"), 1, 0)
        grid_layout.addWidget(create_colored_label("Grid (1, 1)", "lightyellow"), 1, 1)
        grid_layout.addWidget(create_colored_label("Grid (2, 0)", "lightpink"), 2, 0, 1, 2)  # Col span 2

        main_layout.addWidget(grid_group)

        # ** QFormLayout **
        form_layout = QFormLayout()
        form_group = QGroupBox("Form Layout (QFormLayout)")
        form_group.setLayout(form_layout)

        form_layout.addRow(create_colored_label("Etiqueta 1:", "lightgray"), create_colored_label("Valor 1", "lightblue"))
        form_layout.addRow(create_colored_label("Etiqueta 2:", "lightgray"), create_colored_label("Valor 2", "lightgreen"))
        form_layout.addRow(create_colored_label("Etiqueta 3:", "lightgray"), create_colored_label("Valor 3", "lightcoral"))

        main_layout.addWidget(form_group)


# Ejecutar la aplicación
if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = LayoutExample()
    window.show()
    sys.exit(app.exec())

