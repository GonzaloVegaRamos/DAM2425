
// Siguiendo en el proyecto que nos han encargado, nos piden que generemos una
// concatenación de ficheros, equivalente al operador >> en entornos Linux. Para ello
// crearemos una función que reciba dos nombres. El primero debe ser un fichero que
// exista. El programa realizará un << volcado>> de dicho fichero, almacenándolo << al
// final>> del segundo fichero.

// Probar la llamada con el fichero de Alumnos.dat creado en el caso práctico anterior, y
// comprobar con algun editor hexadecimal que el contenido ha sido correctamente
// copiado.


import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;


public class ConcatenarFicheros {
    private static String listadoBinario = "alumno.dat";
    private static String aCopiar = "alumnoCopia.dat";

    
    public static void main(String[] args) {
        copiaAlumnos();
        
    }




    private static void copiaAlumnos(){
        try (BufferedReader reader = new BufferedReader(new FileReader(listadoBinario))) {
            String linea;

            while ((linea = reader.readLine()) != null) {

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(aCopiar,true))) {
                    writer.write(linea);
                    writer.newLine();
                    System.out.println("Alumno Copiado: "+linea);
                   
                } catch (IOException e) {
                    System.err.println("Error al guardar los datos: " + e.getMessage());
                }
            }
            System.out.println("Todos los alumnos copiados!");
            
        } catch (IOException e) {
            System.err.println("Error al leer los datos: " + e.getMessage());
        }

    }
}
