import java.util.Scanner
import kotlin.system.exitProcess

class Calculadora {

    val sc = Scanner(System.`in`)

    fun suma(num1: Double, num2: Double) {
        var sum = num1 + num2
        println(sum)
    }

    fun restar(num1: Double, num2: Double) {
        var res = num1 - num2
        println(res)
    }

    fun multiplicar(num1: Double, num2: Double) {
        var mul = num1 * num2
        println(mul)
    }

    fun dividir(num1: Double, num2: Double) {
        var div = num1 / num2
        println(div)
    }

    fun option(op: Int) {
        if (op != 5) {
            print("Pon el primer numero: ")
            val num1 = sc.nextDouble()
            print("Pon el segundo numero: ")
            val num2 = sc.nextDouble()

            when {
                op == 1 -> {
                    suma(num1, num2)
                };
                op == 2 -> {
                    restar(num1, num2)
                }

                op == 3 -> {
                    multiplicar(num1, num2)
                }

                op == 4 -> {
                    dividir(num1, num2)
                }
            }
        } else {
            exitProcess(0)
        }
    }

    fun menu() {
        print("¿Que quieres hacer?\n" +
                "1. Sumar\n" +
                "2. Restar\n" +
                "3. Multijugador\n" +
                "4. Dividir\n" +
                "5. Salir\n" +
                "(1, 2, 3, 4, 5): ")

        var op = sc.nextInt();

        option(op)
    }
}

fun main() {
    while (true) {
        val calc = Calculadora()
        calc.menu()
    }
}