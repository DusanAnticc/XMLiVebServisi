package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IKorisnikServis;
import com.spring.rest.xmlproj.obj.korisnik.Korisnik;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.KorisnikRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KorisnikServis implements IKorisnikServis {

    @Value("${configPath}")
    private String configPath;
    
    private final KorisnikRepo korisnikRepo;

    @Autowired
    public KorisnikServis(KorisnikRepo korisnikRepo) {
        this.korisnikRepo = korisnikRepo;
    }

    @Override
    public void upis(Korisnik entitet) {
        try{
            entitet.setAbout("http://www.xmlproj.rs/korisnik/"+entitet.getLicniPodaci().getKontakt().getEmail());
            this.korisnikRepo.upis(entitet);
            this.korisnikRepo.generisiXML(entitet);
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", configPath+"/data/xml/korisnici/"+entitet.getLicniPodaci().getKontakt().getEmail()+".xml", configPath+"/data/rdf/korisnici/"+entitet.getLicniPodaci().getKontakt().getEmail()+".rdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Korisnik dobaviPoId(String id) {
        try{
            return this.korisnikRepo.dobaviPoId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Korisnik> dobaviSve() {
        try{
            return this.korisnikRepo.dobaviSve();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Korisnik>();
        }
    }
}
