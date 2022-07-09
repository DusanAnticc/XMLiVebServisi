package com.spring.rest.xmlproj.obj.liste;


import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Saglasnost.class)
public class Saglasnosti {

    private List<Saglasnost> saglasnostList;

    @XmlAnyElement
    public List<Saglasnost> getSaglasnostList() {
        return saglasnostList;
    }

    public void setSaglasnostList(List<Saglasnost> saglasnostList) {
        this.saglasnostList = saglasnostList;
    }

    public Saglasnosti(List<Saglasnost> saglasnostList) {
        this.saglasnostList = saglasnostList;
    }

    public Saglasnosti(){
        this.saglasnostList = new ArrayList<>();
    }
}
