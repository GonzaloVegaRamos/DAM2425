package com.example.ProductorConsumidor;
public class Productor implements Runnable {
    private Cola cola;  // Referencia a la cola compartida

    public Productor(Cola cola) {
        this.cola = cola;
    }

    @Override
    public void run() {
        try {
            int valor = 0;  // Valor inicial a producir
            while (true) {
                cola.put(valor);  // Pone el valor en la cola
                System.out.println("Productor produjo: " + valor);
                valor++;  // Incrementa el valor para el siguiente ciclo
                Thread.sleep(500);  // Simula el tiempo de producción
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // Maneja la interrupción
        }
    }
}
