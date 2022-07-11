package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IZahtevServis;
import com.spring.rest.xmlproj.obj.zahtev.Zahtev;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.ZahtevRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import com.spring.rest.xmlproj.util.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZahtevServis implements IZahtevServis {

    @Value("${configPath}")
    private String configPath;

    private final ZahtevRepo zahtevRepo;

    @Autowired
    public ZahtevServis(ZahtevRepo zahtevRepo) {
        this.zahtevRepo = zahtevRepo;
    }


    @Override
    public void upis(Zahtev entitet) {
        try {
            if(entitet.getSifra() == null || entitet.getSifra().equals(""))
                entitet.setSifra(RandomString.getAlphaNumericString(8).toUpperCase());
            entitet.setRel("pred:potvrda");
            entitet.setAbout("http://www.xmlproj.rs/gradjanin/zahtev/"+entitet.getSifra());
            entitet.getRazlog().setProperty("pred:razlog");
            entitet.getMesto().setProperty("pred:mesto");
            entitet.getDatumPodnosenja().setProperty("pred:podnet");
            this.zahtevRepo.upis(entitet);
            this.zahtevRepo.generisiXML(entitet);
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", configPath+"/data/xml/zahtevi/"+entitet.getSifra()+".xml", configPath+"/data/rdf/zahtevi/"+entitet.getSifra()+".rdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Zahtev dobaviPoId(String id) {
        try {
            return this.zahtevRepo.dobaviPoId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Zahtev> dobaviSve() {
        try {
            return this.zahtevRepo.dobaviSve();
        } catch (Exception e) {
            return new ArrayList<Zahtev>();
        }
    }
}
