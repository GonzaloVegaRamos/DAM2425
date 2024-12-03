package com.example.ProductorConsumidor;

public class Consumidor implements Runnable {
    private Cola cola;  // Referencia a la cola compartida

    public Consumidor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int valor = cola.take();  // Toma un valor de la cola
                System.out.println("Consumidor consumió: " + valor);
                Thread.sleep(1000);  // Simula el tiempo de consumo
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Maneja la interrupción
        }
    }
}
