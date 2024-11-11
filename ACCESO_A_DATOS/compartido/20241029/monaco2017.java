import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class monaco2017 {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse("monaco2017.xml");
            NodeList listaResultados = documento.getElementsByTagName("result");

            for (int i = 0; i < listaResultados.getLength(); i++) {
                Node nodoResultado = listaResultados.item(i);

                if (nodoResultado.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementoResultado = (Element) nodoResultado;

                    String posicion = elementoResultado.getElementsByTagName("position").item(0).getTextContent();
                    Element piloto = (Element) elementoResultado.getElementsByTagName("Driver").item(0);
                    String nombrePiloto = piloto.getElementsByTagName("GivenName").item(0).getTextContent();
                    String apellidoPiloto = piloto.getElementsByTagName("FamilyName").item(0).getTextContent();
                    String nombreConstructor = elementoResultado.getElementsByTagName("Name").item(0).getTextContent();
                    String posicionSalida = elementoResultado.getElementsByTagName("Grid").item(0).getTextContent();
                    String vueltasCompletadas = elementoResultado.getElementsByTagName("Laps").item(0).getTextContent();
                    String tiempoVueltaRapida = elementoResultado.getElementsByTagName("lapTime").item(0).getTextContent();
                    String clasificacionVueltaRapida = ((Element) elementoResultado.getElementsByTagName("FastestLap").item(0)).getAttribute("rank");

                    System.out.printf("%s %s conduciendo un %s Parte de la posición %s y termina en la %s ha completado %s vueltas tardando %s su clasificación en vuelta rápida personal = %s\n",
                            nombrePiloto, apellidoPiloto, nombreConstructor, posicionSalida, posicion, vueltasCompletadas, tiempoVueltaRapida, clasificacionVueltaRapida);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

