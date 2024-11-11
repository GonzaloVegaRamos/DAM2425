import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class generarxml {

    static class Modulo {
        private String nombre;
        private int horas;
        private boolean permiteFCT;
        private double nota;

        public Modulo(String nombre, int horas, boolean permiteFCT, double nota) {
            this.nombre = nombre;
            this.horas = horas;
            this.permiteFCT = permiteFCT;
            this.nota = nota;
        }

        public String getNombre() {
            return nombre;
        }

        public int getHoras() {
            return horas;
        }

        public boolean isPermiteFCT() {
            return permiteFCT;
        }

        public double getNota() {
            return nota;
        }
    }

    static class Alumno {
        private String nombre;
        private String cicloFormativo;
        private String curso;
        private ArrayList<Modulo> modulos;

        public Alumno(String nombre, String cicloFormativo, String curso) {
            this.nombre = nombre;
            this.cicloFormativo = cicloFormativo;
            this.curso = curso;
            this.modulos = new ArrayList<>();
        }

        public void addModulo(Modulo modulo) {
            this.modulos.add(modulo);
        }

        public String getNombre() {
            return nombre;
        }

        public String getCicloFormativo() {
            return cicloFormativo;
        }

        public String getCurso() {
            return curso;
        }

        public ArrayList<Modulo> getModulos() {
            return modulos;
        }
    }

    public static void main(String[] args) {

        Alumno alumno = new Alumno("Sergio Márquez", "Desarrollo de Aplicaciones Multiplataforma", "Segundo");

        alumno.addModulo(new Modulo("Desarrollo de Interfaces", 100, true, 8.5));
        alumno.addModulo(new Modulo("Programación Multimedia y Dispositivos Móviles", 120, true, 9.0));
        alumno.addModulo(new Modulo("Programación de Servicios y Procesos", 80, false, 7.5));
        alumno.addModulo(new Modulo("Sistemas de Gestión Empresarial", 90, true, 6.8));

        try {
            generarXML(alumno);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public static void generarXML(Alumno alumno) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("alumno");
        doc.appendChild(rootElement);

        Element nombreAlumno = doc.createElement("nombre");
        nombreAlumno.appendChild(doc.createTextNode(alumno.getNombre()));
        rootElement.appendChild(nombreAlumno);

        Element cicloFormativo = doc.createElement("cicloFormativo");
        cicloFormativo.appendChild(doc.createTextNode(alumno.getCicloFormativo()));
        rootElement.appendChild(cicloFormativo);

        Element curso = doc.createElement("curso");
        curso.appendChild(doc.createTextNode(alumno.getCurso()));
        rootElement.appendChild(curso);

        Element modulosElement = doc.createElement("modulos");
        rootElement.appendChild(modulosElement);

        for (Modulo modulo : alumno.getModulos()) {
            Element moduloElement = doc.createElement("modulo");
            moduloElement.setAttribute("horas", String.valueOf(modulo.getHoras()));
            moduloElement.setAttribute("permiteFCT", String.valueOf(modulo.isPermiteFCT()));
            Element nombreModulo = doc.createElement("nombre");
            nombreModulo.appendChild(doc.createTextNode(modulo.getNombre()));
            moduloElement.appendChild(nombreModulo);
            Element notaModulo = doc.createElement("nota");
            notaModulo.appendChild(doc.createTextNode(String.valueOf(modulo.getNota())));
            moduloElement.appendChild(notaModulo);
            modulosElement.appendChild(moduloElement);
        }

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);

        StreamResult result = new StreamResult(new File("alumno2.xml"));
        transformer.transform(source, result);
    }
}
