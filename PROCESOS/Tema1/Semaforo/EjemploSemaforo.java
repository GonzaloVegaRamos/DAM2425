import java.util.concurrent.Semaphore;

public class EjemploSemaforo {
    private static final Semaphore semaforo = new Semaphore(2); // permite máximo 3 hilos

    static class Tarea implements Runnable {
        private final String nombre;

        public Tarea(String nombre) {
            this.nombre = nombre;
        }
        

        @Override
        public void run() {
            
            try {
                int contador=0;
                Thread.sleep((long) Math.random() * 10000000);
                contador++;

                System.out.println(nombre + " está esperando..."+"("+contador+")");
                
                semaforo.acquire();

                System.out.println(nombre + " accedió al recurso");

                // Simula el uso del recurso

            } catch (InterruptedException e) {

                e.printStackTrace();

            } finally {

                System.out.println(nombre + " liberó el recurso");

                semaforo.release();

            }
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            new Thread(new Tarea("Hilo " + i)).start();
        }
    }
}
