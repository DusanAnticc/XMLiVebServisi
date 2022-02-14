package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.ISaglasnostServis;
import com.spring.rest.xmlproj.obj.Saglasnost;
import com.spring.rest.xmlproj.repo.SaglasnostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaglasnostServis implements ISaglasnostServis {
    private final SaglasnostRepo saglasnostRepo;

    @Autowired
    public SaglasnostServis(SaglasnostRepo saglasnostRepo) {
        this.saglasnostRepo = saglasnostRepo;
    }

    @Override
    public void upis(Saglasnost saglasnost){
        try {
            this.saglasnostRepo.upis(saglasnost);
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
