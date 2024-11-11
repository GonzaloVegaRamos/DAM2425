import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class alumno {
    // Atributos de la clase
    private String nombre;
    private int edad;
    private String ciclo;
    private double notaMedia;

    // Constructor
    public alumno(String nombre, int edad, String ciclo, double notaMedia) {
        this.nombre = nombre;
        this.edad = edad;
        this.ciclo = ciclo;
        this.notaMedia = notaMedia;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCiclo() {
        return ciclo;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    // Método toString
    @Override
    public String toString() {
        return "Nombre: " + nombre + 
               ", Años: " + edad + 
               ", Ciclo: " + ciclo + 
               ", Nota media: " + String.format("%.2f", notaMedia);
    }

    // Método principal para prueba
    public static void main(String[] args) {
        String csvFile = "alumnos.csv";
        String line;
        String csvSplitBy = ",";

        ArrayList<alumno> listaAlumnos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] datos = line.split(csvSplitBy);
                String nombre = datos[0];
                int edad = Integer.parseInt(datos[1]);
                String curso = datos[2];
                double nota = Double.parseDouble(datos[3]);
                alumno alumno = new alumno(nombre, edad, curso, nota);
                listaAlumnos.add(alumno);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir la lista de alumnos usando el método toString()
        System.out.println("Lista de Alumnos:");
        for (alumno alumno : listaAlumnos) {
            System.out.println(alumno.toString());
        }

        // Comprobamos que Arraylist no esta vacía
        if (!listaAlumnos.isEmpty()) {
            alumno alumnoConMayorNota = listaAlumnos.get(0); // Suponemos que el primero tiene la mayor nota

            // Recorrer la lista para encontrar al alumno con la mayor nota
            for (alumno alumno : listaAlumnos) {
                if (alumno.getNotaMedia() > alumnoConMayorNota.getNotaMedia()) {
                    alumnoConMayorNota = alumno;
                }
            }

            // Mostrar el alumno con la mayor nota
            System.out.println("\nAlumno con la mayor nota:");
            System.out.println(alumnoConMayorNota.toString());
        } else {
            System.out.println("No hay alumnos en la lista.");
        }
    }
}
