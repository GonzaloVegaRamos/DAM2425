// Crea un programa en el que un hilo imprima "Hola desde un Hilo" en la consola cinco veces. 
// Usa Thread.sleep(1000) para que el hilo espere un segundo entre cada mensaje.

public class Ej1 extends Thread{
    
    public void run(){
        for(int i= 0;i<5;i++){
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
                System.out.println((i+1)+") Hola desde el Hilo");
        }
    }

    public static void main(String [] args){
        Ej1 minihilo = new Ej1();
        minihilo.start();
   
    }
}