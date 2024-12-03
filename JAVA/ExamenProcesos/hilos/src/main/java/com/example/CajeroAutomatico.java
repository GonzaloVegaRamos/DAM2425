package com.example;

import java.util.LinkedList;
import java.util.Queue;

public class CajeroAutomatico {
    private static final int CAPACIDAD_MAXIMA = 10;
    private final Queue<Integer> efectivo = new LinkedList<>();

    public synchronized void depositar(int monto) throws InterruptedException {
        while (efectivo.size() == CAPACIDAD_MAXIMA) {
            wait();
        }
        efectivo.add(monto);
        System.out.println("Se depositó: " + monto + " euros. Saldo actual: " + efectivo.size());
        notifyAll();
    }

    public synchronized int retirar() throws InterruptedException {
        while (efectivo.isEmpty()) {
            wait();
        }
        int monto = efectivo.poll();
        System.out.println("Se retiró: " + monto + " euros. Saldo actual: " + efectivo.size());
        notifyAll();
        return monto;
    }

    public static void main(String[] args) {
        CajeroAutomatico cajero = new CajeroAutomatico();

        Thread productor = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    cajero.depositar(10);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumidor = new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    cajero.retirar();
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
