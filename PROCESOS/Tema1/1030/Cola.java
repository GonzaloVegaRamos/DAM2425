public class Cola {
    private int[] buffer;
    private int capacidad;
    private int count = 0;
    private int in = 0;
    private int out = 0;

    public Cola(int capacidad) {
        this.capacidad = capacidad;
        buffer = new int[capacidad];
    }

    public synchronized void put(int valor) throws InterruptedException {
        while (count == capacidad) {
            wait();
        }
        buffer[in] = valor;
        in = (in + 1) % capacidad;
        count++;
        notifyAll();
    }

    public synchronized int take() throws InterruptedException {
        while (count == 0) {
            wait();
        }
        int valor = buffer[out];
        out = (out + 1) % capacidad;
        count--;
        notifyAll();
        return valor;
    }
}