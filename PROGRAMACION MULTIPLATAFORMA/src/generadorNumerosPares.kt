fun main() {
    print("Introduce un número: ")
    val numero = readLine()?.toIntOrNull()

    // Validamos que el número ingresado sea válido
    if (numero != null && numero >= 0) {
        var i = 0

        println("Números pares desde 0 hasta $numero:")

        // Bucle while para mostrar los números pares
        while (i <= numero) {
            println(i)
            i += 2 // Incrementa de 2 en 2 para obtener los números pares
        }
    } else {
        println("Por favor, introduce un número válido mayor o igual a 0.")
    }
}
