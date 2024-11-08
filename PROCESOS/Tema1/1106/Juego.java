public class Juego {
    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();

        // Crear tanques
        Tank tank1 = new Tank("Trump", battlefield);
        Tank tank2 = new Tank("Kim Jong-un", battlefield);
        Tank tank3 = new Tank("Vladimir Putin", battlefield);

        // Añadir tanques al campo de batalla
        battlefield.addTank(tank1);
        battlefield.addTank(tank2);
        battlefield.addTank(tank3);

        // Iniciar hilos
        Thread t1 = new Thread(tank1);
        Thread t2 = new Thread(tank2);
        Thread t3 = new Thread(tank3);

        t1.start();
        t2.start();
        t3.start();

        // Esperar a que termine el juego
        while (battlefield.isGameRunning()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}