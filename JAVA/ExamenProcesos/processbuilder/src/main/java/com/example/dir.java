package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class dir {
    public static void main(String[] args) {
        try {
            // Crear el proceso
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", "dir"); // Para Windows
            // processBuilder.command("bash", "-c", "ls"); // Para Linux/Mac

            // Ejecutar el proceso
            Process process = processBuilder.start();

            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            System.out.println("Proceso terminado con código: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
