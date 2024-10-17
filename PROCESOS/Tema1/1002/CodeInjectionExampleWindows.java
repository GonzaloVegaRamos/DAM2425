import java.io.IOException;

public class CodeInjectionExampleWindows {
    public static void main(String[] args) throws IOException {
        // Supongamos que este argumento proviene de una entrada del usuario
        String userInput = args[0];

        // Vulnerable: el input del usuario se concatena directamente en el comando
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "echo ",userInput );
        processBuilder.start();
    }
}