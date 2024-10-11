import java.io.IOException;

public class ProcessBuilderNotepadExample {

    public static void main(String[] args) {
        // Crea el proceso para abrir Notepad
        ProcessBuilder processBuilder = new ProcessBuilder("notepad.exe");
        
        Process process = null; // Inicializa el proceso

        try {
            // Inicia el proceso
            process = processBuilder.start();

            // Espera un tiempo determinado (por ejemplo, 5 segundos)
            Thread.sleep(1000); // espera 5000 milisegundos (5 segundos)

            // Cierra Notepad usando un comando de sistema
            Process closeProcess = new ProcessBuilder("taskkill", "/F", "/IM", "notepad.exe").start();
            
            closeProcess.waitFor(); // Espera a que el proceso de cierre termine

            // Verifica el código de salida
            int exitCode = closeProcess.exitValue();
            System.out.println("Notepad cerrado con código de salida: " + exitCode);
            if(exitCode==0){
                process = processBuilder.start();
                Thread.sleep(1000);
                // Espera un tiempo determinado (por ejemplo, 5 segundos)
               
    
                // Cierra Notepad usando un comando de sistema
                closeProcess = new ProcessBuilder("taskkill", "/F", "/IM", "notepad.exe").start();
                closeProcess.waitFor(); // Espera a que el proceso de cierre termine
    
                // Verifica el código de salida
                exitCode = closeProcess.exitValue();
                System.out.println("Notepad cerrado con código de salida: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
