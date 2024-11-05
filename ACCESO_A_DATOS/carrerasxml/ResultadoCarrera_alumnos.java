/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ACCESO_A_DATOS.carrerasxml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * 
 * <Result number="44" position="7" positionText="7" points="6">
 * <Driver driverId="hamilton" code="HAM" url=
 * "http://en.wikipedia.org/wiki/Lewis_Hamilton">
 * <PermanentNumber>44</PermanentNumber> <GivenName>Lewis</GivenName>
 * <FamilyName>Hamilton</FamilyName> <DateOfBirth>1985-01-07</DateOfBirth>
 * <Nationality>British</Nationality> </Driver>
 * <Constructor constructorId="mercedes" url=
 * "http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One">
 * <Name>Mercedes</Name> <Nationality>German</Nationality> </Constructor>
 * <Grid>13</Grid> <Laps>78</Laps> <Status statusId="1">Finished</Status>
 * <Time millis="6300141">+15.801</Time> <FastestLap rank="5" lap="54">
 * <Time>1:15.825</Time> <AverageSpeed units="kph">158.433</AverageSpeed>
 * </FastestLap> </Result>
 */
public class ResultadoCarrera_alumnos {
	private Driver d;
	private String constructor;
	private int initialPos;
	private int finalPos;
	private long timeMillis;
	private int completedLaps;
	private int rankFastesLap;
	private boolean finisher;
	
	public static void main(String[] args) {
	    // TODO: Definir la ruta del archivo XML, en este caso "monaco_2017.xml"

	    try {
	        // TODO: Crear una instancia de DocumentBuilderFactory
	    	
	        // TODO: Crear un DocumentBuilder a partir de dbFactory

	    	// TODO: Cargar y analizar el archivo XML especificado por xmlFilePath

	    	// TODO: Normalizar el contenido del documento XML

	        // TODO: Obtener la lista de elementos "Result" del XML

	        // TODO: Iterar sobre cada elemento "Result" en resultList

	    		// TODO: Obtener el elemento "Result" actual de resultList

	            // TODO: Crear una instancia de ResultadoCarrera_TO_DO utilizando el elemento resultElement

	            // TODO: Imprimir el resultado utilizando el método toString()

	    } catch (Exception e) {
	        // TODO: Imprimir un mensaje de error si ocurre una excepción al procesar el archivo XML
	    }
	}

	public ResultadoCarrera_alumnos(Element result) {
	    try {
	        // TODO: Obtener el elemento "Driver" de result y extraer "GivenName" y "FamilyName"

	    	// TODO: Crear una instancia de Driver usando driverElement

	        // TODO: Obtener el nombre del "Constructor" del coche

	        // TODO: Obtener la posición inicial en la parrilla de salida

	        // TODO: Obtener la posición final de la carrera a partir del atributo "position"

	        // TODO: Obtener la cantidad de vueltas completadas

	        // TODO: Obtener el tiempo total en milisegundos (si existe)

	        // TODO: Obtener el rango de la vuelta rápida (si existe)

	        // TODO: Comprobar si el piloto terminó la carrera ("statusId" igual a "1")

	    } catch (Exception e) {
	        // TODO: Imprimir la traza de la excepción si ocurre un error
	    }
	}

	@Override
	public String toString() {
		String resul = "Resultado de Carrera:\n\t" + d.getName() + " " + d.getSurname() + " conduciendo un "
				+ constructor + "\n\tParte de la posicion: " + initialPos + " y termina en la " + finalPos
				+ "\n\tHa completado " + completedLaps + " vueltas";
		if (this.finisher)
			resul += " tardando " + toHHMMSSmmm(timeMillis);
		else
			resul += " sin completar la carrera";

		resul += "\n\tSu clasificación en vuelta rápida personal=" + rankFastesLap;
		return resul;
	}

	public static String toHHMMSSmmm(long millis) {
		long mmm = millis % 1000;
		long seconds = millis / 1000;
		long s = seconds % 60;
		long m = (seconds / 60) % 60;
		long h = (seconds / (60 * 60)) % 24;
		return String.format("%02d:%02d:%02d:%03d", h, m, s, mmm);

	}

	public Driver getD() {
		return d;
	}

	public String getConstructor() {
		return constructor;
	}

	public int getInitialPos() {
		return initialPos;
	}

	public int getFinalPos() {
		return finalPos;
	}

	public long getTimeMillis() {
		return timeMillis;
	}

	public int getCompletedLaps() {
		return completedLaps;
	}

	public int getRankFastesLap() {
		return rankFastesLap;
	}

	public boolean isFinisher() {
		return finisher;
	}

}
