import java.io.File;
import java.util.Scanner;;

public class ejercico{

 public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("Introduce la ruta del fichero: ");
    String rutaFichero = in.next();

    File f = new File(rutaFichero);
    if (f.exists()) {
        String[] archivosList = f.list();
        System.out.println("El directorio " + rutaFichero + " contiene:");
        for (String archivo : archivosList) {
            File fileAux = new File(rutaFichero+"\\"+archivo);
            if (fileAux.isFile()) {
                System.out.println("\t fichero: " + archivo + " " + archivo.length() + " KB");
            } else if (fileAux.isDirectory()) {
                System.out.println("\t directorio: " + archivo + " " + archivo.length() + " KB");
            } 
        }

    } else {
        System.out.println("El archivo o ruta no existe");
        System.exit(1);
    }
} 
}
