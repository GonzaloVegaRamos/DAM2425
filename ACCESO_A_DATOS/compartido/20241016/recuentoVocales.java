import java.io.*;

public class recuentoVocales {
    public static void main(String[] args) throws IOException {
        File fichero = new File("ej1.txt");
        char[] vocales = {  'a', 'e', 'i', 'o', 'u', 
                            'A', 'E', 'I', 'O', 'U', 
                            'á', 'é', 'í', 'ó', 'ú', 
                            'Á', 'É', 'Í', 'Ó', 'Ú' };                 

        int contadorVocales = 0;

        if (fichero.exists()) { //Comprobar si el fichero existe
            if (fichero.isFile()) {
                System.out.println(("El archivo es: " + fichero));
                System.out.println("El fichero tiene: " + fichero.length() + " caracteres");

                FileReader fReader = new FileReader(fichero);

                int caracter;

                while ((caracter = fReader.read()) != -1) { //Recorre el fichero y recoge los caract

                    for(int i = 0; i < vocales.length; i++){ //Recorre el array 
                        if(caracter == vocales[i]){ //Compara el espacio del arrai ocupado en el indice con los caracteres escritos
                            contadorVocales++;
                        }
                    }
                }
                 fReader.close();
                System.out.println("El fichero contiene: " + contadorVocales + " vocales");
            }
        } else {
            fichero.createNewFile(); //Crea nuevo fichero si no existe
            System.err.println("Se ha creado " + fichero);
        }
    }
}