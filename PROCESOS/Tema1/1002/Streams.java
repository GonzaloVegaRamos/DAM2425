import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;

public class Streams {

    public static void main(String[] args) {
        
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        String ruta = "";

        try {
            System.out.println("Nombra el fichero que quieres leer");
            ruta = reader.readLine();
        } catch (Exception e) {
            
        }


        try (BufferedReader br = new BufferedReader(new FileReader(ruta + ".txt"))) {
            String linea;
           
            while ((linea = br.readLine()) != null) {
                System.out.println(linea); 
            }
        } catch (Exception e) {
            
            e.printStackTrace();
        }

    }

}



