package com.spring.rest.xmlproj.obj.liste;

import com.spring.rest.xmlproj.obj.potvrda.Potvrda;

import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Potvrda.class)
public class Potvrde {

    private List<Potvrda> potvrdaList;

    @XmlAnyElement
    public List<Potvrda> getPotvrdaList() {
        return potvrdaList;
    }

    public void setPotvrdaList(List<Potvrda> potvrdaList) {
        this.potvrdaList = potvrdaList;
    }

    public Potvrde(List<Potvrda> potvrdaList) {
        this.potvrdaList = potvrdaList;
    }

    public Potvrde(){
        this.potvrdaList = new ArrayList<>();
    }
}
