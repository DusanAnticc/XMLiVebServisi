package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IInteresovanjeServis;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje.IzabraneVakcine.Vakcina;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.InteresovanjeRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import com.spring.rest.xmlproj.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InteresovanjeServis implements IInteresovanjeServis {
    @Value("${configPath}")
    private String configPath;

    private final InteresovanjeRepo interesovanjeRepo;

    @Autowired
    public InteresovanjeServis(InteresovanjeRepo interesovanjeRepo) {
        this.interesovanjeRepo = interesovanjeRepo;
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

    public List<Interesovanje> dobaviNeobradjenaInteresovanja(){
        try {
            return this.interesovanjeRepo.dobaviSve().stream().filter(i -> i.getHref() == null).collect(Collectors.toList());
        } catch (Exception e) {
            return new ArrayList<Interesovanje>();
        }
    }
}
