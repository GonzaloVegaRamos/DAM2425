import java.io.Serializable;

public class Alumno implements Serializable {
    private String nombre;
    private int edad;
    private String curso;
    private double promedio;

    // Constructor
    public Alumno(String nombre, int edad, String curso, double promedio) {
        this.nombre = nombre;
        this.edad = edad;
        this.curso = curso;
        this.promedio = promedio;
    }

    // Métodos get
    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getCurso() {
        return curso;
    }

    public double getPromedio() {
        return promedio;
    }


    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", Curso: " + curso + ", Promedio: " + promedio;
    }
}
