// la jefa de proyecto, es crear un programa que importe una seria de registros
// almacenados en un fichero de texto en formato CSV denominado alumnos.csv. Un
// extracto del contenido es el siguiente:

// · Angela Veron,20,DAM,7.1223
// . Silvia Climent,24,DAM,5.5
// . Jorge Costa,22,DAM,9

// El contenido es información de alumnos: nombre, edad, siclo que cursa y nota media
// actual. Se pide codificar un programa que realice las siguientes acciones:

// 1. Crear una clase Alumnos, contenedora de la información que se solicita.
// Contendrá los métodos habituales: constuctores, getters y setters y toString.
// 2. Importar: leer la información del fichero, guardándola en memoria. Para ello,
// creara una clase que almacenara la información.
// 3. Imprimir todos los alumnos.
// 4. Buscar aquel alumno con mayor nota y mostrarlo por pantalla.
// 5. Crear un método que devuelva una representación de cada alumno en formato
// CSV.
// 6. Crear una funcion que genere un fichero de texto AlumnosAprobados.csv, con la
// misma estructura que el archivo inicial, pero solo con los alumnos aprobados.
// 7. Puedes crear los metodos y funciones auxiliares que consideres.

import java.io.*;
import java.util.*;

class Alumnos {
    public String nombre;
    public int edad;
    public String ciclo;
    public double nota;

    public Alumnos(String nombre, int edad, String ciclo, double nota) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciclo = ciclo;
        this.nota = nota;
    }

    @Override
    public String toString() {
        return String.join(",", nombre, String.valueOf(edad), ciclo, String.valueOf(nota));
    }

    public static Alumnos fromString(String str) {
        String[] parts = str.split(",");
        return new Alumnos(parts[0], Integer.parseInt(parts[1]), parts[2], Double.parseDouble(parts[3]));
    }
    public String toCSV() {
        return String.join(",", nombre, String.valueOf(edad), ciclo, String.valueOf(nota));
    }
}

public class ImportarRegistros {
    private static final String ARCHIVO_APROBADOS = "AlumnosAprobados.csv";
    private static List<Alumnos> listaAlumnos = new ArrayList<>();

    public static void main(String[] args) {
        cargarAlumnos();
        listarAlumnos();
        mayorNota();
        alumnosCsv();
        guardarAlumnosAprobados();
    }

    private static void cargarAlumnos() {
        String nombreFichero = "alumnos.csv"; 
        File file = new File(nombreFichero);
       
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    listaAlumnos.add(Alumnos.fromString(line));
                }
            } catch (IOException e) {
                System.out.println("Error al cargar los alumnos: " + e.getMessage());
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }

    private static void listarAlumnos(){
        if (listaAlumnos.isEmpty()) {
            System.out.println("No hay alumnos disponibles");
            return;
        }
        System.out.printf("%-4s %-15s %-8s %-12s %-5s%n", "  ", "NOMBRE", "EDAD", "CICLO", "NOTA");
        for (int i = 0; i < listaAlumnos.size(); i++) {
            Alumnos alumno = listaAlumnos.get(i);
            System.out.printf("%-4d %-15s %-8d %-12s %-5.2f%n", 
                              (i + 1), alumno.nombre, alumno.edad, alumno.ciclo, alumno.nota);
        }
    }

    private static void mayorNota(){
        System.out.println("Alumno con mayor nota: ");
        Collections.sort(listaAlumnos, Comparator.comparingDouble((Alumnos a) -> a.nota).reversed());
        Alumnos alumno = listaAlumnos.get(0);
        System.out.println(alumno.nombre + "   " + alumno.nota);

    }
    private static void alumnosCsv(){
            for (Alumnos alumno : listaAlumnos) {
            System.out.println(alumno.toCSV());
        }
    }

    private static void guardarAlumnosAprobados() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO_APROBADOS))) {
            for (Alumnos alumno : listaAlumnos) {
                if (alumno.nota >= 5.0) {
                    writer.write(alumno.toCSV());
                    writer.newLine();
                }
            }
            System.out.println("Alumnos aprobados guardados en " + ARCHIVO_APROBADOS);
        } catch (IOException e) {
            System.out.println("Error al guardar los alumnos aprobados: " + e.getMessage());
        }
    }
}
