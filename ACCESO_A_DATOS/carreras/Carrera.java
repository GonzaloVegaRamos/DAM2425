package ACCESO_A_DATOS.carreras;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.FileInputStream;
import java.io.IOException;

public class Carrera {
    public static void main(String[] args) {
        // Definir la ruta del archivo JSON como un String
        String ruta = "ACCESO_A_DATOS/carreras/monaco.json";

        try (
            // Crear un FileInputStream para leer el archivo JSON
            FileInputStream monaco = new FileInputStream(ruta)
        ) {
            // Crear un JSONTokener utilizando el FileInputStream
            JSONTokener token = new JSONTokener(monaco);
            // Crear un JSONObject a partir del JSONTokener
            JSONObject monob = new JSONObject(token);
            // Obtener el objeto "race" del JSON principal
            JSONObject race = monob.getJSONObject("race");
            // Obtener el objeto "results" dentro de "race"
            JSONObject results = race.getJSONObject("results");
            // Obtener el array "result" dentro de "results"
            JSONArray resultArray = results.getJSONArray("result");

            // Iterar sobre cada elemento del array "result"
            for (int i = 0; i < resultArray.length(); i++) {
                // Obtener el objeto "result" en la posición actual del array
                JSONObject registro = resultArray.getJSONObject(i);

                // Obtener la posición
                int position = registro.getInt("position");

                // Obtener el objeto "Driver" y los detalles del conductor
                JSONObject driver = registro.getJSONObject("Driver");
                String nombre = driver.getString("GivenName") + " " + driver.getString("FamilyName");
                String nacionalidad = driver.getString("Nationality");

                // Obtener el objeto "Constructor" y los detalles del equipo
                JSONObject constructor = registro.getJSONObject("Constructor");
                String equipo = constructor.getString("Name");

                // Obtener el valor de "Grid"
                String grid = registro.getString("Grid");

                // Obtener el valor de "Laps"
                String laps = registro.getString("Laps");

                // Obtener el tiempo total de "Time"
                JSONObject timeObj = registro.getJSONObject("Time");
                String totalTime = timeObj.getString("text");

                // Obtener el tiempo de la vuelta más rápida desde "FastestLap"
                JSONObject fastestLap = registro.getJSONObject("FastestLap");
                String lapTime = fastestLap.getString("lapTime");

                // Imprimir los datos del resultado en el formato indicado
                System.out.printf("Posición: %d\n", position);
                System.out.printf("Conductor: %s (%s)\n", nombre, nacionalidad);
                System.out.printf("Equipo: %s\n", equipo);
                System.out.printf("Grid: %s\n", grid);
                System.out.printf("Laps: %s\n", laps);
                System.out.printf("Tiempo total: %s\n", totalTime);
                System.out.printf("Vuelta más rápida: %s\n", lapTime);
                System.out.println("------------------------------");
            }

        } catch (IOException e) {
            System.out.println("Error al leer el archivo JSON: " + e.getMessage());
        }
    }
}
