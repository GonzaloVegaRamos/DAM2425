package com.example;

import java.util.LinkedList;
import java.util.Queue;

public class ProductorConsum {
    private static final Queue<Integer> cola = new LinkedList<>();
    private static final int LIMITE = 5;

    public static void main(String[] args) {
        Thread productor = new Thread(() -> {
            int valor = 0;
            try {
                while (true) {
                    synchronized (cola) {
                        while (cola.size() == LIMITE) {
                            cola.wait();
                        }
                        cola.add(valor);
                        System.out.println("Productor produjo: " + valor);
                        valor++;
                        cola.notify();
                    }
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumidor = new Thread(() -> {
            try {
                while (true) {
                    synchronized (cola) {
                        while (cola.isEmpty()) {
                            cola.wait();
                        }
                        int valor = cola.poll();
                        System.out.println("Consumidor consumió: " + valor);
                        cola.notify();
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        productor.start();
        consumidor.start();
    }
}
