import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class EjecutarPython {
    public static void main(String[] args) {
        
String scriptPython = "PROCESOS/Tema1/1017/Random.py"; // Cambia esto si es necesario

// Crear ProcessBuilder para ejecutar el script de Python
          ProcessBuilder pb = new ProcessBuilder("python", scriptPython);
        try {
            Process proceso = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
            String numeroGenerado = reader.readLine();
            int numeroSecreto = Integer.parseInt(numeroGenerado);

            Scanner scanner = new Scanner(System.in);
            int intento = 0;
            System.out.println("Adivina el número entre 1 y 100:");

            while (intento != numeroSecreto) {
                intento = scanner.nextInt();
                if (intento < numeroSecreto) {
                    System.out.println("El número es mayor." + "( Era el "+numeroSecreto+")");
                } else if (intento > numeroSecreto) {
                    System.out.println("El número es menor."+ "(Era el "+numeroSecreto+")");
                
                } else {
                    System.out.println("¡Felicidades! Has adivinado el número.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

