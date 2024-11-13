fun main() {
    print("Introduce tu edad: ")
    val edad = readLine()?.toIntOrNull()

    // Validamos que la edad no sea nula y aplicamos la clasificación
    val categoria = if (edad != null) {
        when {
            edad in 0..12 -> "Infantil"
            edad in 13..17 -> "Adolescente"
            edad in 18..64 -> "Adulto"
            edad >= 65 -> "Mayor"
            else -> "Edad no válida"
        }
    } else {
        "Por favor, introduce un número válido."
    }

    // Mostramos la categoría correspondiente
    println("Grupo de edad: $categoria")
}
