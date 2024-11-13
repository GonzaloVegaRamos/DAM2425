import kotlin.random.Random

fun main() {
    // Generamos un número aleatorio entre 1 y 100
    val numeroSecreto = Random.nextInt(1, 101)
    var intento: Int? = null

    println("¡Bienvenido al juego de adivinanza!")
    println("He escogido un número entre 1 y 100. Intenta adivinarlo.")

    do {
        print("Introduce tu intento: ")
        intento = readLine()?.toIntOrNull()

        // Verificamos que el intento sea un número válido
        if (intento != null) {
            when {
                intento < numeroSecreto -> println("El número es mayor.")
                intento > numeroSecreto -> println("El número es menor.")
                else -> println("¡Felicidades! Has adivinado el número $numeroSecreto.")
            }
        } else {
            println("Por favor, introduce un número válido.")
        }
    } while (intento != numeroSecreto) // Repetimos hasta que el usuario adivine
}