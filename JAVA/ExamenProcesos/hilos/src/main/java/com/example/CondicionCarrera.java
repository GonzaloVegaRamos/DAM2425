
// Condición de carrera:

// Este código simula una condición de carrera donde dos hilos (hilo1 y hilo2) intentan incrementar un contador (contador) de manera concurrente. 
//Como no hay sincronización, ambos hilos pueden leer y escribir el valor del contador al mismo tiempo, lo que puede resultar en un valor final incorrecto.
// contador++ no es una operación atómica. 
//En un escenario sin sincronización, ambos hilos pueden leer el valor de contador simultáneamente, incrementar el contador, y luego escribir el valor incrementado, lo que lleva a una condición de carrera.
// Métodos start() y join():

// start(): Este método inicia un nuevo hilo de ejecución. Cuando se llama a start(), el hilo comienza a ejecutarse de manera concurrente.
// join(): Este método hace que el hilo principal (el que llama a join()) espere hasta que el hilo con el que se llama termine su ejecución. 
//En este caso, el hilo principal espera hasta que ambos hilos (hilo1 y hilo2) terminen antes de imprimir el resultado final.
// Ejecuta el programa varias veces:

// Si ejecutas este programa varias veces, observarás que el valor final del contador no es el esperado (20,000) en la mayoría de las ejecuciones debido a la condición de carrera.
// Esto es porque los hilos no están sincronizados, y ambos pueden incrementar el contador al mismo tiempo, sin saberlo.


package com.example;

public class CondicionCarrera implements Runnable {
    private int contador = 0;

     public synchronized void incrementar() {
        contador++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementar();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CondicionCarrera cc = new CondicionCarrera();
        Thread hilo1 = new Thread(cc);
        Thread hilo2 = new Thread(cc);

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println("Valor final del contador: " + cc.contador);
    }
}
