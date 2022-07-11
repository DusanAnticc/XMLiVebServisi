package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IIzvestajServis;
import com.spring.rest.xmlproj.obj.izvestaj.Izvestaj;
import com.spring.rest.xmlproj.obj.izvestaj.Izvestaj.Proizvodjaci.Proizvodjac;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.IzvestajRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IzvestajServis implements IIzvestajServis {
    @Value("${configPath}")
    private String configPath;

    private final IzvestajRepo izvestajRepo;

    @Autowired
    public IzvestajServis(IzvestajRepo izvestajRepo) {
        this.izvestajRepo = izvestajRepo;
    }

    @Override
    public void upis(Izvestaj entitet) {
        try {
            entitet.setAbout("http://www.xmlproj.rs/sluzbenik/izvestaj/"+entitet.getPeriodOd().getValue()+"_"+entitet.getPeriodDo().getValue());
            entitet.getPeriodOd().setProperty("pred:od");
            entitet.getPeriodDo().setProperty("pred:do");
            entitet.getDatumIzdavanja().setProperty("pred:izdat");
            for(Proizvodjac p : entitet.getProizvodjaci().getProizvodjac())
                p.getNaziv().setProperty("pred:nazivVakcine");
            this.izvestajRepo.upis(entitet);
            this.izvestajRepo.generisiXML(entitet);
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", configPath+"/data/xml/izvestaji/"+entitet.getPeriodOd().getValue()+"_"+entitet.getPeriodDo().getValue()+".xml", configPath+"/data/rdf/izvestaji/"+entitet.getPeriodOd().getValue()+"_"+entitet.getPeriodDo().getValue()+".rdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Izvestaj dobaviPoId(String id) {
        try {
            return this.izvestajRepo.dobaviPoId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Izvestaj> dobaviSve() {
        try {
            return this.izvestajRepo.dobaviSve();
        } catch (Exception e) {
            return new ArrayList<Izvestaj>();
        }
    }
}
