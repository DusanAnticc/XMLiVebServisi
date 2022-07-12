package com.spring.rest.xmlproj.bservisi.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.spring.rest.xmlproj.bservisi.IInteresovanjeServis;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje.IzabraneVakcine.Vakcina;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.InteresovanjeRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import com.spring.rest.xmlproj.util.HtmlTransformer;
import com.spring.rest.xmlproj.util.PDFTransformer;


@Service
public class InteresovanjeServis implements IInteresovanjeServis {
    @Value("${configPath}")
    private String configPath;

    private final InteresovanjeRepo interesovanjeRepo;
    private final EmailServis emailServis;
    private final PDFTransformer pdfTransformer;
    private final HtmlTransformer htmlTransformer;
    @Autowired
    public InteresovanjeServis(InteresovanjeRepo interesovanjeRepo, EmailServis emailServis, PDFTransformer pdfTransformer, HtmlTransformer htmlTransformer, RestTemplate restTemplate) {
        this.interesovanjeRepo = interesovanjeRepo;
        this.emailServis = emailServis;
        this.pdfTransformer = pdfTransformer;
        this.htmlTransformer = htmlTransformer;
    }

    @Override
    public void upis(Interesovanje entitet) {
        try {
            entitet.setAbout("http://www.xmlproj.rs/gradjanin/interesovanje/"+entitet.getSifra());
            entitet.setRel("pred:saglasnost");
            for(Vakcina v : entitet.getIzabraneVakcine().getVakcina())
                v.setProperty("pred:nazivVakcine");
            entitet.getDatum().setProperty("pred:podneto");
            this.interesovanjeRepo.upis(entitet);
            this.interesovanjeRepo.generisiXML(entitet);

            htmlTransformer.generateHTML(configPath+"/data/xml/interesovanja/"+entitet.getSifra()+".xml", configPath+"/data/xslt/interesovanje.xsl");
            pdfTransformer.generatePDFfromHTML(configPath+"/data/transform_result/html/"+entitet.getSifra()+".html");
            emailServis.slanjeInteresovanja(entitet.getLicniPodaci().getKontakt().getEmail(), configPath+"/data/transform_result/pdf/"+entitet.getSifra()+".pdf");
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", configPath+"/data/xml/interesovanja/"+entitet.getSifra()+".xml", configPath+"/data/rdf/interesovanja/"+entitet.getSifra()+".rdf");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Interesovanje dobaviPoId(String id) {
        try {
            return this.interesovanjeRepo.dobaviPoId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Interesovanje> dobaviSve() {
        try {
            return this.interesovanjeRepo.dobaviSve();
        } catch (Exception e) {
            return new ArrayList<Interesovanje>();
        }
    }
}
