public class Contador100 {
    

        private static final int LIMITE = 100;
        private int contador = 0;
    
        public static void main(String[] args) {
            Contador100 recurso = new Contador100();
    
            // Crear tres hilos
            Thread hilo1 = new Thread(new TareaContador(recurso, "Hilo 1"));
            Thread hilo2 = new Thread(new TareaContador(recurso, "Hilo 2"));
            Thread hilo3 = new Thread(new TareaContador(recurso, "Hilo 3"));
    
            // Iniciar los hilos
            hilo1.start();
            hilo2.start();
            hilo3.start();
        }
    
        // Método sincronizado para incrementar el contador
        public synchronized void incrementar(String nombreHilo) {
            if (contador < LIMITE) {
                contador++;
                System.out.println(nombreHilo + " incrementó el contador a: " + contador);
            }
        }
    
        // Método para verificar si el contador alcanzó el límite
        public synchronized boolean haTerminado() {
            return contador >= LIMITE;
        }
    }
    
    // Clase que representa la tarea de cada hilo
    class TareaContador implements Runnable {
        private final Contador100 recurso;
        private final String nombreHilo;
    
        public TareaContador(Contador100 recurso, String nombreHilo) {
            this.recurso = recurso;
            this.nombreHilo = nombreHilo;
        }
    
        @Override
        public void run() {
            while (!recurso.haTerminado()) {
                try {
                    recurso.incrementar(nombreHilo);
                    Thread.sleep((long) (Math.random() * 100)); // Pausa aleatoria
                } catch (InterruptedException e) {
                    System.out.println(nombreHilo + " fue interrumpido.");
                }
            }
        }
    }
    

