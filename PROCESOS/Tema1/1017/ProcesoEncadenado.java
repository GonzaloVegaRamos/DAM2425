
public class ProcesoEncadenado {
    public static void main(String args[]){
        ProcessBuilder pb = new ProcessBuilder("cmd","echo hola");
        
       try {
        pb.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
