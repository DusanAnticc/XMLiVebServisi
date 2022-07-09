package com.spring.rest.xmlproj.obj.liste;

import com.spring.rest.xmlproj.obj.korisnik.Korisnik;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Korisnik.class)
public class Korisnici {

    private List<Korisnik> korisnikList;

    @XmlAnyElement
    public List<Korisnik> getkorisnikList() {
        return korisnikList;
    }

    public void setkorisnikList(List<Korisnik> korisnikList) {
        this.korisnikList = korisnikList;
    }

    public Korisnici(List<Korisnik> korisnikList) {
        this.korisnikList = korisnikList;
    }

    public Korisnici(){
        this.korisnikList = new ArrayList<>();
    }
}
