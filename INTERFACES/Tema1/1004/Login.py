import sys
from PyQt6.QtWidgets import (QApplication, QWidget,
                             QPushButton, QLineEdit, QGridLayout,QLabel,QDialog,QVBoxLayout)

class Login(QWidget):

    def __init__(self):
        super().__init__()

        self.setWindowTitle("Login")
        self.setGeometry(600, 250, 300, 300)
        
        self.nombre = QLineEdit()
        self.passwd = QLineEdit()
        layout = QGridLayout()
        self.label = QLabel("NOMBRE:  ")
        self.label1 = QLabel("CONTRASEÑA:  ")
        

        self.error = QLabel(" ")


        layout.addWidget(self.label,0,0,1,0)
        layout.addWidget(self.nombre,0,0,2,0)
        layout.addWidget(self.label1,0,0,3,0)
        layout.addWidget(self.passwd,0,0,4,0)
        layout.addWidget(self.error,0,0,5,0)

        Enviar = QPushButton("Registrarse")
        layout.addWidget(Enviar)

        Login = QPushButton("Login")
        layout.addWidget(Login)

        Limpiar = QPushButton("Limpiar")
        layout.addWidget(Limpiar)

        Enviar.clicked.connect(self.login)
        Limpiar.clicked.connect(self.clear)
        Login.clicked.connect(self.verificar_usuario)

        self.setLayout(layout)

    def login(self):
        nom = self.nombre.text()
        pas = self.passwd.text()
        
        with open("Usuarios.txt", "r") as archivo:
          
          for linea in archivo:
                    
                    usuario = (nom +" "+pas+"\n")
                    
                    if linea == usuario:
                        self.error.setText("Ya hay un usuario con ese nombre!")
                        break
                       
                        
                    else:
                      
                      try:
                           with open("Usuarios.txt", "a") as archivo:
                            archivo.write(nom)
                            archivo.write(" ")
                            archivo.write(pas)
                            archivo.write("\n")
                            self.nombre.clear() 
                            self.passwd.clear() 
                            self.error.setText("Se ha Registrado correctamente")
         
                      except Exception:
                             self.resultado.setText("Operación inválida")

                             
                    return False  
      


    def verificar_usuario(self):
         
            nom = self.nombre.text()
            pas = self.passwd.text()
            self.error.setText(" ") 

            with open("Usuarios.txt", "r") as archivo:
                for linea in archivo:
                    usuario = (nom +" "+pas+"\n")

                    if linea == usuario:
                        layout = QVBoxLayout()
                        dlg = QDialog(self)
                        dlg.setGeometry(600, 250, 300, 300)
                        dlg.setWindowTitle("Perfil de "+nom)
                        dlg.setLayout(layout)
                        
                        buttonBox = QPushButton("Volver")
                        buttonBox1 = QPushButton("Borrar usuario(No funciona)")
                        mensaje = QLabel("Hola de nuevo "+ nom+"!")

                        buttonBox.clicked.connect(lambda:self.volver(dlg))
                        buttonBox.clicked.connect(lambda:self.BorrarUsuario(mensaje))

                        layout.addWidget(mensaje)
                        layout.addWidget(buttonBox1)
                        layout.addWidget(buttonBox)
                        
                        
                        
                        dlg.exec()
                        break
                        return True

                    else:
                        
                        self.error.setText("No existe") 
         
    def volver(self,dlg):
        dlg.close()

    def BorrarUsuario(self,mensaje):
        print("Te lo he dicho")
                       
                        
                    

    def clear(self):
        self.nombre.clear() 
        self.passwd.clear()  
        self.error.setText("Se ha limpiado con exito")


if __name__ == "__main__":
    app = QApplication(sys.argv)
    calc = Login()
    calc.show()
    sys.exit(app.exec())
