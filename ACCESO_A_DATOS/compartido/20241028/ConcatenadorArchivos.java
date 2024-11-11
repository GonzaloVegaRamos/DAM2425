import java.io.*;

public class ConcatenadorArchivos {

    public static void concatenarFicheros(String archivoFuente, String archivoDestino) {
        File fuente = new File(archivoFuente);
        
        // Verificar que el archivo fuente existe
        if (!fuente.exists()) {
            System.out.println("El archivo fuente no existe: " + archivoFuente);
            return;
        }

        try (
            BufferedReader lector = new BufferedReader(new FileReader(fuente));
            BufferedWriter escritor = new BufferedWriter(new FileWriter(archivoDestino, true)) // Modo append activado
            ) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                escritor.write(linea);
                escritor.newLine();
            }
            System.out.println("Contenido de " + archivoFuente + " ha sido añadido a " + archivoDestino);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al concatenar los archivos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        String archivoFuente = "archivo1.txt";  // Archivo fuente que queremos concatenar
        String archivoDestino = "archivo2.txt"; // Archivo donde se añadirá el contenido

        concatenarFicheros(archivoFuente, archivoDestino);
    }
}