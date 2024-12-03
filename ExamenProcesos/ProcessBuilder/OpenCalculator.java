
import java.io.IOException;

    public class OpenCalculator {
        public static void main(String[] args) {
            // Determinar el comando según el sistema operativo
            String command;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                command = "calc"; // Comando para Windows
            } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                command = "open -a Calculator"; // Comando para macOS
            } else {
                command = "gnome-calculator"; // Comando para Linux (GNOME)
            }
    
            // Configurar el proceso
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (command.contains(" ")) { // Manejar espacios en comandos (macOS)
                processBuilder.command("bash", "-c", command);
            } else {
                processBuilder.command(command);
            }
    
            try {
                // Iniciar el proceso
                Process process = processBuilder.start();
                System.out.println("La calculadora se abrió correctamente.");
    
                // Esperar a que el proceso termine (opcional)
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                System.err.println("Ocurrió un error al intentar abrir la calculadora.");
                e.printStackTrace();
            }
        }
    }
       

