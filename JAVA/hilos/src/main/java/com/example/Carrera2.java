package com.example;
import java.util.Random;

class Corredor implements Runnable {
    private String nombre;
    private int pasos = 0;
    private static final int META = 100;
    private static boolean haGanado = false; // Flag para verificar si alguien ha ganado

    public Corredor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (pasos < META && !haGanado) { // Verificamos si no ha ganado otro corredor
            // Avanzar entre 1 y 10 pasos de forma aleatoria
            int avance = random.nextInt(10) + 1;
            pasos += avance;

            // Limitar los pasos a 100
            if (pasos > META) {
                pasos = META;
            }

            System.out.println(nombre + " avanzó " + avance + " pasos. Total: " + pasos + " pasos.");

            try {
                // Simular el sueño del hilo con un tiempo aleatorio entre 100 y 500 milisegundos
                Thread.sleep(random.nextInt(400) + 100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Si el corredor llega a la meta, indicar que ha ganado y cambiar la bandera
        if (pasos == META && !haGanado) {
            haGanado = true;
            System.out.println(nombre + " ha llegado a la meta con " + pasos + " pasos.");
        }
    }
}

public class Carrera2 {
    public static void main(String[] args) {
        // Crear tres corredores (hilos)
        Corredor corredor1 = new Corredor("Corredor 1");
        Corredor corredor2 = new Corredor("Corredor 2");
        Corredor corredor3 = new Corredor("Corredor 3");

        // Crear los hilos
        Thread hilo1 = new Thread(corredor1);
        Thread hilo2 = new Thread(corredor2);
        Thread hilo3 = new Thread(corredor3);

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            // Esperar a que cualquiera de los hilos termine (puede ser el primero en llegar)
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("La carrera ha terminado!");
    }
}
