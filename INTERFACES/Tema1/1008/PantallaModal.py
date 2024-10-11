
import sys

from PyQt6.QtWidgets import QApplication, QDialog, QMainWindow, QPushButton,QVBoxLayout,QLabel


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("My App")

        button = QPushButton("Press me for a dialog!")
        button.clicked.connect(self.button_clicked)
        self.setCentralWidget(button)

    def button_clicked(self, s):
        print("click", s)

        dlg = CustomDialog()
        
        dlg.setGeometry(600, 250, 300, 300)
       
        dlg.setWindowTitle("HELLO!")
        
        dlg.exec()



class CustomDialog(QDialog):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("HELLO!")
        buttonBox = QPushButton("Volver?")
        layout = QVBoxLayout()
        message = QLabel("Something happened, is that OK?")
        buttonBox.clicked.connect(self.button_clicked)
        layout.addWidget(message)
        layout.addWidget(buttonBox)
        self.setLayout(layout)

    def button_clicked(self, s):
       print("HOLA")
       CustomDialog.close(self)
    

app = QApplication(sys.argv)
window = MainWindow()
window.show()
app.exec()