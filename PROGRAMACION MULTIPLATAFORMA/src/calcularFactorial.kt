fun main() {
    print("Introduce un número entero no negativo: ")
    val numero = readLine()?.toIntOrNull()

    // Verificamos que el número sea válido y no negativo
    if (numero != null && numero >= 0) {
        val resultado = calcularFactorial(numero)
        println("El factorial de $numero es: $resultado")
    } else {
        println("Por favor, introduce un número entero no negativo válido.")
    }
}

// Función para calcular el factorial de un número
fun calcularFactorial(n: Int): Long {
    var factorial = 1L // Usamos Long para manejar números grandes
    for (i in 1..n) {
        factorial *= i
    }
    return factorial
}
