import java.io.*;
import java.util.*;

class Producto {
    public String nombre;
    public double precio;
    public int cantidad;
    public String descripcion;

    public Producto(String nombre, double precio, int cantidad, String descripcion) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return String.join(";", nombre, String.valueOf(precio), String.valueOf(cantidad), descripcion);
    }

    public static Producto fromString(String str) {
        String[] parts = str.split(";");
        return new Producto(parts[0], Double.parseDouble(parts[1]), Integer.parseInt(parts[2]), parts[3]);
    }
}

public class EjercicioCRUD {
    private static final String ARCHIVO = "productos.txt"; // Ruta del archivo donde se guardan los productos
    private static List<Producto> listaProductos = new ArrayList<>(); // Lista que almacena los productos

    public static void main(String[] args) {
        cargarProductos(); // Carga los productos desde un archivo al iniciar la aplicación
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Menú principal para la gestión de productos
            System.out.println("\nGestión de Productos\n"
                    + "1. Añadir producto\n"
                    + "2. Modificar producto\n"
                    + "3. Eliminar producto\n"
                    + "4. Listar productos\n"
                    + "5. Limpiar todos los datos\n"
                    + "6. Guardar y Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();  

            switch (opcion) {
                case 1:
                    añadirProducto(scanner); // Llama al método para añadir un nuevo producto
                    break;
                case 2:
                    modificarProducto(scanner); // Llama al método para modificar un producto existente
                    break;
                case 3:
                    eliminarProducto(scanner); // Llama al método para eliminar un producto
                    break;
                case 4:
                    listarProductos(); // Llama al método para listar todos los productos
                    break;
                case 5:
                    eliminarTodosLosProductos(); // Llama al método para limpiar todos los datos
                    break;
                case 6:
                    guardarProductos(); // Llama al método para guardar los productos en un archivo
                    System.out.println("Productos guardados y salida exitosa.");
                    return;
                default:
                    System.out.println("Opción no válida."); // Mensaje de error para opción inválida
                    break;
            }
        }
    }

    private static void añadirProducto(Scanner scanner) {
        System.out.println("Añadir un nuevo producto");
        Producto producto = new Producto(
                leerDato(scanner, "Nombre: "),
                Double.parseDouble(leerDato(scanner, "Precio: ")),
                Integer.parseInt(leerDato(scanner, "Cantidad: ")),
                leerDato(scanner, "Descripción: ")
        );
        listaProductos.add(producto); // Añade el producto a la lista
        System.out.println("Producto añadido correctamente.");
    }

    private static void modificarProducto(Scanner scanner) {
        listarProductos(); // Lista los productos para que el usuario elija
        int index = Integer.parseInt(leerDato(scanner, "Indique el número del producto a modificar: ")) - 1;

        if (index >= 0 && index < listaProductos.size()) {
            Producto producto = listaProductos.get(index); // Obtiene el producto a modificar
            System.out.println("Modificar producto: " + producto.nombre);

            producto.nombre = leerDatoConActual(scanner, "Nuevo nombre", producto.nombre); // Modifica el nombre
            producto.precio = Double.parseDouble(leerDatoConActual(scanner, "Nuevo precio", String.valueOf(producto.precio))); // Modifica el precio
            producto.cantidad = Integer.parseInt(leerDatoConActual(scanner, "Nueva cantidad", String.valueOf(producto.cantidad))); // Modifica la cantidad
            producto.descripcion = leerDatoConActual(scanner, "Nueva descripción", producto.descripcion); // Modifica la descripción

            System.out.println("Producto modificado correctamente.");
        } else {
            System.out.println("Producto no encontrado."); // Mensaje de error si el índice es inválido
        }
    }

    private static void eliminarProducto(Scanner scanner) {
        listarProductos(); // Lista los productos para que el usuario elija
        int index = Integer.parseInt(leerDato(scanner, "Indique el número del producto a eliminar: ")) - 1;

        if (index >= 0 && index < listaProductos.size()) {
            listaProductos.remove(index); // Elimina el producto de la lista
            System.out.println("Producto eliminado");
        } else {
            System.out.println("Producto no encontrado"); // Mensaje de error si el índice es inválido
        }
    }

    private static void listarProductos() {
        if (listaProductos.isEmpty()) {
            System.out.println("No hay productos disponibles"); // Mensaje si no hay productos en la lista
            return;
        }

        for (int i = 0; i < listaProductos.size(); i++) {
            Producto p = listaProductos.get(i); // Obtiene el producto en el índice actual
            System.out.printf("%d. %s - %.2f€ - Cantidad: %d\n   Descripción: %s\n",
                    (i + 1), p.nombre, p.precio, p.cantidad, p.descripcion);
        }
    }

    private static void guardarProductos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Producto p : listaProductos) {
                writer.write(p.toString()); // Escribe cada producto en el archivo
                writer.newLine(); // Salto de línea después de cada producto
            }
        } catch (IOException e) {
            System.out.println(e.getMessage()); // Mensaje de error en caso de fallo al guardar
        }
    }

    private static void cargarProductos() {
        File file = new File(ARCHIVO); // Crea un objeto File con la ruta del archivo
        if (file.exists()) { // Comprueba si el archivo existe
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    listaProductos.add(Producto.fromString(line)); // Añade cada producto del archivo a la lista
                }
            } catch (IOException e) {
                System.out.println(e.getMessage()); // Mensaje de error en caso de fallo al cargar
            }
        }
    }

    // Método para eliminar todos los productos
    private static void eliminarTodosLosProductos() {
        listaProductos.clear(); // Limpiar la lista
        System.out.println("Todos los productos han sido eliminados."); // Mensaje de confirmación
    }

    // Métodos auxiliares
    private static String leerDato(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine(); // Lee un dato del usuario
    }

    private static String leerDatoConActual(Scanner scanner, String mensaje, String valorActual) {
        System.out.printf("%s (actual: %s): ", mensaje, valorActual); // Muestra el valor actual
        String nuevoValor = scanner.nextLine(); // Lee el nuevo valor
        return nuevoValor.isEmpty() ? valorActual : nuevoValor; // Devuelve el nuevo valor o el actual si está vacío
    }
}
