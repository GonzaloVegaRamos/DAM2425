fun main() {
    val inventario = mutableListOf<String>() // Lista de productos en el inventario

    while (true) {
        println("\n--- Menú de Administración de Inventario ---")
        println("1. Añadir producto")
        println("2. Eliminar producto")
        println("3. Modificar producto")
        println("4. Mostrar inventario")
        println("5. Salir")
        print("Selecciona una opción (1-5): ")

        when (readLine()?.toIntOrNull()) {
            1 -> anadirProducto(inventario)
            2 -> eliminarProducto(inventario)
            3 -> modificarProducto(inventario)
            4 -> mostrarInventario(inventario)
            5 -> {
                println("Saliendo del programa...")
                break
            }
            else -> println("Por favor, selecciona una opción válida.")
        }
    }
}

fun anadirProducto(inventario: MutableList<String>) {
    print("Introduce el nombre del producto a añadir: ")
    val producto = readLine()
    if (!producto.isNullOrEmpty()) {
        inventario.add(producto)
        println("Producto añadido: $producto")
    } else {
        println("No se puede añadir un producto vacío.")
    }
}

fun eliminarProducto(inventario: MutableList<String>) {
    mostrarInventario(inventario)
    print("Introduce el número del producto a eliminar: ")
    val indice = readLine()?.toIntOrNull()
    if (indice != null && indice in 1..inventario.size) {
        val productoEliminado = inventario.removeAt(indice - 1)
        println("Producto eliminado: $productoEliminado")
    } else {
        println("Número inválido. Por favor, selecciona un número válido.")
    }
}

fun modificarProducto(inventario: MutableList<String>) {
    mostrarInventario(inventario)
    print("Introduce el número del producto a modificar: ")
    val indice = readLine()?.toIntOrNull()
    if (indice != null && indice in 1..inventario.size) {
        print("Introduce el nuevo nombre para el producto: ")
        val nuevoProducto = readLine()
        if (!nuevoProducto.isNullOrEmpty()) {
            inventario[indice - 1] = nuevoProducto
            println("Producto modificado: $nuevoProducto")
        } else {
            println("El nombre del producto no puede estar vacío.")
        }
    } else {
        println("Número inválido. Por favor, selecciona un número válido.")
    }
}

fun mostrarInventario(inventario: List<String>) {
    if (inventario.isEmpty()) {
        println("El inventario está vacío.")
    } else {
        println("\n--- Inventario Actual ---")
        inventario.forEachIndexed { index, producto ->
            println("${index + 1}. $producto")
        }
    }
}