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
import com.spring.rest.xmlproj.bservisi.impl.PotvrdaServis;
import com.spring.rest.xmlproj.obj.korisnik.Korisnik;
import com.spring.rest.xmlproj.obj.liste.Potvrde;
import com.spring.rest.xmlproj.obj.potvrda.Potvrda;

@RestController
@CrossOrigin
@RequestMapping(value = "api/gradjani/potvrde", produces = {"application/xml"})
public class PotvrdaVebServis {

    private final PotvrdaServis potvrdaServis;
    private final KorisnikServis korisnikServis;

    @Autowired
    public PotvrdaVebServis(PotvrdaServis potvrdaServis, KorisnikServis korisnikServis) {
        this.potvrdaServis = potvrdaServis;
        this.korisnikServis = korisnikServis;
    }

    @GetMapping("")
    public ResponseEntity<?> dobaviSvePotvrde() {
        List<Potvrda> svePotvrde = this.potvrdaServis.dobaviSve();
        if(svePotvrde.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new Potvrde(svePotvrde), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> dobaviJednuPotvrdu(@PathVariable("id") String id) {
        Potvrda potvrda = this.potvrdaServis.dobaviPoId(id);

        if(potvrda != null){
            return new ResponseEntity<>(potvrda, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upis")
    public ResponseEntity<?> upisPotvrde(@RequestBody Potvrda potvrda) {
        if (potvrda != null) {
            this.potvrdaServis.upis(potvrda);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sve/{email}")
    public ResponseEntity<?> potvrdeKorisnika(@PathVariable String email){
        if(email == null || email.equals("")) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        Korisnik korisnik = this.korisnikServis.dobaviPoId(email);

        List<Potvrda> korisnikovePotvrde = this.potvrdaServis.dobaviSve().stream().
        filter(p -> p.getLicniPodaci().getJmbg().equals(korisnik.getLicniPodaci().getJmbg()) ||
                    p.getLicniPodaci().getKontakt().getEmail().equals(korisnik.getLicniPodaci().getKontakt().getEmail())).
        collect(Collectors.toList());

        return new ResponseEntity<>(new Potvrde(korisnikovePotvrde), HttpStatus.OK);
    }
}
