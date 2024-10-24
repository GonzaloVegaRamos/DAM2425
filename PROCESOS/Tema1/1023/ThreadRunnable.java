import java.util.jar.Attributes.Name;

public class ThreadRunnable implements Runnable {
    
        private Thread hilo = null;
        private Thread hiloRandom = null;

        public ThreadRunnable (String name){
            this.hilo = new Thread(this,name);
            this.hiloRandom = new Thread(this,name);
        }



        public void run(){
            for(int i= 100;i==0;i--){

                System.out.println(this.hiloRandom.getName()+ i);
                
            }
        }
    
        public void runContador(){
            for(int i= 0;i<=100;i++){

                System.out.println(this.hilo.getName()+ i);
                
            }
        }
    

        public static void main(String [] args){

        }
    
    
}
