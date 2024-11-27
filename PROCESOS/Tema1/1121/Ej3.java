// Simula una carrera de tres hilos que avanzan en pasos aleatorios.
// Cada hilo representa un corredor y debe imprimir su progreso en cada paso. El primer hilo en llegar a 50 pasos (o alguna meta definida) "gana".

public class Ej3 extends Thread{

    public Ej3 (String name){
        super(name);
     }

    public void run(){

       While(1<50);{
            try{
               Thread.sleep(100);
               int n = Math.random(10);
               i +=n;
            }catch(InterruptedException e){
               System.out.println("algo ha fallado");
                e.printStackTrace();
            }
                System.out.println(getName()+(i+1));
                
        }
    }

    public static void main(String [] args){
        Ej3 minihilo1 = new Ej3("(1)");
        Ej3 minihilo2 = new Ej3("(2)");
        Ej3 minihilo3 = new Ej3("(3)");

        
        minihilo1.start();
        minihilo2.start();
        minihilo3.start();
        
   
    }
}
