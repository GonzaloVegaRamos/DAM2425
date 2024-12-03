package com.example;

class MiRunnable implements Runnable {
    @Override
    public void run() {
        // Código que ejecuta el hilo
        System.out.println("Hilo en ejecución: " + Thread.currentThread().getName());
    }
}

public class Runble {
    public static void main(String[] args) {
        MiRunnable miRunnable = new MiRunnable(); // Crear el Runnable
        Thread hilo = new Thread(miRunnable); // Crear el hilo con el Runnable
        hilo.start(); // Iniciar el hilo
    }
}
