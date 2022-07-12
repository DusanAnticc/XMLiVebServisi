package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.KorisnikServis;
import com.spring.rest.xmlproj.bservisi.impl.ZahtevServis;
import com.spring.rest.xmlproj.obj.liste.Zahtevi;
import com.spring.rest.xmlproj.obj.zahtev.Zahtev;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spring.rest.xmlproj.obj.korisnik.Korisnik;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "api/gradjani/zahtevi", produces = {"application/xml"})
public class ZahtevVebServis {

    private final ZahtevServis zahtevServis;
    private final KorisnikServis korisnikServis;
    @Autowired
    public ZahtevVebServis(ZahtevServis zahtevServis, KorisnikServis korisnikServis) {
        this.zahtevServis = zahtevServis;
        this.korisnikServis = korisnikServis;
    }

    @GetMapping("")
    public ResponseEntity<?> dobaviSveZahteve() {
        List<Zahtev> sviZahtevi = this.zahtevServis.dobaviSve();

        if(sviZahtevi.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new Zahtevi(sviZahtevi), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> dobaviJedanZahtev(@PathVariable("id") String id) {
        Zahtev zahtev = this.zahtevServis.dobaviPoId(id);

        if(zahtev != null){
            return new ResponseEntity<>(zahtev, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upis")
    public ResponseEntity<?> upisZahtev(@RequestBody Zahtev zahtev) {
        if (zahtev != null) {
            this.zahtevServis.upis(zahtev);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/svi/{email}")
    public ResponseEntity<?> zahteviKorisnika(@PathVariable String email){
        if(email == null || email.equals("")) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        Korisnik korisnik = this.korisnikServis.dobaviPoId(email);

        List<Zahtev> korisnikoviZahtevi = this.zahtevServis.dobaviSve().stream().
        filter(z -> z.getLicniPodaci().getKontakt().getEmail().equals(korisnik.getLicniPodaci().getKontakt().getEmail())).
        collect(Collectors.toList());

        return new ResponseEntity<>(new Zahtevi(korisnikoviZahtevi), HttpStatus.OK);
    }
}
