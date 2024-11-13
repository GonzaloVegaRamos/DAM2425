// Clase abstracta Cuenta con métodos abstractos para depositar y retirar
abstract class sistemaBancario(var saldo: Double) {
    abstract fun depositar(monto: Double)
    abstract fun retirar(monto: Double)

    fun mostrarSaldo() {
        println("Saldo actual: $saldo")
    }
}

// Clase CuentaAhorros que hereda de Cuenta
class CuentaAhorros(saldoInicial: Double, private val tasaInteres: Double) : sistemaBancario(saldoInicial) {
    override fun depositar(monto: Double) {
        saldo += monto
        println("Depósito de $monto en Cuenta de Ahorros. Nuevo saldo: $saldo")
    }

    override fun retirar(monto: Double) {
        if (monto <= saldo) {
            saldo -= monto
            println("Retiro de $monto en Cuenta de Ahorros. Nuevo saldo: $saldo")
        } else {
            println("Fondos insuficientes en Cuenta de Ahorros.")
        }
    }

    // Método específico para aplicar interés
    fun aplicarInteres() {
        saldo += saldo * tasaInteres
        println("Interés aplicado. Nuevo saldo: $saldo")
    }
}

// Clase CuentaCorriente que hereda de Cuenta
class CuentaCorriente(saldoInicial: Double, private val limiteCredito: Double) : sistemaBancario(saldoInicial) {
    override fun depositar(monto: Double) {
        saldo += monto
        println("Depósito de $monto en Cuenta Corriente. Nuevo saldo: $saldo")
    }

    override fun retirar(monto: Double) {
        if (monto <= saldo + limiteCredito) {
            saldo -= monto
            println("Retiro de $monto en Cuenta Corriente. Nuevo saldo: $saldo")
        } else {
            println("Fondos insuficientes en Cuenta Corriente.")
        }
    }
}

// Función de orden superior para aplicar una operación a cada cuenta en una lista
fun aplicarOperacionACuentas(cuentas: List<sistemaBancario>, operacion: (sistemaBancario) -> Unit) {
    cuentas.forEach { cuenta ->
        operacion(cuenta)
    }
}

fun main() {
    // Creamos instancias de cuentas
    val cuentaAhorros = CuentaAhorros(saldoInicial = 1000.0, tasaInteres = 0.05)
    val cuentaCorriente = CuentaCorriente(saldoInicial = 500.0, limiteCredito = 200.0)

    // Lista de cuentas
    val cuentas = listOf(cuentaAhorros, cuentaCorriente)

    // Aplicamos una operación de retiro a cada cuenta utilizando la función de orden superior
    println("\n--- Retiro de 100 en todas las cuentas ---")
    aplicarOperacionACuentas(cuentas) { cuenta ->
        cuenta.retirar(100.0)
    }

    // Aplicamos una operación de depósito a cada cuenta utilizando la función de orden superior
    println("\n--- Depósito de 50 en todas las cuentas ---")
    aplicarOperacionACuentas(cuentas) { cuenta ->
        cuenta.depositar(50.0)
    }

    // Aplicamos el metodo específico de cuenta de ahorros para aplicar el interés
    println("\n--- Aplicación de interés en la cuenta de ahorros ---")
    cuentaAhorros.aplicarInteres()

    // Mostramos el saldo final de cada cuenta
    println("\n--- Saldos finales ---")
    cuentas.forEach { it.mostrarSaldo() }
}