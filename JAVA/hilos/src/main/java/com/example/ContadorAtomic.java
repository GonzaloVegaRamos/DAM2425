package com.example;

import java.util.concurrent.atomic.AtomicInteger;

public class ContadorAtomic {
    
    private AtomicInteger contador = new AtomicInteger(0);

    public void incrementar() {
        contador.incrementAndGet();  // Operación atómica
    }

    public static void main(String[] args) throws InterruptedException {
        ContadorAtomic c = new ContadorAtomic();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                c.incrementar();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                c.incrementar();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Contador final: " + c.contador);
    }
}

