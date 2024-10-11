import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessCommunicationWindowsExample {

    public static void main(String[] args) {
        try {
            // Crear el primer proceso (Productor) que lista los archivos del directorio actual
            ProcessBuilder producer = new ProcessBuilder("cmd.exe", "/c", "dir");

            // Crear el segundo proceso (Consumidor) que busca una palabra en la salida
            ProcessBuilder consumer = new ProcessBuilder("cmd.exe", "/c", "findstr", "Program");

            // Iniciar el proceso productor (dir)
            Process producerProcess = producer.start();

            // Leer la salida del primer proceso

            // Redirigir la salida del primer proceso como entrada para el segundo proceso
            

            //consumer.redirectInput(producerProcess.getInputStream());

            // Iniciar el proceso consumidor (findstr)
            Process consumerProcess = consumer.start();

            // Leer la salida del proceso consumidor
            BufferedReader consumerOutput = new BufferedReader(new InputStreamReader(consumerProcess.getInputStream()));
            String line;
            while ((line = consumerOutput.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que los procesos terminen
            producerProcess.waitFor();
            consumerProcess.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}