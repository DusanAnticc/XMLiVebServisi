package com.spring.rest.xmlproj.util;

import com.itextpdf.text.DocumentException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.obj.izvestaj.Izvestaj;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat;
import com.spring.rest.xmlproj.obj.zahtev.Zahtev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

@Component
public class HtmlTransformer {
	@Autowired
	private JaxBParser parser;

    @Value("${configPath}")
    private String configPath;
	
    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    public static final String HTML_FILE = "../data/transform_result/html/potvrda.html";

//    public static final String INPUT_FILE = "../data/xml/zahtevi/JJKA441N.xml";
    public static final String INPUT_FILE = "./src/main/resources/podaci/potvrda1.xml";

    public static final String XSL_FILE = "../data/xslt/potvrda.xsl";
    
    private static HashMap<Class, String> shemaLocationRegistry = new HashMap<>();
    
    static {

    	shemaLocationRegistry.put(Interesovanje.class, "../data/xslt/interesovanje.xsl");
        shemaLocationRegistry.put(Izvestaj.class, "../data/xslt/izvestaj.xsl");
        shemaLocationRegistry.put(Saglasnost.class, "../data/xslt/saglasnost.xsl");
        shemaLocationRegistry.put(Sertifikat.class, "../data/xslt/sertifikat.xsl");
        shemaLocationRegistry.put(Zahtev.class, "../data/xslt/zahtev.xsl");
    }
    

    static {

        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        transformerFactory = TransformerFactory.newInstance();

    }

    public org.w3c.dom.Document buildDocument(String filePath) {

        org.w3c.dom.Document document = null;
        try {

            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(new File(filePath));

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;

        }

        return document;
    }

    public void generateHTML(String xmlPath, String xslPath) throws FileNotFoundException {

        try {
            File xmlFile = new File(xmlPath);

            String outputPath = "C:/Users/Dusan/Desktop/XMLiVebServisi/data/transform_result/html/"+xmlFile.getName().split("\\.")[0]+".html";

            System.out.println(outputPath);

            File outputHtmlFile = new File(outputPath);
            System.out.println("Created html file:"+outputHtmlFile.createNewFile());

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(xmlPath));
            StreamResult result = new StreamResult(new FileOutputStream(outputPath));
            transformer.transform(source, result);
            
            

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public <T> byte[] generateHTMLtoByteArray(T object) throws JAXBException, SAXException, IOException, ParserConfigurationException {

        try {
        	String xslPath = shemaLocationRegistry.get(object.getClass());
        	org.w3c.dom.Document document = null;
        	
            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
            OutputStream os = parser.marshall(object, object.getClass());

            ByteArrayOutputStream baos = (ByteArrayOutputStream)os;
            
            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(new ByteArrayInputStream(baos.toByteArray()));
            // Transform DOM to HTML
            DOMSource source = new DOMSource(document);
            
            ByteArrayOutputStream xhtmlResult = new ByteArrayOutputStream();
			transformer.transform(source, new StreamResult(xhtmlResult));

			return xhtmlResult.toByteArray();
            

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
		return null;
    }


    public static void main(String[] args) throws IOException, DocumentException {
        HtmlTransformer htmlTransformer = new HtmlTransformer();

        htmlTransformer.generateHTML(INPUT_FILE, XSL_FILE);
    }
}
