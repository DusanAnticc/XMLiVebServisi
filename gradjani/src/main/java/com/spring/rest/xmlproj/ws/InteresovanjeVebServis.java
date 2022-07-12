package com.spring.rest.xmlproj.ws;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.rest.xmlproj.bservisi.impl.InteresovanjeServis;
import com.spring.rest.xmlproj.bservisi.impl.KorisnikServis;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.obj.korisnik.Korisnik;
import com.spring.rest.xmlproj.obj.liste.Interesovanja;
import com.spring.rest.xmlproj.util.RandomString;


@RestController
@CrossOrigin
@RequestMapping(value = "api/gradjani/interesovanja", produces = {"application/xml" })
public class InteresovanjeVebServis {

    private final InteresovanjeServis interesovanjeServis;
    private final KorisnikServis korisnikServis;
    private final RestTemplate restTemplate;

    @Autowired
    public InteresovanjeVebServis(InteresovanjeServis interesovanjeServis, KorisnikServis korisnikServis, RestTemplate restTemplate) {
        this.interesovanjeServis = interesovanjeServis;
        this.korisnikServis = korisnikServis;
        this.restTemplate = restTemplate;
    }

    @GetMapping("")
    public ResponseEntity<?> dobaviSvaInteresovanja() {
        List<Interesovanje> svaInteresovanja = this.interesovanjeServis.dobaviSve();

        if(svaInteresovanja.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new Interesovanja(svaInteresovanja), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> dobaviJednoInteresovanje(@PathVariable("id") String id) {

        Interesovanje interesovanje = this.interesovanjeServis.dobaviPoId(id);
        if(interesovanje != null){
            return new ResponseEntity<>(interesovanje, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upis")
    public ResponseEntity<?> upisInteresovanja(@RequestBody Interesovanje interesovanje) {
        if (interesovanje != null) {
            if(interesovanje.getSifra() == null || interesovanje.getSifra().equals(""))
                interesovanje.setSifra(RandomString.getAlphaNumericString(8).toUpperCase());
            this.interesovanjeServis.upis(interesovanje);
            interesovanje = this.interesovanjeServis.dobaviPoId(interesovanje.getSifra());
            this.slanjeSluzbenicima(interesovanje);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sva/{email}")
    public ResponseEntity<?> interesovanjaKorisnika(@PathVariable String email){
        if(email == null || email.equals("")) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        Korisnik korisnik = this.korisnikServis.dobaviPoId(email);

        List<Interesovanje> korisnikovaInteresovanja = this.interesovanjeServis.dobaviSve().stream().
        filter(i -> i.getLicniPodaci().getJmbg().equals(korisnik.getLicniPodaci().getJmbg()) ||
                    i.getLicniPodaci().getKontakt().getEmail().equals(korisnik.getLicniPodaci().getKontakt().getEmail())).
        collect(Collectors.toList());

        return new ResponseEntity<>(new Interesovanja(korisnikovaInteresovanja), HttpStatus.OK);
    }

    public void slanjeSluzbenicima(Interesovanje entitet){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<Interesovanje> request = new HttpEntity<Interesovanje>(entitet, headers);

        ResponseEntity<Interesovanje> entity = restTemplate.postForEntity("http://localhost:8081/api/sluzbenici/interesovanja/upis", request, Interesovanje.class);
    }
}
