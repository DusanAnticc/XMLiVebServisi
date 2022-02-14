package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IZahtevServis;
import com.spring.rest.xmlproj.obj.Zahtev;
import com.spring.rest.xmlproj.repo.ZahtevRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZahtevServis implements IZahtevServis {

    private final ZahtevRepo zahtevRepo;

    @Autowired
    public ZahtevServis(ZahtevRepo zahtevRepo) {
        this.zahtevRepo = zahtevRepo;
    }


    @Override
    public void upis(Zahtev entitet) {
        try {
            this.zahtevRepo.upis(entitet);
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
