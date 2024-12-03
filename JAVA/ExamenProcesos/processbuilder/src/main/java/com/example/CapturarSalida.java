package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CapturarSalida {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo Hola desde Java!");
        try {
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Salida: " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
