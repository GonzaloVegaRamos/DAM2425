// Muchas veces, en la vida real, los problemas más complejos se solucionan con
// simples búsquedas y recuentos. En este caso, de manera simplificada, se pide la
// búsqueda y el recuento de las vocales de un fichero de texto. Para ello se pude crear un
// programa que reviva un nombre de fichero. Comprobará que dicho fichero exista para
// posteriormente contar sus vocales. Hay que considerar que las letras pueden estar
// tanto en mayusculas como en minusculas y suponer que no estan acentuadas.
// Mostrar por pantalla el resultado de la ejecución.

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ContarVocales {

    public static void main(String[] args) {
        
        String nombreFichero = "vocales.txt"; 
        File fichero = new File(nombreFichero);

        if (!fichero.exists() || !fichero.isFile()) {
            System.out.println("El fichero especificado no existe o no es un fichero válido.");
            return;
        }

        int contadorVocales = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            int caracter;
            while ((caracter = reader.read()) != -1) {
                char letra = Character.toLowerCase((char) caracter);
                if (esVocal(letra)) {
                    contadorVocales++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el fichero: " + e.getMessage());
        }

        System.out.println("El número total de vocales en el fichero es: " + contadorVocales);
    }

    private static boolean esVocal(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
