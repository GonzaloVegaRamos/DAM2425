// Estamos en un proyecto con otros companeros, y tras un briefing de partida,
// acordamos que tenemos que guardar datos de una clase que disponemos, Alumno, en
// fichero binario. Tenemos que decidir si guardamos los datos de dicha clase por partes
// (por atributos) o en bloque.

// Escribe un programa que pida al usuario datos de un Alumno, para posteriormente
// guardarlo en un fichero binario Alumno.dat. Una vez guardado y cerrado, invoca una
// funcion para que lo lea y lo muestre por pantalla.

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class guardarAlumno {
    private static String listadoBinario = "alumno.dat";
    

    
    public static void main(String[] args) {
        introducirAlumno();
        
    }

    private static void introducirAlumno() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Introduzca el nombre del alumno: ");
            String nombre = scanner.nextLine();
            System.out.println("Introduzca su edad:");
            int edad = scanner.nextInt();
            guardarAlumno(nombre, edad);
        }
    }


    private static void guardarAlumno(String nombre, int edad) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(listadoBinario,true))) {
            writer.write(nombre + " " + edad + "\n");
            System.out.println("Alumno Guardado");
            leerAlumnos(nombre,edad);
           
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }
    private static void leerAlumnos(String nombre, int edad) throws IOException {
        System.out.println("Alumnos guardados: ");
        try (BufferedReader reader = new BufferedReader(new FileReader(listadoBinario))) {
            String linea;
            // Leer el archivo línea por línea
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea); // Imprimir la línea leída
            }
            System.out.println(nombre+" "+edad);
        } catch (IOException e) {
            System.err.println("Error al leer los datos: " + e.getMessage());
        }
    }
    
}
