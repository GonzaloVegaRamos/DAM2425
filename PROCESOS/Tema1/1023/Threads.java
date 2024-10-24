public class Threads extends Thread{
    public Threads (String name){
        super(name);
    }

    public void run(){
        for(int i= 0;i<=100;i++){
            
            try{
                sleep(50);
            }catch(InterruptedException e){
                
                e.printStackTrace();
            }
            if(i==10){
                System.out.println(this.getName()+ " Quedo primero");
            }
        }
    }

    public static void main(String [] args){
        Threads minihilo1 = new Threads("(1)");
        Threads minihilo2 = new Threads("(2)");

        minihilo1.setPriority(Thread.MIN_PRIORITY);
        minihilo2.setPriority(Thread.MAX_PRIORITY);
        minihilo1.start();
        minihilo2.start();
    }
}
