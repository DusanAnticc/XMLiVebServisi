package com.spring.rest.xmlproj.dto;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "odobren",
        "razlog"
})
@XmlRootElement(name = "Odgovor_Zahtev_DTO")
public class OdgovorZahtevDTO {

    @XmlElement(required = true)
    private String odobren;
    @XmlElement
    private String razlog;

    public String getOdobren() {
        return odobren;
    }

    public void setOdobren(String odobren) {
        this.odobren = odobren;
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public OdgovorZahtevDTO(String odobren, String razlog) {
        this.odobren = odobren;
        this.razlog = razlog;
    }

    public OdgovorZahtevDTO(){
        this.odobren = "";
        this.razlog = "";
    }
}
