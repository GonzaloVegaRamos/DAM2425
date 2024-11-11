import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String textoplano = "textoplano.txt";
        String textoconfig = "textoconfig.cfg";
        String textoxml = "textoxml.xml";
        int opcion;
        do{
            System.out.println("1. Escribir fichero de texto");
            System.out.println("2. Leer fichero de texto");
            System.out.println("3. Escribir fichero de configuración");
            System.out.println("4. Leer fichero de configuración");
            System.out.println("5. Escribir fichero XML");
            System.out.println("6. Leer fichero XML");
            System.out.println("7. Salir");
            System.out.print("Elige una opcion: ");

            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion){
            case 1:
                escribirTextoPlano(textoplano, sc);
                break;           
            case 2:
                leerTextoPlano(textoplano);
                break;
            case 3:
                escribirConfiguracion(textoconfig, sc);
                break;
            case 4:
                leerConfiguracion(textoconfig);
                break;
            case 5:
                escribirXMLProductos(textoxml, sc);
                break;
            case 6:
                leerXMLProductos(textoxml);
                break;
            case 7:
                System.out.println("¡Hasta otra!");
                break;
            default:
                System.err.println("Escoge una opcion entre 1-7.");

            }
        }while (opcion != 7);

    }
    public static void escribirTextoPlano(String textoplano, Scanner sc) {
        System.out.println("Escribe una frase");
        String frase = sc.nextLine(); 
        try(BufferedWriter escritor = new BufferedWriter(new FileWriter(textoplano))) {
            escritor.write(frase);
            System.out.println("Frase escrita en " + textoplano);
        }catch (IOException e) {
            System.err.println("Error en la escritura");
        }
    }
        public static void leerTextoPlano(String textoplano) {
        if (Files.exists(Paths.get(textoplano))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(textoplano))) {
                String linea;
                System.out.println("Contenido de" + textoplano + ":");
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.out.println("Error leyendo el fichero de texto plano.");
            }
        } else {
            System.out.println("El fichero de texto plano no existe.");
        }
    }
    public static void escribirConfiguracion(String textoconfig, Scanner sc) {
        System.out.print("Introduce el usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Introduce la contraseña: ");
        String password = sc.nextLine();
        System.out.print("Introduce el servidor: ");
        String servidor = sc.nextLine();
        System.out.print("Introduce el puerto: ");
        int puerto = sc.nextInt();
        sc.nextLine(); 

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(textoconfig))) {
            writer.write("usuario=" + usuario + "\n");
            writer.write("password=" + password + "\n");
            writer.write("servidor=" + servidor + "\n");
            writer.write("puerto=" + puerto + "\n");
            System.out.println("Configuración guardada en " + textoconfig);
        } catch (IOException e) {
          System.out.println("Error escribiendo en el fichero de configuración.");
        }
    }
    public static void leerConfiguracion(String textoconfig) {
        if (Files.exists(Paths.get(textoconfig))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(textoconfig))) {
                String linea;
                System.out.println("Configuración en " + textoconfig + ":");
                while ((linea = reader.readLine()) != null) {
                    System.out.println(linea);
                }
            } catch (IOException e) {
                System.err.println("Error");
            }
        } else {
            System.err.println("El fichero no existe.");
        }
    }
        public static void escribirXMLProductos(String textoxml, Scanner sc) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("productos");
            doc.appendChild(rootElement);

            System.out.print("¿Cuántos productos quieres añadir? ");
            int numProductos = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < numProductos; i++) {
                System.out.println("Producto " + (i + 1) + ":");
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Precio: ");
                double precio = sc.nextDouble();
                sc.nextLine();

                Element producto = doc.createElement("producto");
                rootElement.appendChild(producto);

                Element nombreElem = doc.createElement("nombre");
                nombreElem.appendChild(doc.createTextNode(nombre));
                producto.appendChild(nombreElem);

                Element precioElem = doc.createElement("precio");
                precioElem.appendChild(doc.createTextNode(String.valueOf(precio)));
                producto.appendChild(precioElem);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(textoxml));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("XML guardado en " + textoxml);
        } catch (Exception e) {
            System.out.println("Error al escribir el archivo XML.");
        }
    }
    public static void leerXMLProductos(String textoxml) {
        if (Files.exists(Paths.get(textoxml))) {
            try {
                File inputFile = new File(textoxml);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(inputFile);
                doc.getDocumentElement().normalize();

                System.out.println("Elemento raíz: " + doc.getDocumentElement().getNodeName());

                NodeList nList = doc.getElementsByTagName("producto");

                for (int i = 0; i < nList.getLength(); i++) {
                    Node node = nList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element elem = (Element) node;
                        String nombre = elem.getElementsByTagName("nombre").item(0).getTextContent();
                        String precio = elem.getElementsByTagName("precio").item(0).getTextContent();

                        System.out.println("Producto " + (i + 1) + ":");
                        System.out.println("Nombre: " + nombre);
                        System.out.println("Precio: " + precio);
                    }
                }
            } catch (Exception e) {
                System.out.println("Error al leer el archivo XML.");
            }
        } else {
            System.out.println("El archivo XML no existe.");
        }
    }
}

