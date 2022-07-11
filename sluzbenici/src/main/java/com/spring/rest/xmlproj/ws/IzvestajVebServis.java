package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.IzvestajServis;
import com.spring.rest.xmlproj.obj.izvestaj.Izvestaj;
import com.spring.rest.xmlproj.obj.liste.Izvestaji;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/sluzbenici/izvestaji", produces = {"application/xml"})
public class IzvestajVebServis {

    private final IzvestajServis izvestajServis;

    @Autowired
    public IzvestajVebServis(IzvestajServis izvestajServis) {
        this.izvestajServis = izvestajServis;
    }

    @GetMapping("")
    public ResponseEntity<?> dobaviSveIzvestaje() {
        List<Izvestaj> sviIzvestaji = this.izvestajServis.dobaviSve();

        if(sviIzvestaji.size() == 0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(new Izvestaji(sviIzvestaji), HttpStatus.OK);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> dobaviJedanIzvestaj(@PathVariable("id") String id) {
        Izvestaj izvestaj = this.izvestajServis.dobaviPoId(id);

        if(izvestaj != null){
            return new ResponseEntity<>(izvestaj, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/upis")
    public ResponseEntity<?> upisIzvestaj(@RequestBody Izvestaj izvestaj) {
        if (izvestaj!= null) {
            this.izvestajServis.upis(izvestaj);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
