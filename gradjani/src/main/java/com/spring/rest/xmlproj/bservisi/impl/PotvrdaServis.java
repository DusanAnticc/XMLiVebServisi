package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IPotvrdaServis;
import com.spring.rest.xmlproj.obj.Potvrda;
import com.spring.rest.xmlproj.repo.PotvrdaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PotvrdaServis implements IPotvrdaServis {

    private final PotvrdaRepo potvrdaRepo;

    @Autowired
    public PotvrdaServis(PotvrdaRepo potvrdaRepo) {
        this.potvrdaRepo = potvrdaRepo;
    }


    @Override
    public void upis(Potvrda entitet) {
        try {
            this.potvrdaRepo.upis(entitet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Potvrda dobaviPoId(String id) {
        try {
            return this.potvrdaRepo.dobaviPoId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Potvrda> dobaviSve() {
        try {
            return this.potvrdaRepo.dobaviSve();
        } catch (Exception e) {
            return new ArrayList<Potvrda>();
        }
    }
}
