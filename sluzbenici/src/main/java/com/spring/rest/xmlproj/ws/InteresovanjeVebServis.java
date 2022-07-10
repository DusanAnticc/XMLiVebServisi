package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.InteresovanjeServis;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.obj.liste.Interesovanja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "sluzbenici/interesovanja", produces = {"application/xml" })
public class InteresovanjeVebServis {

    private final InteresovanjeServis interesovanjeServis;

    @Autowired
    public InteresovanjeVebServis(InteresovanjeServis interesovanjeServis) {
        this.interesovanjeServis = interesovanjeServis;
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
}
