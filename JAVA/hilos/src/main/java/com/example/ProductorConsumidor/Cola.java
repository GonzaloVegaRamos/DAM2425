package com.example.ProductorConsumidor;

public class Cola {
    private int[] buffer = new int[5];  // Buffer con capacidad para 5 elementos
    private int count = 0;  // Cantidad de elementos en la cola
    private int in = 0, out = 0;  // Punteros para manejar las posiciones de entrada y salida

    // Método sincronizado para agregar elementos a la cola
    public synchronized void put(int valor) throws InterruptedException {
        while (count == buffer.length) {
            wait();  // Espera si la cola está llena
        }
        buffer[in] = valor;  // Agrega el valor en la posición "in"
        in = (in + 1) % buffer.length;  // Mueve el puntero "in" circularmente
        count++;  // Aumenta el contador de elementos
        notify();  // Despierta al consumidor si está esperando
    }

    // Método sincronizado para tomar elementos de la cola
    public synchronized int take() throws InterruptedException {
        while (count == 0) {
            wait();  // Espera si la cola está vacía
        }
        int valor = buffer[out];  // Toma el valor en la posición "out"
        out = (out + 1) % buffer.length;  // Mueve el puntero "out" circularmente
        count--;  // Disminuye el contador de elementos
        notify();  // Despierta al productor si está esperando
        return valor;
    }
}
