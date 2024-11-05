package ACCESO_A_DATOS.Json;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class main {

    public static void main(String[] args) {
        
        //TODO: carga el archivo trail.json
        List<TrackPoint> trackList = cargarTrackPoints("ACCESO_A_DATOS/Json/trail.json");
        
        //TODO: comprueba que la lista no esté vacía
        if (trackList.isEmpty()) {
            System.out.println("La lista de puntos de seguimiento está vacía.");
            return;
        } else {
            //TODO: llama al método calcularElevacionMaxMin
            calcularElevacionMaxMin(trackList);
            //TODO: llama al método calcularLaps
            calcularLaps(trackList);
        }
    }

    // Método para cargar los puntos de seguimiento desde un archivo JSON
    public static List<TrackPoint> cargarTrackPoints(String rutaArchivo) {
        List<TrackPoint> trackPoints = new ArrayList<>();

        

    try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
        StringBuilder sb = new StringBuilder();
        String line;

        
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

       
        

        JSONTokener tokener = new JSONTokener(sb.toString());
        JSONArray jsonArray = new JSONArray(tokener);
    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH); 
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            
            double lat = jsonObject.getDouble("lat");
            double lng = jsonObject.getDouble("lng");
            double ele = jsonObject.getDouble("ele");
            String timeString = jsonObject.getString("time");
            LocalDateTime time = LocalDateTime.parse(timeString, formatter); 

            TrackPoint trackPoint = new TrackPoint(lat, lng, ele, time);
            trackPoints.add(trackPoint);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } catch (Exception e) {
        e.printStackTrace(); 
    }

    
        return trackPoints;
    }

    
    public static void calcularElevacionMaxMin(List<TrackPoint> trackPoints) {
        double elevacionMaxima = Double.NEGATIVE_INFINITY;
        double elevacionMinima = Double.POSITIVE_INFINITY;

        for (TrackPoint point : trackPoints) {
            if (point.getEle() > elevacionMaxima) {
                elevacionMaxima = point.getEle();
            }
            if (point.getEle() < elevacionMinima) {
                elevacionMinima = point.getEle();
            }
        }
        System.out.printf("Elevación máxima: %.2f metros\n", elevacionMaxima);
        System.out.printf("Elevación mínima: %.2f metros\n", elevacionMinima);
    }

    // Método para calcular las vueltas (laps) de 1000 metros
    public static void calcularLaps(List<TrackPoint> trackPoints) {
        double distanciaAcumulada = 0.0;
        int lapCount = 1;
        TrackPoint lapInicio = trackPoints.get(0);

        for (int i = 1; i < trackPoints.size(); i++) {
            TrackPoint actual = trackPoints.get(i);
            distanciaAcumulada += actual.calcularDistancia(trackPoints.get(i - 1));

            if (distanciaAcumulada >= 1000) {
                long tiempoLap = lapInicio.calcularTiempoEnSegundos(actual);
                Duration duracion = Duration.ofSeconds(tiempoLap);
                long minutos = duracion.toMinutes();
                long segundos = duracion.minusMinutes(minutos).getSeconds();

                System.out.printf("Lap %d: 1000 m efectuada en %02d:%02d\n", lapCount, minutos, segundos);

                // Reiniciar para la siguiente vuelta
                lapInicio = actual;
                lapCount++;
                distanciaAcumulada -= 1000;
            }
        }

        // Imprimir la última vuelta si queda una distancia menor a 1000 m
        if (distanciaAcumulada > 0) {
            long tiempoLap = lapInicio.calcularTiempoEnSegundos(trackPoints.get(trackPoints.size() - 1));
            Duration duracion = Duration.ofSeconds(tiempoLap);
            long minutos = duracion.toMinutes();
            long segundos = duracion.minusMinutes(minutos).getSeconds();

            System.out.printf("Lap %d: %.0f m efectuada en %02d:%02d\n", lapCount, distanciaAcumulada, minutos, segundos);
        }
    }
}
