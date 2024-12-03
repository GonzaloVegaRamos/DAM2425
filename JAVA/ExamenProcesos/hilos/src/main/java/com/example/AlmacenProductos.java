package com.example;

import java.util.concurrent.Semaphore;

public class AlmacenProductos {
    private static final int CAPACIDAD = 5;
    private final Semaphore espaciosDisponibles = new Semaphore(CAPACIDAD);
    private final Semaphore productosDisponibles = new Semaphore(0);
    private int[] buffer = new int[CAPACIDAD];
    private int in = 0, out = 0;

    public void almacenar(int producto) throws InterruptedException {
        espaciosDisponibles.acquire();
        synchronized (this) {
            buffer[in] = producto;
            in = (in + 1) % CAPACIDAD;
            System.out.println("Producto almacenado: " + producto);
        }
        productosDisponibles.release();
    }

    public int recoger() throws InterruptedException {
        productosDisponibles.acquire();
        int producto;
        synchronized (this) {
            producto = buffer[out];
            out = (out + 1) % CAPACIDAD;
            System.out.println("Producto recogido: " + producto);
        }
        espaciosDisponibles.release();
        return producto;
    }

    public static void main(String[] args) {
        AlmacenProductos almacen = new AlmacenProductos();

        Thread trabajadores = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    almacen.almacenar(i);
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread transportistas = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    almacen.recoger();
                    Thread.sleep(800);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        trabajadores.start();
        transportistas.start();
    }
}
