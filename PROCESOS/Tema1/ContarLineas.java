import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ResourceBundle.Control;

public class ContarLineas {

    public static void main(String[] args){
        ContarLineas c = new ContarLineas();
        c.lanzar();
    }

    public contador(){

    }
    
    public void lanzar(){
        ProcessBuilder pb = new ProcessBuilder("cmd", "find \\c \\v", "archivo.java");
        
        
        try{
        
        Process proceso = pb.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader( proceso.getInputStream()));   
        String linea = reader.readLine();
        while(linea == ""){
            System.out.println("a");
        }     
        System.out.println(reader.readLine());

        }catch(Exception e){
            System.out.println(e);
        
        }
        

    }
    
}
