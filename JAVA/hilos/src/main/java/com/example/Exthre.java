package com.example;

class MiHilo extends Thread {
    @Override
    public void run() {
        // Código que ejecuta el hilo
        System.out.println("Hilo en ejecución: " + Thread.currentThread().getName());
    }
}

public class Exthre {
    public static void main(String[] args) {
        MiHilo hilo = new MiHilo(); // Crear el hilo
        MiHilo hilo1 = new MiHilo();
        hilo1.start();
        hilo.start(); // Iniciar el hilo
    }
}
