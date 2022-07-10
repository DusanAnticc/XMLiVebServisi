package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.PotvrdaServis;
import com.spring.rest.xmlproj.obj.liste.Potvrde;
import com.spring.rest.xmlproj.obj.potvrda.Potvrda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "sluzbenici/potvrde", produces = {"application/xml"})
public class PotvrdaVebServis {

    private final PotvrdaServis potvrdaServis;

    @Autowired
    public PotvrdaVebServis(PotvrdaServis potvrdaServis) {
        this.potvrdaServis = potvrdaServis;
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
}
