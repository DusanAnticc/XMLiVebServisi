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
import org.springframework.beans.factory.annotation.Autowired;
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
	
    private static DocumentBuilderFactory documentFactory;

    private static TransformerFactory transformerFactory;

    public static final String HTML_FILE = "../data/transform_result/html/interesovanje.html";

    public static final String INPUT_FILE = "../data/xml/interesovanja/5AA6VDA8.xml";

    public static final String XSL_FILE = "../data/xslt/interesovanje.xsl";
    
    private static HashMap<Class, String> shemaLocationRegistry = new HashMap<>();
    
    static {

    	shemaLocationRegistry.put(Interesovanje.class, "../data/xslt/interesovanje.xsl");
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

            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(xmlPath));
            StreamResult result = new StreamResult(new FileOutputStream(HTML_FILE));
            transformer.transform(source, result);
            
            

        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            e.printStackTrace();
        } catch (TransformerException e) {
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
