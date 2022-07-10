package com.spring.rest.xmlproj.obj.liste;

import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Interesovanje.class)
public class Interesovanja {

    private List<Interesovanje> interesovanjeList;

    @XmlAnyElement
    public List<Interesovanje> getInteresovanjeList() {
        return interesovanjeList;
    }

    public void setInteresovanjeList(List<Interesovanje> interesovanjeList) {
        this.interesovanjeList = interesovanjeList;
    }

    public Interesovanja(List<Interesovanje> interesovanjeList) {
        this.interesovanjeList = interesovanjeList;
    }

    public Interesovanja(){
        this.interesovanjeList = new ArrayList<>();
    }
}
