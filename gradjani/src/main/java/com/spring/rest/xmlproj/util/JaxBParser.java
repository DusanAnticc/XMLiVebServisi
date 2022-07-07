package com.spring.rest.xmlproj.util;


import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.obj.izvestaj.Izvestaj;
import com.spring.rest.xmlproj.obj.korisnik.Korisnik;
import com.spring.rest.xmlproj.obj.potvrda.Potvrda;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat;
import com.spring.rest.xmlproj.obj.zahtev.Zahtev;
import com.spring.rest.xmlproj.util.ShemaValidationHandler;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.HashMap;

@Component
public class JaxBParser {
    private HashMap<Class, String> shemaLocationRegistry = new HashMap<>();

    public JaxBParser() {
        shemaLocationRegistry.put(Sertifikat.class, "../data/schema/sertifikat.xsd");
        shemaLocationRegistry.put(Saglasnost.class, "../data/schema/saglasnost.xsd");
        shemaLocationRegistry.put(Izvestaj.class, "../data/schema/izvestaj.xsd");
        shemaLocationRegistry.put(Interesovanje.class, "../data/schema/interesovanje.xsd");
        shemaLocationRegistry.put(Korisnik.class, "../data/schema/korisnik.xsd");
        shemaLocationRegistry.put(Zahtev.class, "../data/schema/zahtev.xsd");
        shemaLocationRegistry.put(Potvrda.class, "../data/schema/potvrda.xsd");
    }

    public <T> T unmarshall(XMLResource resource, Class genericClass) throws JAXBException, XMLDBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(genericClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        File schemaFile = new File(shemaLocationRegistry.get(genericClass));
        Schema schema = schemaFactory.newSchema(schemaFile);

        unmarshaller.setSchema(schema);
        unmarshaller.setEventHandler(new ShemaValidationHandler());

        return (T) unmarshaller.unmarshal(new StringReader(resource.getContent().toString()));
    }



    public <T> OutputStream marshall(T objectToMarshall, Class genericClass) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(objectToMarshall.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

//        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//        File schemaFile = new File(shemaLocationRegistry.get(genericClass));
//        Schema schema = schemaFactory.newSchema(schemaFile);
//        marshaller.setSchema(schema);

        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(objectToMarshall, os);
        return os;
    }

    public <T> OutputStream marshallWithoutSchema(T objectToMarshall) throws JAXBException, SAXException {
        JAXBContext context = JAXBContext.newInstance(objectToMarshall.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        OutputStream os = new ByteArrayOutputStream();
        marshaller.marshal(objectToMarshall, os);
        return os;
    }
}
