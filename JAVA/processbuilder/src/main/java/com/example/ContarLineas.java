
package com.example;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ContarLineas {
    
public static void main(String[] args) {
ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "find /c /v \"\" Calc.java");
try {
    Process process = pb.start();
    
    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String output = reader.readLine();
    
    System.out.println("Número de líneas en el archivo: " + output);
} catch (IOException e) {
    e.printStackTrace();
}
}
}
