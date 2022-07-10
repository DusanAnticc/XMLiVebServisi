package com.spring.rest.xmlproj.obj.liste;

import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso(Sertifikat.class)
public class Sertifikati {

    private List<Sertifikat> sertifikatList;

    @XmlAnyElement
    public List<Sertifikat> getSertifikatList() {
        return sertifikatList;
    }

    public void setSertifikatList(List<Sertifikat> sertifikatList) {
        this.sertifikatList = sertifikatList;
    }

    public Sertifikati(List<Sertifikat> sertifikatList) {
        this.sertifikatList = sertifikatList;
    }

    public Sertifikati(){
        this.sertifikatList = new ArrayList<>();
    }
}
