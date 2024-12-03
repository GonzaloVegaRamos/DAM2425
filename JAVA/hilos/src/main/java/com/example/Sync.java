package com.example;

class Contador {
    private int cuenta = 0;

    // Método sincronizado para que solo un hilo acceda a él a la vez
    public synchronized void incrementar() {
        cuenta++;
    }

    public int obtenerCuenta() {
        return cuenta;
    }
}

public class Sync {
    public static void main(String[] args) {
        Contador contador = new Contador();

        // Crear dos hilos que incrementan el contador
        Thread hilo1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                contador.incrementar();
            }
        });

        Thread hilo2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                contador.incrementar();
            }
        });

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();

        // Esperar que ambos hilos terminen
        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Imprimir el resultado
        System.out.println("Cuenta final: " + contador.obtenerCuenta());
    }
}

