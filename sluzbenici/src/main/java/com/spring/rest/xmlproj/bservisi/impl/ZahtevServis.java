package com.spring.rest.xmlproj.bservisi.impl;

import com.spring.rest.xmlproj.bservisi.IZahtevServis;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost.RadnikovDeo.Vakcine.Vakcina;
import com.spring.rest.xmlproj.obj.sertifikat.LicniPodaci;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat.QRKod;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat.Vakcinacije;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat.Vakcinacije.Vakcinacija.DatumPrimanja;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat.Vakcinacije.Vakcinacija.Naziv;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat.Vakcinacije.Vakcinacija;
import com.spring.rest.xmlproj.obj.sertifikat.TKontakt;
import com.spring.rest.xmlproj.obj.zahtev.Zahtev;
import com.spring.rest.xmlproj.rdf.UpisMeta;
import com.spring.rest.xmlproj.repo.ZahtevRepo;
import com.spring.rest.xmlproj.util.FusekiAuthenticationUtilities;
import com.spring.rest.xmlproj.util.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class ZahtevServis implements IZahtevServis {
    @Value("${configPath}")
    private String configPath;

    private final ZahtevRepo zahtevRepo;
    private final EmailServis emailServis;
    private final RestTemplate restTemplate;

    @Autowired
    public ZahtevServis(ZahtevRepo zahtevRepo, EmailServis emailServis, RestTemplate restTemplate) {
        this.zahtevRepo = zahtevRepo;
        this.emailServis = emailServis;
        this.restTemplate = restTemplate;
    }


    @Override
    public void upis(Zahtev entitet) {
        try {
            if(entitet.getSifra() == null || entitet.getSifra().equals(""))
                entitet.setSifra(RandomString.getAlphaNumericString(8).toUpperCase());
            entitet.setRel("pred:potvrda");
            entitet.setAbout("http://www.xmlproj.rs/gradjanin/zahtev/"+entitet.getSifra());
            entitet.getRazlog().setProperty("pred:razlog");
            entitet.getMesto().setProperty("pred:mesto");
            entitet.getDatumPodnosenja().setProperty("pred:podnet");
            this.zahtevRepo.upis(entitet);
            this.zahtevRepo.generisiXML(entitet);
            UpisMeta.run(FusekiAuthenticationUtilities.loadProperties(), "/metadata", configPath+"/data/xml/zahtevi/"+entitet.getSifra()+".xml", configPath+"/data/rdf/zahtevi/"+entitet.getSifra()+".rdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Zahtev dobaviPoId(String id) {
        try {
            return this.zahtevRepo.dobaviPoId(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Zahtev> dobaviSve() {
        try {
            return this.zahtevRepo.dobaviSve();
        } catch (Exception e) {
            return new ArrayList<Zahtev>();
        }
    }

    public void prihvatanjeZahteva(String jmbg){
        Saglasnost saglasnost = this.dobavljanjeSaglasnostiOdGradjanina(jmbg);
        Sertifikat sertifikat = new Sertifikat();

        LocalDateTime localDatetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        String formattedDate = localDatetime.format(formatter);
        try {
            XMLGregorianCalendar calendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(formattedDate);
            sertifikat.setVremeIzdavanja(calendar);
        } catch (DatatypeConfigurationException e) {
            e.printStackTrace();
        }
        sertifikat.setLicniPodaci(new LicniPodaci());

        sertifikat.setQRKod(new QRKod());

        sertifikat.getLicniPodaci().setJmbg(jmbg);
        sertifikat.getLicniPodaci().setIme(saglasnost.getPacijentovDeo().getLicniPodaci().getIme());
        sertifikat.getLicniPodaci().setPrezime(saglasnost.getPacijentovDeo().getLicniPodaci().getPrezime());
        sertifikat.getLicniPodaci().setDatumRodjenja(saglasnost.getPacijentovDeo().getLicniPodaci().getDatumRodjenja());
        sertifikat.getLicniPodaci().setPol(saglasnost.getPacijentovDeo().getLicniPodaci().getPol());
        sertifikat.getLicniPodaci().setBrPasosa(saglasnost.getPacijentovDeo().getLicniPodaci().getBrPasosa());
        sertifikat.getLicniPodaci().setKontakt(new TKontakt());
        sertifikat.getLicniPodaci().getKontakt().setEmail(saglasnost.getPacijentovDeo().getLicniPodaci().getKontakt().getEmail());
        sertifikat.getLicniPodaci().getKontakt().setBrojFiksnog(saglasnost.getPacijentovDeo().getLicniPodaci().getKontakt().getBrojFiksnog());
        sertifikat.getLicniPodaci().getKontakt().setBrojMobilnog(saglasnost.getPacijentovDeo().getLicniPodaci().getKontakt().getBrojMobilnog());


        sertifikat.setVakcinacije(new Vakcinacije());
        int rbrDoze = 1;
        for(Vakcina v : saglasnost.getRadnikovDeo().getVakcine().getVakcina()){
            Vakcinacija vakcinacija = new Vakcinacija();
            vakcinacija.setDoza(BigInteger.valueOf(rbrDoze));
            vakcinacija.setDatumPrimanja(new DatumPrimanja());
            vakcinacija.getDatumPrimanja().setValue(v.getDatumDavanja());
            vakcinacija.setProizvodjac(v.getProizvodjac());
            vakcinacija.setNaziv(new Naziv());
            vakcinacija.getNaziv().setValue(v.getNaziv().getValue());
            vakcinacija.setUstanova(saglasnost.getRadnikovDeo().getZdravstvenaUstanova().getValue());

            sertifikat.getVakcinacije().getVakcinacija().add(vakcinacija);
            rbrDoze++;
        }

        this.slanjeSertifikataGradjaninu(sertifikat);
    }

    public void odbijanjeZahteva(String jmbg, String razlog){
        Saglasnost saglasnost = this.dobavljanjeSaglasnostiOdGradjanina(jmbg);
        this.emailServis.odbijenZahtev(saglasnost.getPacijentovDeo().getLicniPodaci().getKontakt().getEmail(), razlog);
    }


    public Saglasnost dobavljanjeSaglasnostiOdGradjanina(String jmbg){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        ResponseEntity<Saglasnost> entity = restTemplate.getForEntity("http://localhost:8080/api/gradjani/saglasnosti/poJmbg/"+jmbg, Saglasnost.class);

        return entity.getBody();
    }

    public void slanjeSertifikataGradjaninu(Sertifikat entitet){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<Sertifikat> request = new HttpEntity<Sertifikat>(entitet, headers);

        ResponseEntity<Sertifikat> entity = restTemplate.postForEntity("http://localhost:8080/api/gradjani/sertifikati/upisSlanje", request, Sertifikat.class);
    }
}
