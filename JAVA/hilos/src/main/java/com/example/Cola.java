package com.example;

public class Cola {
    private int[] buffer = new int[5];
    private int count = 0;
    private int in = 0, out = 0;

    public synchronized void put(int valor) throws InterruptedException {
        while (count == buffer.length) wait();
        buffer[in] = valor;
        in = (in + 1) % buffer.length;
        count++;
        notify();
    }

    public synchronized int take() throws InterruptedException {
        while (count == 0) wait();
        int valor = buffer[out];
        out = (out + 1) % buffer.length;
        count--;
        notify();
        return valor;
    }
}
