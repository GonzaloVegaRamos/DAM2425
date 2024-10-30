import java.io.File;

public class GestionDirectorios {

    public static void main(String[] args) {
        
        String rutaDirectorio = "C:\\Users\\lacer";
        boolean esRecursivo = false;

        
        if (args.length > 0) {
            if (args[0].equals("-r")) {
                esRecursivo = true;
            } else {
                
                rutaDirectorio = args[0];
                if (args.length > 1 && args[1].equals("-r")) {
                    esRecursivo = true;
                }
            }
        }

        File directorio = new File(rutaDirectorio);

        if (!directorio.exists() || !directorio.isDirectory()) {
            
            return;
        }

        listarContenido(directorio, esRecursivo, 0);
    }

    private static void listarContenido(File directorio, boolean esRecursivo, int nivel) {
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                for (int i = 0; i < nivel; i++) {
                    System.out.print("\t");
                }
                if (archivo.isDirectory()) {
                    System.out.printf("[DIRECTORIO] %s%n", archivo.getName());
                    if (esRecursivo) {
                        listarContenido(archivo, true, nivel + 1);
                    }
                } else {
                    long tamanoKB = archivo.length() / 1024;
                    System.out.printf("[FICHERO] %s - %d KB%n", archivo.getName(), tamanoKB);
                }
            }
        }
    }
}
