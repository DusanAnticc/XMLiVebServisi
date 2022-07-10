package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IInteresovanjeServis;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.InteresovanjeRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InteresovanjeServis implements IInteresovanjeServis {

    private final InteresovanjeRepo interesovanjeRepo;

    @Autowired
    public InteresovanjeServis(InteresovanjeRepo interesovanjeRepo) {
        this.interesovanjeRepo = interesovanjeRepo;
    }

    @Override
    public void upis(Interesovanje entitet) {
        try {
            this.interesovanjeRepo.upis(entitet);
            this.interesovanjeRepo.generisiXML(entitet);
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", "../data/xml/interesovanja/"+entitet.getSifra()+".xml", "../data/rdf/interesovanja/"+entitet.getSifra()+".rdf");
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
