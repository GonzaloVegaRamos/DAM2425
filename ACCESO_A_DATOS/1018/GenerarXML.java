
// En este ejercicio se pretende generar, a partir de estructuras existentes en nuestro
// programa, un archivo para el equipo de desarrollo web con el que compartimos
// proyecto.

// Se proporciona el fichero alumno.xml. En dicho fichero se parte de cuatro vectores
// paralelos con el contenido de los modulos de segundo de DAM cursados por un
// alumno.

// String[] modulos = ("Acceso a Datos", "Programación de servicios y procesos",
// "Desarrollo de interfaces", "Programación multimedia y dispositivos moviles",
// "Sistemas de gestión empresarial", "Empresa e iniciativa emprendedora");
// boolean[] permiteFCT = {false, true, false, false, true, true);
// int[] horas = (6, 3, 6, 5, 5, 3);
// double(] notas = (8.45, 9.0, 8.0, 7.34, 8.2, 7.4);

// Se pide completar el programa para generar un documento XML con la información
// facilitada. Deberá generar una colección de módulos, donde las opciones de permitir la
// FCT y el numero de horas son atributos, y el nombre y la nota son elementos internos
// del XML, como se ve a continuación.

// <? xml version="1.0" encoding="UTF-8" standalone="no"?><modulos>
// <modulo horas="6" permiteFct="false">
// <nombre>Acceso a Datos</nombre>
// <nota>8.45</nota>
// </modulo>
// <modulo horas="3" permiteFct="true">
// cnombre>Programación de servicios y procesos</nombre>
// <nota>9.0</nota>
// </modulo>

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

public class GenerarXML {
        public static void main(String[] args) {
            // Datos de ejemplo
            String[] modulos = {
                "Acceso a Datos",
                "Programación de servicios y procesos",
                "Desarrollo de interfaces",
                "Programación multimedia y dispositivos móviles",
                "Sistemas de gestión empresarial",
                "Empresa e iniciativa emprendedora"
            };
            
            boolean[] permiteFCT = {false, true, false, false, true, true};
            int[] horas = {6, 3, 6, 5, 5, 3};
            double[] notas = {8.45, 9.0, 8.0, 7.34, 8.2, 7.4};
    
            try {
                // Crear un nuevo documento XML
                DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("modulos");
                doc.appendChild(rootElement);
    
                // Crear elementos "modulo"
                for (int i = 0; i < modulos.length; i++) {
                    Element modulo = doc.createElement("modulo");
                    modulo.setAttribute("horas", String.valueOf(horas[i]));
                    modulo.setAttribute("permiteFct", String.valueOf(permiteFCT[i]));
    
                    // Crear subelementos "nombre" y "nota"
                    Element nombre = doc.createElement("nombre");
                    nombre.appendChild(doc.createTextNode(modulos[i]));
                    modulo.appendChild(nombre);
    
                    Element nota = doc.createElement("nota");
                    nota.appendChild(doc.createTextNode(String.valueOf(notas[i])));
                    modulo.appendChild(nota);
    
                    // Añadir el módulo al elemento raíz
                    rootElement.appendChild(modulo);
                }
    
                // Guardar el documento XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("alumno.xml"));
    
                transformer.transform(source, result);
    
                System.out.println("Archivo XML 'alumno.xml' creado exitosamente!");
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    


