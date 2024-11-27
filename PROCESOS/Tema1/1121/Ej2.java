public class Ej2 extends Thread {

    public Ej2(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) { 
            try {
                switch (this.getName()) {
                    case "(1)":
                        Thread.sleep(500);
                        break;
                    case "(2)": 
                        Thread.sleep(1000);
                        break;
                    case "(3)":
                        Thread.sleep(2000);
                        break;
                }
            } catch (InterruptedException e) {
                System.out.println(this.getName() + " ha sido interrumpido.");
                e.printStackTrace();
            }
           
            System.out.println(this.getName() + " cuenta: " + i);
        }
        System.out.println(this.getName() + " ha terminado de contar.");
    }

    public static void main(String[] args) {
        
        Ej2 hilo1 = new Ej2("(1)");
        Ej2 hilo2 = new Ej2("(2)");
        Ej2 hilo3 = new Ej2("(3)");

       
        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
