package com.example;

import java.util.LinkedList;
import java.util.Queue;

public class CocinaRestaurante {
    private static final int CAPACIDAD_BARRA = 5;
    private final Queue<String> barra = new LinkedList<>();

    public synchronized void cocinar(String plato) throws InterruptedException {
        while (barra.size() == CAPACIDAD_BARRA) {
            wait();
        }
        barra.add(plato);
        System.out.println("Chef preparó: " + plato + ". Platos en la barra: " + barra.size());
        notifyAll();
    }

    public synchronized String servir() throws InterruptedException {
        while (barra.isEmpty()) {
            wait();
        }
        String plato = barra.poll();
        System.out.println("Camarero sirvió: " + plato + ". Platos en la barra: " + barra.size());
        notifyAll();
        return plato;
    }

    public static void main(String[] args) {
        CocinaRestaurante cocina = new CocinaRestaurante();

        Thread chefs = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    cocina.cocinar("Plato " + i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread camareros = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    cocina.servir();
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        chefs.start();
        camareros.start();
    }
}
