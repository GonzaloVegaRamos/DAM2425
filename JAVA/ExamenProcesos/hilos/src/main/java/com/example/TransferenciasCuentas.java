package com.example;

import java.util.concurrent.atomic.AtomicInteger;

public class TransferenciasCuentas {
    private static final AtomicInteger cuenta1 = new AtomicInteger(1000);
    private static final AtomicInteger cuenta2 = new AtomicInteger(1000);

    public static void transferir(AtomicInteger origen, AtomicInteger destino, int monto) {
        if (origen.get() >= monto) {
            origen.addAndGet(-monto);
            destino.addAndGet(monto);
            System.out.println("Transferencia de " + monto + " realizada. Saldo origen: " + origen.get() + ", saldo destino: " + destino.get());
        } else {
            System.out.println("Transferencia fallida. Saldo insuficiente en la cuenta origen.");
        }
    }

    public static void main(String[] args) {
        Thread transferencias = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                transferir(cuenta1, cuenta2, 200);
                transferir(cuenta2, cuenta1, 100);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });

        transferencias.start();
    }
}
