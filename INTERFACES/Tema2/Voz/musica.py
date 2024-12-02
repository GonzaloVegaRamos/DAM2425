import pygame

# Inicializa Pygame
pygame.init()
pygame.mixer.init()

# Dimensiones de la ventana y colores
ANCHO, ALTO = 640, 480
NEGRO = (0, 0, 0)
BLANCO = (255, 255, 255)
GRIS = (128, 128, 128)

# Cargar las canciones
song1 = pygame.mixer.Sound("Mesmerizing Galaxy Loop.wav")
song2 = pygame.mixer.Sound("Galactic Rap.wav")

# Reproducir las canciones en bucle
canal_song1 = pygame.mixer.Channel(0)
canal_song2 = pygame.mixer.Channel(1)
canal_song1.play(song1, loops=-1)
canal_song2.play(song2, loops=-1)

# Volúmenes iniciales
volumen_song1 = 0.5  # Entre 0.0 y 1.0
volumen_song2 = 0.5  # Entre 0.0 y 1.0
canal_song1.set_volume(volumen_song1)
canal_song2.set_volume(volumen_song2)

# Barra deslizadora
slider_x = ANCHO // 2  # Posición inicial del deslizador
slider_y = ALTO // 2
slider_width = 20
slider_height = 40
barra_width = 400  # Longitud de la barra
barra_height = 10

# Configurar la ventana de Pygame
screen = pygame.display.set_mode((ANCHO, ALTO))
pygame.display.set_caption("Control de Volumen Gráfico")

# Función para actualizar el volumen basado en la posición del deslizador
def actualizar_volumen(slider_pos):
    global volumen_song1, volumen_song2
    # Calcular el balance como proporción entre 0 y 1
    balance = (slider_pos - (ANCHO // 2 - barra_width // 2)) / barra_width
    balance = max(0.0, min(1.0, balance))  # Restringir entre 0.0 y 1.0

    # Asignar volúmenes inversos
    volumen_song1 = 1.0 - balance
    volumen_song2 = balance
    canal_song1.set_volume(volumen_song1)
    canal_song2.set_volume(volumen_song2)

# Función para dibujar la barra de control y el deslizador
def dibujar_interfaz(slider_pos):
    screen.fill(NEGRO)  # Limpiar la pantalla

    # Dibujar la barra
    barra_x = ANCHO // 2 - barra_width // 2
    pygame.draw.rect(screen, GRIS, (barra_x, slider_y, barra_width, barra_height))

    # Dibujar el deslizador
    pygame.draw.rect(screen, BLANCO, (slider_pos - slider_width // 2, slider_y - slider_height // 2, slider_width, slider_height))

    # Dibujar los volúmenes actuales
    fuente = pygame.font.Font(None, 36)
    texto = fuente.render(f"Vol1: {volumen_song1:.2f}, Vol2: {volumen_song2:.2f}", True, BLANCO)
    screen.blit(texto, (ANCHO // 2 - texto.get_width() // 2, slider_y - 60))

    pygame.display.flip()  # Actualizar pantalla

# Bucle principal
running = True
dragging = False
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False

        # Detectar si el deslizador está siendo arrastrado
        if event.type == pygame.MOUSEBUTTONDOWN:
            if slider_x - slider_width // 2 <= event.pos[0] <= slider_x + slider_width // 2:
                dragging = True

        if event.type == pygame.MOUSEBUTTONUP:
            dragging = False

        if event.type == pygame.MOUSEMOTION and dragging:
            # Restringir el movimiento del deslizador dentro de la barra
            slider_x = max(ANCHO // 2 - barra_width // 2, min(ANCHO // 2 + barra_width // 2, event.pos[0]))
            actualizar_volumen(slider_x)

    # Dibujar la interfaz gráfica
    dibujar_interfaz(slider_x)

    pygame.time.delay(10)  # Pausar un poco el ciclo para evitar sobrecargar el procesador

# Detener la reproducción al cerrar la ventana
pygame.quit()
