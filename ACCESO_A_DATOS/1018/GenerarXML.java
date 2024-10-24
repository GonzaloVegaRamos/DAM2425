// En este ejercicio se pretende generar, a partir de estructuras existentes en nuestro
// programa, un archivo para el equipo de desarrollo web con el que compartimos
// proyecto.

// Se proporciona el fichero alumno.xml. En dicho fichero se parte de cuatro vectores
// paralelos con el contenido de los modulos de segundo de DAM cursados por un
// alumno.

// String(] modulos = ["Acceso a Datos", "Programación de servicios y procesos",
// "Desarrollo de interfaces", "Programacón multimedia y dispositivos móviles",
// "Sistemas de gestion empresarial", "Empresa e iniciativa emprendedora"):
// boolean( permiteFCT = ftalse, true, false, false, true, truel:
// int[]horas =(6, 2, 6,5,5, 3ł:
// double[] notas = (8.45, 9.0, 8.6, 7.34, 8.2, 7.4);

// Se pide completar el programa para generar un documento XML con la información
// facilitada. Deberá generar una colección de modulos, donde las opciones de permitir la
// FCT y el numero de horas son atributos, y el nombre y la nota son elementos internos
// del XML, como se ve a continuación.
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class GenerarXML {
   public GenerarXML() {
   }

   public static void main(String[] var0) {
      String[] var1 = new String[]{"Acceso a Datos", "Programaci\u00c3\u00b3n de servicios y procesos", "Desarrollo de interfaces", "Programaci\u00c3\u00b3n multimedia y dispositivos m\u00c3\u00b3viles", "Sistemas de gesti\u00c3\u00b3n empresarial", "Empresa e iniciativa emprendedora"};
      boolean[] var2 = new boolean[]{false, true, false, false, true, true};
      int[] var3 = new int[]{6, 3, 6, 5, 5, 3};
      double[] var4 = new double[]{8.45, 9.0, 8.0, 7.34, 8.2, 7.4};

      try {
         DocumentBuilderFactory var5 = DocumentBuilderFactory.newInstance();
         DocumentBuilder var6 = var5.newDocumentBuilder();
         Document var7 = var6.newDocument();
         Element var8 = var7.createElement("modulos");
         var7.appendChild(var8);

         for(int var9 = 0; var9 < var1.length; ++var9) {
            Element var10 = var7.createElement("modulo");
            var10.setAttribute("horas", String.valueOf(var3[var9]));
            var10.setAttribute("permiteFct", String.valueOf(var2[var9]));
            Element var11 = var7.createElement("nombre");
            var11.appendChild(var7.createTextNode(var1[var9]));
            var10.appendChild(var11);
            Element var12 = var7.createElement("nota");
            var12.appendChild(var7.createTextNode(String.valueOf(var4[var9])));
            var10.appendChild(var12);
            var8.appendChild(var10);
         }

         TransformerFactory var14 = TransformerFactory.newInstance();
         Transformer var15 = var14.newTransformer();
         DOMSource var16 = new DOMSource(var7);
         StreamResult var17 = new StreamResult(new File("alumno.xml"));
         var15.transform(var16, var17);
         System.out.println("Archivo XML 'alumno.xml' creado exitosamente!");
      } catch (Exception var13) {
         var13.printStackTrace();
      }

   }
}
