package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.ISertifikatServis;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.SertifikatRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SertifikatServis implements ISertifikatServis {

    private final SertifikatRepo sertifikatRepo;

    @Autowired
    public SertifikatServis(SertifikatRepo sertifikatRepo) {
        this.sertifikatRepo = sertifikatRepo;
    }

    @Override
    public void upis(Sertifikat entitet) {
        try {
            this.sertifikatRepo.upis(entitet);
            this.sertifikatRepo.generisiXML(entitet);
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", "../data/xml/sertifikati/"+entitet.getBrojSertifikata()+".xml", "../data/rdf/sertifikati/"+entitet.getBrojSertifikata()+".rdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Sertifikat dobaviPoId(String id) {
        try {
            return this.sertifikatRepo.dobaviPoId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Sertifikat> dobaviSve() {
        try {
            return this.sertifikatRepo.dobaviSve();
        } catch (Exception e) {
            return new ArrayList<Sertifikat>();
        }
    }
}
