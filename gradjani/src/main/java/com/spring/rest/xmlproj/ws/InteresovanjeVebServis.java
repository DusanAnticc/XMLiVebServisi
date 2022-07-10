package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.InteresovanjeServis;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.obj.liste.Interesovanja;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "api/gradjani/interesovanja", produces = {"application/xml" })
public class InteresovanjeVebServis {

    private final InteresovanjeServis interesovanjeServis;
    private final RestTemplate restTemplate;

    @Autowired
    public InteresovanjeVebServis(InteresovanjeServis interesovanjeServis, RestTemplate restTemplate) {
        this.interesovanjeServis = interesovanjeServis;
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
            this.interesovanjeServis.upis(interesovanje);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
