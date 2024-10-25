public class mensajeThread extends Thread{
    
    public mensajeThread (String name){
        super(name);
    }

    public void run(){
        for(int i= 0;i<=5;i++){
            try{
                Thread.sleep(10);

            }catch(InterruptedException e){
                e.printStackTrace();
            }
                System.out.println(i+")"+"Este es el hilo" +this.getName());
        }
    }

    public static void main(String [] args){
        mensajeThread minihilo1 = new mensajeThread("(1)");
        mensajeThread minihilo2 = new mensajeThread("(2)");

        minihilo1.setPriority(Thread.MIN_PRIORITY);
        minihilo2.setPriority(Thread.MAX_PRIORITY);
        minihilo1.start();
        minihilo2.start();
    }
}
