package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IIzvestajServis;
import com.spring.rest.xmlproj.obj.Izvestaj;
import com.spring.rest.xmlproj.repo.IzvestajRepo;
import org.apache.jena.base.Sys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IzvestajServis implements IIzvestajServis {

    private final IzvestajRepo izvestajRepo;

    @Autowired
    public IzvestajServis(IzvestajRepo izvestajRepo) {
        this.izvestajRepo = izvestajRepo;
    }

    public void upisIzvestaja(Izvestaj izvestaj){
        try {
            this.izvestajRepo.upis(izvestaj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Izvestaj dobaviIzvestajPoId(String id){
        try {
            Izvestaj izvestaj = this.izvestajRepo.dobaviPoId(id);
            System.out.println(izvestaj);
            return izvestaj;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Izvestaj> dobaviSveIzvestaje(){
        try {
            return this.izvestajRepo.dobaviSve();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
