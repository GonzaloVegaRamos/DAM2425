// Definimos la clase Estudiante
class Estudiante(val nombre: String, var nota: Double) {

    // Metodo para actualizar la nota
    fun actualizarNota(nuevaNota: Double) {
        if (nuevaNota in 0.0..10.0) {
            nota = nuevaNota
            println("Nota actualizada a $nuevaNota para $nombre.")
        } else {
            println("Error: La nota debe estar entre 0 y 10.")
        }
    }

    // Metodo para obtener la calificación en formato de texto
    fun obtenerCalificacion(): String {
        return when {
            nota < 5.0 -> "Suspenso"
            nota < 7.0 -> "Aprobado"
            nota < 9.0 -> "Notable"
            else -> "Sobresaliente"
        }
    }

    // Metodo para mostrar la información del estudiante
    fun mostrarInfo() {
        println("Nombre: $nombre | Nota: $nota | Calificación: ${obtenerCalificacion()}")
    }
}

fun main() {
    // Creamos algunos objetos de la clase Estudiante
    val estudiante1 = Estudiante("Ana", 8.5)
    val estudiante2 = Estudiante("Luis", 6.0)
    val estudiante3 = Estudiante("María", 9.7)

    // Mostramos la información de cada estudiante
    println("Información de los estudiantes:")
    estudiante1.mostrarInfo()
    estudiante2.mostrarInfo()
    estudiante3.mostrarInfo()

    // Actualizamos la nota de uno de los estudiantes
    println("\nActualizando nota de Luis...")
    estudiante2.actualizarNota(7.5)
    estudiante2.mostrarInfo() // Mostramos la información actualizada de Luis
}