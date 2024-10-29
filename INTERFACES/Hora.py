import tkinter as tk
import time

def update_time():
    """Actualiza el tiempo cada segundo."""
    current_time = time.strftime("%H:%M:%S")  # Formato de hora
    time_label.config(text=current_time)
    time_label.after(1000, update_time)  # Actualizar cada segundo

def hide_overlay(event):
    """Oculta la ventana cuando el mouse está encima de ella."""
    root.withdraw()  # Ocultar la ventana

def show_overlay(event):
    """Muestra la ventana cuando el mouse está fuera de ella."""
    root.deiconify()  # Mostrar la ventana
    root.lift()  # Llevar la ventana al frente
    root.attributes('-alpha', 1)  # Hacer la ventana completamente visible

# Configuración de la ventana principal
root = tk.Tk()
root.title("Overlay de Hora")
root.geometry("200x100")
root.wm_attributes("-topmost", 1)  # Mantener la ventana siempre encima
root.overrideredirect(True)  # Quitar bordes de la ventana

# Configuración del label para mostrar la hora
time_label = tk.Label(root, font=("Helvetica", 24), fg="black", bg="white")
time_label.pack(expand=True)

# Actualizar la hora y asignar eventos de mouse
update_time()
root.bind("<Enter>", hide_overlay)  # Ocultar al entrar el mouse
root.bind("<Leave>", show_overlay)  # Mostrar al salir el mouse

# Inicialmente, la ventana se mostrará
root.deiconify()  # Asegúrate de que la ventana se muestre inicialmente

# Mostrar la ventana inicialmente en la posición deseada
root.geometry(f"+{root.winfo_screenwidth() - 250}+50")  # Ajusta la posición en la pantalla

root.mainloop()
