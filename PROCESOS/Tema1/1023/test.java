public class test {
    public test(){

    }
    
    public static void main(String [] args){

        ThreadRunnable minihilo1 = new ThreadRunnable("(1)");
        ThreadRunnable minihilo2 = new ThreadRunnable("(2)");

        minihilo2.run();
        minihilo1.runContador();

        // minihilo1.setPriority(Thread.MIN_PRIORITY);
        // minihilo2.setPriority(Thread.MAX_PRIORITY);
        // minihilo1.start();
        // minihilo2.start();
    }
}
