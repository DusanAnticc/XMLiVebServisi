package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.KorisnikServis;
import com.spring.rest.xmlproj.bservisi.impl.SertifikatServis;
import com.spring.rest.xmlproj.obj.korisnik.Korisnik;
import com.spring.rest.xmlproj.obj.liste.Sertifikati;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "api/gradjani/sertifikati", produces = {"application/xml"})
public class SertifikatVebServis {

    private final SertifikatServis sertifikatServis;
    private final KorisnikServis korisnikServis;

    @Autowired
    public SertifikatVebServis(SertifikatServis sertifikatServis, KorisnikServis korisnikServis) {
        this.sertifikatServis = sertifikatServis;
        this.korisnikServis = korisnikServis;
    }


    @GetMapping("")
    public ResponseEntity<?> dobaviSveSertifikate() {
        List<Sertifikat> sviSertifikati = this.sertifikatServis.dobaviSve();
        if(sviSertifikati.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new Sertifikati(sviSertifikati), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> dobaviJedanSertifikat(@PathVariable("id") String id) {
        Sertifikat sertifikat = this.sertifikatServis.dobaviPoId(id);

        if(sertifikat != null){
            return new ResponseEntity<>(sertifikat, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upis")
    public ResponseEntity<?> upisSertifikata(@RequestBody Sertifikat sertifikat) {
        if (sertifikat != null) {
            this.sertifikatServis.upis(sertifikat);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/upisSlanje")
    public ResponseEntity<?> upisSertifikataISlanjeGradjaninu(@RequestBody Sertifikat sertifikat) {
        if (sertifikat != null) {
            this.sertifikatServis.upisSlanjeMejl(sertifikat);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/svi/{email}")
    public ResponseEntity<?> sertifikatiKorisnika(@PathVariable String email){
        if(email == null || email.equals("")) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        Korisnik korisnik = this.korisnikServis.dobaviPoId(email);

        List<Sertifikat> korisnikoviSertifikati = this.sertifikatServis.dobaviSve().stream().
        filter(s -> s.getLicniPodaci().getJmbg().equals(korisnik.getLicniPodaci().getJmbg()) ||
                    s.getLicniPodaci().getKontakt().getEmail().equals(korisnik.getLicniPodaci().getKontakt().getEmail())).
        collect(Collectors.toList());

        return new ResponseEntity<>(new Sertifikati(korisnikoviSertifikati), HttpStatus.OK);
    }
}
