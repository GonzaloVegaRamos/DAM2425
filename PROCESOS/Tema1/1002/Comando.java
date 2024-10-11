import java.io.*;

public class Comando {

    
    public static void main(String[] args) {
        try {
            // Crear un ProcessBuilder para el comando 'sort'
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir");
            Process process = pb.start();

            // Obtener el OutputStream del proceso para enviarle datos
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "CP850"); // Codificación para Windows
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = "";
            System.out.println("Salida del comando:");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine y obtener el código de salida
            int exitCode = process.waitFor();
            System.out.println("El proceso terminó con el código: " + exitCode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            
}
