package ACCESO_A_DATOS.Json;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.json.JSONObject;

public class TrackPoint {
	private double lng;
	private double lat;
	private double ele;
	private LocalDateTime time;
	

	
	public TrackPoint(double lat, double lng, double ele, LocalDateTime time) {
        this.lat = lat;
        this.lng = lng;
        this.ele = ele;
        this.time = time;
    }
	
	// Método para calcular la distancia en metros entre dos TrackPoints usando la fórmula de Haversine
    public double calcularDistancia(TrackPoint otroPunto) {
        final int R = 6371000; // Radio de la Tierra en metros
        double latDist = Math.toRadians(otroPunto.getLat() - this.lat);
        double lngDist = Math.toRadians(otroPunto.getLng() - this.lng);
        double a = Math.sin(latDist / 2) * Math.sin(latDist / 2) +
                   Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(otroPunto.getLat())) *
                   Math.sin(lngDist / 2) * Math.sin(lngDist / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    // Método estático para crear un TrackPoint desde un objeto JSON
    public static TrackPoint fromJSON(JSONObject json) {
        double lat = json.getDouble("lat");
        double lng = json.getDouble("lng");
        double ele = json.getDouble("ele");
        String timeString = json.getString("time");
        
        // Formato del tiempo en el archivo JSON
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy").withLocale(Locale.ENGLISH);;
        LocalDateTime time = LocalDateTime.parse(timeString, formatter);

        return new TrackPoint(lat, lng, ele, time);
    }

    // Método para calcular el tiempo transcurrido entre dos TrackPoints
    public long calcularTiempoEnSegundos(TrackPoint otroPunto) {
        return ChronoUnit.SECONDS.between(this.time, otroPunto.getTime());
    }
    
	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getEle() {
		return ele;
	}

	public void setEle(double ele) {
		this.ele = ele;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	
	
}
