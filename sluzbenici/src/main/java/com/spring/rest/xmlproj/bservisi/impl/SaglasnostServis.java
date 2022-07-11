package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.ISaglasnostServis;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.SaglasnostRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import com.spring.rest.xmlproj.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaglasnostServis implements ISaglasnostServis {
    @Value("${configPath}")
    private String configPath;

    private final SaglasnostRepo saglasnostRepo;

    @Autowired
    public SaglasnostServis(SaglasnostRepo saglasnostRepo) {
        this.saglasnostRepo = saglasnostRepo;
    }

    @Override
    public void upis(Saglasnost saglasnost){
        try {
            if(saglasnost.getSifra() == null || saglasnost.getSifra().equals(""))
                saglasnost.setSifra(RandomString.getAlphaNumericString(10).toUpperCase());
            saglasnost.setAbout("http://www.xmlproj.rs/gradjanin/saglasnost/"+saglasnost.getSifra());
            saglasnost.setRel("pred:interesovanje");
            saglasnost.getPacijentovDeo().getKoristiSocZastitu().getVrednost().setProperty("pred:koristiSoc");
            saglasnost.getPacijentovDeo().getDatum().setProperty("pred:podneta");
            if(saglasnost.getRadnikovDeo().getZdravstvenaUstanova() != null){
                saglasnost.getRadnikovDeo().getZdravstvenaUstanova().setProperty("pred:ustanova");
                saglasnost.getRadnikovDeo().getPodaciLekar().getFaksimil().setProperty("pred:faksimil");
                for(Saglasnost.RadnikovDeo.Vakcine.Vakcina v : saglasnost.getRadnikovDeo().getVakcine().getVakcina()){
                    v.getNaziv().setProperty("pred:nazivVakcine");
                }
                saglasnost.getRadnikovDeo().getOdluka().setProperty("pred:odluka");
            }
            this.saglasnostRepo.upis(saglasnost);
            this.saglasnostRepo.generisiXML(saglasnost);
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", configPath+"/data/xml/saglasnosti/"+saglasnost.getSifra()+".xml", configPath+"/data/rdf/saglasnosti/"+saglasnost.getSifra()+".rdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Saglasnost dobaviPoId(String id){
        try {
            return this.saglasnostRepo.dobaviPoId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Saglasnost> dobaviSve() {
        try {
            return this.saglasnostRepo.dobaviSve();
        } catch (Exception e) {
            return new ArrayList<Saglasnost>();
        }
    }
}
