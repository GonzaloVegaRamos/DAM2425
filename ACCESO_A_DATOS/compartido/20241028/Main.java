import java.io.*;
import java.util.Scanner;

public class Main {

    // Método para guardar el objeto Alumno en un archivo binario
    public static void guardarAlumno(Alumno alumno, String datosalumnos) {
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(datosalumnos))) {
            escritor.writeObject(alumno);
            System.out.println("Datos del alumno guardados en " + datosalumnos);
        } catch (IOException e) {
            System.out.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    // Método para leer el objeto Alumno desde un archivo binario
    public static void leerAlumno(String datosalumnos) {
        try (ObjectInputStream lector = new ObjectInputStream(new FileInputStream(datosalumnos))) {
            Alumno alumno = (Alumno) lector.readObject();
            System.out.println("Datos del alumno leídos desde el archivo:");
            System.out.println(alumno);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer los datos: " + e.getMessage());
        }
    }

    // Método para escribir los datos del alumno en un archivo de texto sin sobreescribir
    public static void escribirAlumnoEnArchivoTexto(Alumno alumno, String archivoTexto) {
        try (FileWriter escritor = new FileWriter(archivoTexto, true)) { // true para modo append
            escritor.write("Nombre: " + alumno.getNombre() + "\n");
            escritor.write("Edad: " + alumno.getEdad() + "\n");
            escritor.write("Curso: " + alumno.getCurso() + "\n");
            escritor.write("Promedio: " + alumno.getPromedio() + "\n");
            escritor.write("-----------------------------\n");
            System.out.println("Datos del alumno añadidos a " + archivoTexto);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de texto: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Solicitar datos del usuario
        System.out.print("Ingrese el nombre del alumno: ");
        String nombre = sc.nextLine();
        
        System.out.print("Ingrese la edad del alumno: ");
        int edad = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea

        System.out.print("Ingrese el curso del alumno: ");
        String curso = sc.nextLine();

        System.out.print("Ingrese el promedio del alumno: ");
        double promedio = sc.nextDouble();

        // Crear el objeto Alumno
        Alumno alumno = new Alumno(nombre, edad, curso, promedio);

        // Guardar en archivo binario y luego leer el objeto
        String datosalumnos = "Alumno.dat";
        guardarAlumno(alumno, datosalumnos);
        leerAlumno(datosalumnos);

        // Guardar en archivo de texto sin sobrescribir
        String archivoTexto = "archivo1.txt";
        escribirAlumnoEnArchivoTexto(alumno, archivoTexto);

        sc.close();
    }
}
