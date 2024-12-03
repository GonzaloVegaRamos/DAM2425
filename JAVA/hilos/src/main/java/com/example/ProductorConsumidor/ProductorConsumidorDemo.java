package com.example.ProductorConsumidor;

public class ProductorConsumidorDemo {
    public static void main(String[] args) {
        Cola cola = new Cola();  // Crea una nueva cola con capacidad 5

        // Crea hilos de productor y consumidor
        Thread productorThread = new Thread(new Productor(cola));
        Thread consumidorThread = new Thread(new Consumidor(cola));

        // Inicia los hilos
        productorThread.start();
        consumidorThread.start();
    }
}
