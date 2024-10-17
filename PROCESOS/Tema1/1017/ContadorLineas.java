import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ContadorLineas {
    public static void main(String[] args) {
        // Asegúrate de proporcionar la ruta correcta a tu archivo Java
        String archivoJava = "PROCESOS/Tema1/1002/Comando.java"; // Cambia esto por el nombre de tu archivo

        // Crear ProcessBuilder para contar las líneas usando 'find'
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "find /c /v \"\" \"" + archivoJava + "\"");

        try {
            // Iniciar el proceso
            Process proceso = pb.start();

            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String line;
            StringBuilder resultado = new StringBuilder();

            // Imprimir cada línea de la salida
            while ((line = reader.readLine()) != null) {
                resultado.append(line).append("\n");
            }

            // Esperar a que el proceso termine
            int exitCode = proceso.waitFor();
            if (exitCode == 0) {
                System.out.println("Resultado del conteo de líneas:");
                System.out.print(resultado.toString());
            } else {
                System.out.println("Error al contar las líneas. Código de salida: " + exitCode);
            }
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.err.println("El proceso fue interrumpido: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

