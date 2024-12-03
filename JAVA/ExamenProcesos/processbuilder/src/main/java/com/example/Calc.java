package com.example;

public class Calc {
    
        public static void main(String[] args) {
            try {
                // Crear el ProcessBuilder para abrir la calculadora
                ProcessBuilder processBuilder = new ProcessBuilder("calc.exe");
    
                // Iniciar el proceso
                Process process = processBuilder.start();
                process.waitFor();
                System.out.println("Calculadora abierta con éxito.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
    
}
