import java.io.File;

public class PropiedadesDirectorio {

    public static void main(String[] args) {
       
        String rutaDirectorio = "C:\\Users\\lacer";

        
        if (args.length > 0) {
            rutaDirectorio = args[0];
        }

        File directorio = new File(rutaDirectorio);

        if (!directorio.exists() || !directorio.isDirectory()) {
            System.out.println("La ruta especificada no existe o no es un directorio válido.");
            return;
        }

        
        recopilarPropiedades(directorio);
    }

    private static void recopilarPropiedades(File directorio) {
        long tamanoTotal = 0;
        int cantidadArchivos = 0;
        int cantidadDirectorios = 0;

       
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    tamanoTotal += archivo.length();
                    cantidadArchivos++;
                } else if (archivo.isDirectory()) {
                    cantidadDirectorios++;
                }
            }
        }

        
        System.out.println("Propiedades del directorio:");
        System.out.println("Ruta: " + directorio.getAbsolutePath());
        System.out.println("Total de archivos: " + cantidadArchivos);
        System.out.println("Total de directorios: " + cantidadDirectorios);
        System.out.printf("Tamaño total: %.2f KB%n", tamanoTotal / 1024.0);
    }
}
