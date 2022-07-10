package com.spring.rest.xmlproj.repo;

import com.spring.rest.xmlproj.obj.izvestaj.Izvestaj;
import com.spring.rest.xmlproj.obj.zahtev.Zahtev;
import com.spring.rest.xmlproj.util.AuthenticationUtilities;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.spring.rest.xmlproj.util.CollectionUtil.getOrCreateCollection;

@Repository
public class IzvestajRepo implements Repo<Izvestaj> {
    @Value("${configPath}")
    private String configPath;

    private final String kolekcija = "/db/xmlproj/sluzbenik/izvestaj";
    private final String JAXBKontekst = "com.spring.rest.xmlproj.obj.izvestaj";

    @Override
    public Izvestaj upis(Izvestaj entitet) throws Exception {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();
        Class<?> cl = Class.forName(conn.driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col = null;
        XMLResource res = null;
        OutputStream os = new ByteArrayOutputStream();

        try {
            col = getOrCreateCollection(kolekcija, conn);
            res = (XMLResource) col.createResource(entitet.getPeriodOd().getValue()+"_"+entitet.getPeriodDo().getValue()+".xml", XMLResource.RESOURCE_TYPE);

            JAXBContext context = JAXBContext.newInstance(JAXBKontekst);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            marshaller.marshal(entitet, os);

            res.setContent(os);

            col.storeResource(res);

        } finally {

            //don't forget to cleanup
            if (res != null) {
                try {
                    ((EXistResource) res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if (col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }


            return entitet;
        }
    }

    @Override
    public Izvestaj dobaviPoId(String id) throws Exception {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        Class<?> cl = Class.forName(conn.driver);

        Izvestaj izvestaj = null;

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        XMLResource res = null;

        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + kolekcija);
            col.setProperty(OutputKeys.INDENT, "yes");
            res = (XMLResource)col.getResource(id+".xml");

            if(res == null) {
                System.out.println("[WARNING] Document '" + id + "' can not be found!");
            } else {

                JAXBContext context = JAXBContext.newInstance(JAXBKontekst);

                Unmarshaller unmarshaller = context.createUnmarshaller();

                izvestaj = (Izvestaj) unmarshaller.unmarshal(res.getContentAsDOM());

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            //don't forget to clean up!

            if(res != null) {
                try {
                    ((EXistResource)res).freeResources();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }

            return izvestaj;
        }
    }

    @Override
    public List<Izvestaj> dobaviSve() throws Exception {
        AuthenticationUtilities.ConnectionProperties conn = AuthenticationUtilities.loadProperties();

        Izvestaj izvestaj = null;

        Class<?> cl = Class.forName(conn.driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        List<Izvestaj> sviIzvestaji = new ArrayList<>();

        try {
            // get the collection
            col = DatabaseManager.getCollection(conn.uri + kolekcija);
            col.setProperty(OutputKeys.INDENT, "yes");

            String[] naziviResursa = col.listResources();

            for(String id : naziviResursa){
                XMLResource res = (XMLResource)col.getResource(id);

                if(res == null) {
                    System.out.println("[WARNING] Document '" + id + "' can not be found!");
                } else {

                    JAXBContext context = JAXBContext.newInstance(JAXBKontekst);

                    Unmarshaller unmarshaller = context.createUnmarshaller();

                    izvestaj = (Izvestaj) unmarshaller.unmarshal(res.getContentAsDOM());
                    sviIzvestaji.add(izvestaj);
                }

                if(res != null) {
                    try {
                        ((EXistResource)res).freeResources();
                    } catch (XMLDBException xe) {
                        xe.printStackTrace();
                    }
                }
            }
        } finally {
            //don't forget to clean up!

            if(col != null) {
                try {
                    col.close();
                } catch (XMLDBException xe) {
                    xe.printStackTrace();
                }
            }
            return sviIzvestaji;
        }
    }

    @Override
    public void generisiXML(Izvestaj entitet) {
        try {
            JAXBContext context = JAXBContext.newInstance(JAXBKontekst);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);


            File temp = new File(configPath + File.separator + "data" + File.separator + "xml"+ File.separator + "izvestaji" + File.separator +entitet.getPeriodOd().getValue()+"_"+entitet.getPeriodDo().getValue()+ ".xml");
            temp.createNewFile();
            marshaller.marshal(entitet, temp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
