package clasificacionEmpleados

// Función para filtrar empleados por departamento
fun filtrarPorDepartamento(empleados: List<Empleado>, departamento: Departamento): List<Empleado> {
    return empleados.filter { it.departamento == departamento }
}

fun main() {
    // Creamos una lista de empleados
    val empleados = listOf(
        Empleado("Ana", 30, Departamento.ADMINISTRACION),
        Empleado("Luis", 25, Departamento.VENTAS),
        Empleado("María", 40, Departamento.PRODUCCION),
        Empleado("Carlos", 28, Departamento.VENTAS),
        Empleado("Laura", 35, Departamento.ADMINISTRACION)
    )

    // Mostramos los departamentos disponibles
    println("Departamentos disponibles:")
    Departamento.values().forEach { println(it) }

    // Pedimos al usuario que elija un departamento para filtrar
    print("\nIntroduce el departamento para filtrar (ADMINISTRACION, VENTAS, PRODUCCION): ")
    val input = readLine()?.uppercase()

    try {
        // Convertimos la entrada en un valor de Departamento y filtramos los empleados
        val departamentoSeleccionado = Departamento.valueOf(input!!)
        val empleadosFiltrados = filtrarPorDepartamento(empleados, departamentoSeleccionado)

        // Mostramos los empleados filtrados
        println("\nEmpleados en el departamento de $departamentoSeleccionado:")
        if (empleadosFiltrados.isEmpty()) {
            println("No hay empleados en este departamento.")
        } else {
            empleadosFiltrados.forEach { println("Nombre: ${it.nombre}, Edad: ${it.edad}") }
        }
    } catch (e: IllegalArgumentException) {
        println("Error: Departamento no válido.")
    }
}