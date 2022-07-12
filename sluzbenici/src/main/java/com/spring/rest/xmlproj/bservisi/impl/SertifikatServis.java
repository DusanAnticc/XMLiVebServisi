package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.ISertifikatServis;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat.Vakcinacije.Vakcinacija;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.SertifikatRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import com.spring.rest.xmlproj.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SertifikatServis implements ISertifikatServis {
    @Value("${configPath}")
    private String configPath;

    private final SertifikatRepo sertifikatRepo;

    @Autowired
    public SertifikatServis(SertifikatRepo sertifikatRepo) {
        this.sertifikatRepo = sertifikatRepo;
    }

    @Override
    public void upis(Sertifikat entitet) {
        try {
            if(entitet.getBrojSertifikata() == null || entitet.getBrojSertifikata().equals(""))
                entitet.setBrojSertifikata(RandomString.getAlphaNumericString(8).toUpperCase());
            entitet.setAbout("http://www.xmlproj.rs/gradjanin/sertifikat/"+entitet.getBrojSertifikata());
            entitet.setRel("pred:zahtev");
            entitet.getQRKod().setProperty("pred:qr");
            entitet.getQRKod().setValue("http://www.xmlproj.rs/gradjanin/sertifikat/"+entitet.getBrojSertifikata());
            for(Vakcinacija v : entitet.getVakcinacije().getVakcinacija()){
                v.getNaziv().setProperty("pred:nazivVakcine");
                v.getDatumPrimanja().setProperty("pred:primljena");
            }
            this.sertifikatRepo.upis(entitet);
            this.sertifikatRepo.generisiXML(entitet);
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", configPath+"/data/xml/sertifikati/"+entitet.getBrojSertifikata()+".xml", configPath+"/data/rdf/sertifikati/"+entitet.getBrojSertifikata()+".rdf");
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

    public void upisSlanjeMejl(Sertifikat entitet){
        try {
            if(entitet.getBrojSertifikata() == null || entitet.getBrojSertifikata().equals(""))
                entitet.setBrojSertifikata(RandomString.getAlphaNumericString(8).toUpperCase());
            entitet.setAbout("http://www.xmlproj.rs/gradjanin/sertifikat/"+entitet.getBrojSertifikata());
            entitet.setRel("pred:zahtev");
            entitet.getQRKod().setProperty("pred:qr");
            entitet.getQRKod().setValue("http://www.xmlproj.rs/gradjanin/sertifikat/"+entitet.getBrojSertifikata());
            for(Vakcinacija v : entitet.getVakcinacije().getVakcinacija()){
                v.getNaziv().setProperty("pred:nazivVakcine");
                v.getDatumPrimanja().setProperty("pred:primljena");
            }
            this.sertifikatRepo.upis(entitet);
            this.sertifikatRepo.generisiXML(entitet);
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", configPath+"/data/xml/sertifikati/"+entitet.getBrojSertifikata()+".xml", configPath+"/data/rdf/sertifikati/"+entitet.getBrojSertifikata()+".rdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
