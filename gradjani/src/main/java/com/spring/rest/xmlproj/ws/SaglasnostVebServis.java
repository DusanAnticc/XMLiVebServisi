package com.spring.rest.xmlproj.ws;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.xmlproj.bservisi.impl.KorisnikServis;
import com.spring.rest.xmlproj.bservisi.impl.SaglasnostServis;
import com.spring.rest.xmlproj.obj.korisnik.Korisnik;
import com.spring.rest.xmlproj.obj.liste.Saglasnosti;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;

@RestController
@CrossOrigin
@RequestMapping(value = "api/gradjani/saglasnosti", produces = {"application/xml"})
public class SaglasnostVebServis {

    private final SaglasnostServis saglasnostServis;
    private final KorisnikServis korisnikServis;

    @Autowired
    public SaglasnostVebServis(SaglasnostServis saglasnostServis, KorisnikServis korisnikServis) {
        this.saglasnostServis = saglasnostServis;
        this.korisnikServis = korisnikServis;
    }

    @PostMapping("/upis")
    public ResponseEntity<?> upisSaglasnosti(@RequestBody Saglasnost saglasnost) {
        if(saglasnost != null){
            this.saglasnostServis.upis(saglasnost);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> dobaviJednuSaglasnost(@PathVariable("id") String id) {
        Saglasnost saglasnost = this.saglasnostServis.dobaviPoId(id);

        if(saglasnost == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(saglasnost, HttpStatus.OK);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> dobaviSveSaglasnosti() {
        List<Saglasnost> sveSaglasnosti = this.saglasnostServis.dobaviSve();
        if(sveSaglasnosti.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new Saglasnosti(sveSaglasnosti), HttpStatus.OK);
        }
    }

    @GetMapping("/sve/{email}")
    public ResponseEntity<?> saglasnostiKorisnika(@PathVariable String email){
        if(email == null || email.equals("")) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        Korisnik korisnik = this.korisnikServis.dobaviPoId(email);

        List<Saglasnost> korisnikoveSaglasnosti = this.saglasnostServis.dobaviSve().stream().
        filter(s -> s.getPacijentovDeo().getLicniPodaci().getJmbg().equals(korisnik.getLicniPodaci().getJmbg()) ||
                    s.getPacijentovDeo().getLicniPodaci().getKontakt().getEmail().equals(korisnik.getLicniPodaci().getKontakt().getEmail())).
        collect(Collectors.toList());

        return new ResponseEntity<>(new Saglasnosti(korisnikoveSaglasnosti), HttpStatus.OK);
    }

    @GetMapping("/poJmbg/{jmbg}")
    public ResponseEntity<?> dobaviSaglsnostPoJmbgu(@PathVariable String jmbg){
        Saglasnost saglasnost = this.saglasnostServis.dobaviPoJmbgu(jmbg);

        if(saglasnost != null) return new ResponseEntity<>(saglasnost, HttpStatus.OK);
        else return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
}
