package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AbrirPy {
    public static void main(String[] args) {
        // Crear el ProcessBuilder para ejecutar el script de Python
        ProcessBuilder pb = new ProcessBuilder("python", "script.py");

        try {
            // Iniciar el proceso
            Process process = pb.start();
            
            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();  // Leer una línea de la salida del script Python
            
            // Mostrar la salida
            System.out.println("Salida de Python: " + line);
            
        } catch (IOException e) {
            e.printStackTrace();  // Capturar posibles errores de entrada/salida
        }
    }
}
