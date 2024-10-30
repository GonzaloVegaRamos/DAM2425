public class Consumidor implements Runnable {
    private Cola cola;

    public Consumidor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000);
                int valor = cola.take();
                System.out.println("Consumidor consumió: " + valor);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
