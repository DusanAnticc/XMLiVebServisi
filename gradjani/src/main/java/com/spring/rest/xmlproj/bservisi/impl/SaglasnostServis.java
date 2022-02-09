package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.ISaglasnostServis;
import com.spring.rest.xmlproj.obj.Saglasnost;
import com.spring.rest.xmlproj.repo.SaglasnostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaglasnostServis implements ISaglasnostServis {
    private final SaglasnostRepo saglasnostRepo;

    @Autowired
    public SaglasnostServis(SaglasnostRepo saglasnostRepo) {
        this.saglasnostRepo = saglasnostRepo;
    }

    public void upisSaglasnosti(Saglasnost saglasnost){
        try {
            this.saglasnostRepo.upis(saglasnost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Saglasnost dobaviSaglasnostPoId(String id){
        try {
            Saglasnost saglasnost = this.saglasnostRepo.dobaviPoId(id);
            System.out.println(saglasnost);
            return saglasnost;
        } catch (Exception e) {
            return null;
        }
    }
}
