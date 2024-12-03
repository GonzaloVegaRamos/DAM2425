package com.example;

import java.io.IOException;

public class Custom {
    
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Por favor, introduce un comando.");
            return;
        }

        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", String.join(" ", args));
        try {
            pb.start();
            System.out.println("Comando ejecutado con éxito.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
