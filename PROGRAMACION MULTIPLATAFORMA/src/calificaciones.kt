fun main() {
    print("Introduce una calificación entre 0 y 10: ")
    val calificacion = readLine()?.toDoubleOrNull()

    // Verificamos si la calificación está en el rango válido
    val resultado = if (calificacion != null && calificacion in 0.0..10.0) {
        when (calificacion) {
            in 0.0..4.9 -> "Suspenso"
            in 5.0..6.9 -> "Aprobado"
            in 7.0..8.9 -> "Notable"
            in 9.0..10.0 -> "Sobresaliente"
            else -> "Calificación no válida"  // Caso que no debería ocurrir debido a la validación previa
        }
    } else {
        "Por favor, introduce una calificación válida entre 0 y 10."
    }

    // Muestra el resultado
    println("Resultado: $resultado")
}