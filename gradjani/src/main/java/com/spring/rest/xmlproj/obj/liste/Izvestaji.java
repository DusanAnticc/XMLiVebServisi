package com.spring.rest.xmlproj.obj.liste;


import com.spring.rest.xmlproj.obj.izvestaj.Izvestaj;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Izvestaj.class)
public class Izvestaji {

    private List<Izvestaj> izvestajList;

    @XmlAnyElement
    public List<Izvestaj> getIzvestajList() {
        return izvestajList;
    }

    public void setIzvestajList(List<Izvestaj> izvestajList) {
        this.izvestajList = izvestajList;
    }

    public Izvestaji(List<Izvestaj> izvestajList) {
        this.izvestajList = izvestajList;
    }

    public Izvestaji(){
        this.izvestajList = new ArrayList<>();
    }
}
