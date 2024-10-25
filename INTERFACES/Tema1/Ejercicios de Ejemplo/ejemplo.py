import sys
from PyQt6.QtWidgets import (
    QApplication, QMainWindow, QWidget, QVBoxLayout, QPushButton, QLineEdit, QLabel,
    QMessageBox, QComboBox, QRadioButton, QCheckBox, QDialog, QHBoxLayout
)
from PyQt6.QtCore import Qt


class LoginWindow(QDialog):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Ventana de Login")
        self.setGeometry(150, 150, 300, 200)
        
        # Layout principal
        layout = QVBoxLayout()
        
        # Etiqueta y campo de texto para el nombre de usuario
        self.username_label = QLabel("Nombre de usuario:", self)
        layout.addWidget(self.username_label)

        

        self.username_input = QLineEdit(self)
        layout.addWidget(self.username_input)
        
        # Etiqueta y campo de texto para la contraseña (con modo de ocultación)
        self.password_label = QLabel("Contraseña:", self)
        layout.addWidget(self.password_label)
        
        self.password_input = QLineEdit(self)
        self.password_input.setEchoMode(QLineEdit.EchoMode.Password)
        layout.addWidget(self.password_input)
        
        # Radio buttons para opciones
        self.radio1 = QRadioButton("Opción 1", self)
        self.radio2 = QRadioButton("Opción 2", self)
        layout.addWidget(self.radio1)
        layout.addWidget(self.radio2)
        
        # Checkbox para recordar la sesión
        self.remember_checkbox = QCheckBox("Recordar sesión", self)
        layout.addWidget(self.remember_checkbox)
        

        self.Cerrarbutton = QPushButton("Cerrar?")
        layout.addWidget(self.Cerrarbutton)
        self.Cerrarbutton.clicked.connect(self.close)


        # Botón de inicio de sesión
        self.login_button = QPushButton("Iniciar sesión", self)
        self.login_button.clicked.connect(self.login)
        layout.addWidget(self.login_button)
        
        self.setLayout(layout)

    def cerrar():
        LoginWindow.close()
    
    def login(self):
        # Obtiene los datos de usuario y contraseña
        username = self.username_input.text()
        password = self.password_input.text()
        
        # Simulación de autenticación simple
        if username == "usuario" and password == "contraseña":
            QMessageBox.information(self, "Inicio de sesión", "Inicio de sesión exitoso!")
            self.accept()  # Cierra el diálogo de login con éxito
        else:
            QMessageBox.warning(self, "Error", "Nombre de usuario o contraseña incorrectos.")


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        
        # Configuración de la ventana principal
        self.setWindowTitle("Ejemplo con Ventana de Login - PyQt6")
        self.setGeometry(100, 100, 400, 400)
        
        # Contenedor principal
        container = QWidget()
        self.setCentralWidget(container)
        
        # Layout principal
        layout = QVBoxLayout()
        container.setLayout(layout)
        
        # Etiqueta principal
        self.label = QLabel("Ingrese su nombre:", self)
        layout.addWidget(self.label)
        
        # Cuadro de texto (QLineEdit)
        self.text_input = QLineEdit(self)
        self.text_input.textChanged.connect(self.update_label)
        layout.addWidget(self.text_input)
        
        # Label dinámico
        self.dynamic_label = QLabel("Escribe en el cuadro para ver aquí", self)
        layout.addWidget(self.dynamic_label)
        
        # ComboBox
        self.combo_box = QComboBox(self)
        self.combo_box.addItems(["Opción 1", "Opción 2", "Opción 3"])
        layout.addWidget(self.combo_box)
        
        # Botón para mostrar mensaje
        self.button = QPushButton("Mostrar mensaje", self)
        self.button.clicked.connect(self.show_message)
        layout.addWidget(self.button)
        
        # Botón para abrir cuadro de diálogo
        self.dialog_button = QPushButton("Abrir diálogo", self)
        self.dialog_button.clicked.connect(self.show_dialog)
        layout.addWidget(self.dialog_button)
        
        # Botón para abrir la ventana de Login
        self.login_button = QPushButton("Abrir Login", self)
        self.login_button.clicked.connect(self.open_login)
        layout.addWidget(self.login_button)
    
    # Función para mostrar mensaje
    def show_message(self):
        user_text = self.text_input.text()
        QMessageBox.information(self, "Mensaje", f"Hola, {user_text}!")
    
    # Función para mostrar diálogo
    def show_dialog(self):
        dialog = QMessageBox(self)
        dialog.setWindowTitle("Diálogo de información")
        dialog.setText("Este es un cuadro de diálogo.")
        dialog.setStandardButtons(QMessageBox.StandardButton.Ok | QMessageBox.StandardButton.Cancel)
        dialog.setIcon(QMessageBox.Icon.Information)
        
        result = dialog.exec()
        if result == QMessageBox.StandardButton.Ok:
            self.label.setText("Presionaste OK")
        else:
            self.label.setText("Presionaste Cancel")
    
    # Función para actualizar el label dinámico
    def update_label(self):
        current_text = self.text_input.text()
        self.dynamic_label.setText(current_text)
    
    # Función para abrir la ventana de Login
    def open_login(self):
        login_window = LoginWindow()
        login_window.exec()


# Ejecutar la aplicación
if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = MainWindow()
    window.show()
    sys.exit(app.exec())
