
// Ana, nuestra jefa de proyectos, es muy aficionada al mundo del motor y quiere ver
// cómo nos comportamos ante datos reales; qué mejor generador de datos que la
// Fórmula 1. Tenemos que realizar unos análisis, de datos de sus carrearas. Para ello, nos
// ha proporcionado el archivo Monaco2017.xml.

// Podemos observar que este fichero contiene una colección de resultados obtenidos
// por cada piloto en la carrera (posición). Además, en su interior encontramos los
// siguientes elementos:

// . Driver: información del conductor.
// . Maker: la marca del coche
// . Grid: posicion inicial en la parrilla de salida.
// . Laps: vueltas que ha completado.
// · Estatus: estado de finalización de la carrera, donde statusID=1 indica que el
// piloto ha terminado la carrera.
// · Time: nos indica, con el atributo millis, el tiempo total de carrera en
// milisegundos, y dentro de este, su diferencia con respecto al ganador.
// . FastestLap: en su atributo rank, nos determina la posición en la clasificación de
// la vuelta rápida.

// Debes realizar un programa a partir del XML que genere un listado por pantalla, como
// se muestra a continuación, tras cargar los resultados del fichero.

// Resultado de carrera:

// Hamilton Lewis conduciendo un Mercedes
// Parte de la posición 1 y termina en la 1
// Ha completado 78 vueltas tardando 1:15.832
// Su clasificación en vuelta rápida personal = 2

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.InputStream;

public class Carrera {
    private static String listadoMonaco = "Monaco2017.xml";

    public static void main(String[] args) {
        try {
            
            InputStream inputStream = Carrera.class.getClassLoader().getResourceAsStream(listadoMonaco);

            if (inputStream == null) {
                throw new IllegalArgumentException("Archivo no encontrado: " + listadoMonaco);
            }

            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            
            
            Document doc = builder.parse(inputStream);
            doc.getDocumentElement().normalize();

            
            NodeList nodeList = doc.getElementsByTagName("result");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element resultElement = (Element) node;
                    
                    
                    String position = resultElement.getElementsByTagName("position").item(0).getTextContent();
                    Element driverElement = (Element) resultElement.getElementsByTagName("Driver").item(0);
                    String firstName = driverElement.getElementsByTagName("GivenName").item(0).getTextContent();
                    String lastName = driverElement.getElementsByTagName("FamilyName").item(0).getTextContent();
                    String maker = resultElement.getElementsByTagName("Name").item(0).getTextContent();
                    String grid = resultElement.getElementsByTagName("Grid").item(0).getTextContent();
                    String laps = resultElement.getElementsByTagName("Laps").item(0).getTextContent();
                    Element timeElement = (Element) resultElement.getElementsByTagName("Time").item(0);
                    String timeMillis = timeElement.getAttribute("millis");
                    Element fastestLapElement = (Element) resultElement.getElementsByTagName("FastestLap").item(0);
                    String fastestLapRank = fastestLapElement.getAttribute("rank");
                    String lapTime = fastestLapElement.getElementsByTagName("lapTime").item(0).getTextContent();

                    
                    System.out.println(firstName + " " + lastName + " conduciendo un " + maker);
                    System.out.println("Parte de la posición " + grid + " y termina en la " + position);
                    System.out.println("Ha completado " + laps + " vueltas tardando " + formatTime(timeMillis));
                    System.out.println("Su clasificación en vuelta rápida personal = " + fastestLapRank + " (Tiempo: " + lapTime + ")");
                    System.out.println();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    private static String formatTime(String millisStr) {
        long millis = Long.parseLong(millisStr);
        long minutes = (millis / 1000) / 60;
        long seconds = (millis / 1000) % 60;
        long milliseconds = millis % 1000;

        return String.format("%d:%02d.%03d", minutes, seconds, milliseconds);
    }
}
