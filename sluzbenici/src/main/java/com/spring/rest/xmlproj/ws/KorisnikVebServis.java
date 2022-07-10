package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.KorisnikServis;
import com.spring.rest.xmlproj.dto.PrijavaDTO;
import com.spring.rest.xmlproj.obj.korisnik.Korisnik;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "sluzbenici/korisnici", produces = {"application/xml"})
public class KorisnikVebServis {

    private final KorisnikServis korisnikServis;

    @Autowired
    public KorisnikVebServis(KorisnikServis korisnikServis) {
        this.korisnikServis = korisnikServis;
    }

    @PostMapping("/prijava")
    public ResponseEntity<?> prijava(@RequestBody PrijavaDTO dto){
        if(dto != null){
            Korisnik prijavljeni = this.korisnikServis.dobaviPoId(dto.getEmail());
            if(prijavljeni.getLozinka().equals(dto.getLozinka())) return new ResponseEntity<>(prijavljeni, HttpStatus.OK);
            else return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/registracija")
    public ResponseEntity<?> registracija(@RequestBody Korisnik korisnik){
        if (korisnik != null) {
            this.korisnikServis.upis(korisnik);
            return new ResponseEntity<>(korisnik, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
