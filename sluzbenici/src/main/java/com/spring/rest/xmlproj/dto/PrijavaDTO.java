package com.spring.rest.xmlproj.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "email",
        "lozinka"
})
@XmlRootElement(name = "Prijava_dto")
public class PrijavaDTO {

    @XmlElement(required = true)
    private String email;

    @XmlElement(required = true)
    private String lozinka;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public PrijavaDTO(String email, String lozinka) {
        this.email = email;
        this.lozinka = lozinka;
    }

    public PrijavaDTO(){
        this.email = "";
        this.lozinka = "";
    }
}
