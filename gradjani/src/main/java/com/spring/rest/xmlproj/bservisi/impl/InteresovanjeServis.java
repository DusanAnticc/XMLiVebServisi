package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IInteresovanjeServis;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje.IzabraneVakcine.Vakcina;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.InteresovanjeRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import com.spring.rest.xmlproj.util.HtmlTransformer;
import com.spring.rest.xmlproj.util.PDFTransformer;
import com.spring.rest.xmlproj.util.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class InteresovanjeServis implements IInteresovanjeServis {
    @Value("${configPath}")
    private String configPath;

    private final InteresovanjeRepo interesovanjeRepo;
    private final EmailServis emailServis;
    private final PDFTransformer pdfTransformer;
    private final HtmlTransformer htmlTransformer;
    private final RestTemplate restTemplate;

    @Autowired
    public InteresovanjeServis(InteresovanjeRepo interesovanjeRepo, EmailServis emailServis, PDFTransformer pdfTransformer, HtmlTransformer htmlTransformer, RestTemplate restTemplate) {
        this.interesovanjeRepo = interesovanjeRepo;
        this.emailServis = emailServis;
        this.pdfTransformer = pdfTransformer;
        this.htmlTransformer = htmlTransformer;
        this.restTemplate = restTemplate;
    }

    @Override
    public void upis(Interesovanje entitet) {
        try {
            if(entitet.getSifra() == null || entitet.getSifra().equals(""))
                entitet.setSifra(RandomString.getAlphaNumericString(8).toUpperCase());
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

            this.slanjeSluzbenicima(entitet);


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

    public void slanjeSluzbenicima(Interesovanje entitet){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<Interesovanje> request = new HttpEntity<Interesovanje>(entitet, headers);

        ResponseEntity<Interesovanje> entity = restTemplate.postForEntity("http://localhost:8081/api/sluzbenici/interesovanja/upis", request, Interesovanje.class);
    }
}
