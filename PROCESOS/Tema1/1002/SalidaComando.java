import java.io.*;

public class SalidaComando {
  
    public static void main(String[] args) {
        try {
            // Crear un ProcessBuilder para el comando 'sort'
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "sort");
            Process process = pb.start();

            // Obtener el OutputStream del proceso para enviarle datos
            OutputStream outputStream = process.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

            // Enviar líneas de texto al proceso 'sort'
            bufferedWriter.write("naranja");
            bufferedWriter.newLine();
            bufferedWriter.write("manzana");
            bufferedWriter.newLine();
            bufferedWriter.write("banana");
            bufferedWriter.newLine();
            bufferedWriter.write("uva");
            bufferedWriter.newLine();

            // Cerrar el BufferedWriter para indicar que no se enviarán más datos
            bufferedWriter.close();

            // Leer la salida ordenada del proceso
            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "CP850"); // Codificación para Windows
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            System.out.println("Salida ordenada del comando 'sort':");
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine y obtener el código de salida
            int exitCode = process.waitFor();
            System.out.println("El proceso terminó con el código: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

