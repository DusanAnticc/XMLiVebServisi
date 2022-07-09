package com.spring.rest.xmlproj.obj.liste;


import com.spring.rest.xmlproj.obj.zahtev.Zahtev;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Zahtev.class)
public class Zahtevi {

    private List<Zahtev> zahtevList;

    @XmlAnyElement
    public List<Zahtev> getZahtevList() {
        return zahtevList;
    }

    public void setZahtevList(List<Zahtev> zahtevList) {
        this.zahtevList = zahtevList;
    }

    public Zahtevi(List<Zahtev> zahtevList) {
        this.zahtevList = zahtevList;
    }

    public Zahtevi(){
        this.zahtevList = new ArrayList<>();
    }
}
