package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.PotvrdaServis;
import com.spring.rest.xmlproj.obj.liste.Potvrde;
import com.spring.rest.xmlproj.obj.potvrda.Potvrda;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/sluzbenici/potvrde", produces = {"application/xml"})
public class PotvrdaVebServis {

    private final PotvrdaServis potvrdaServis;
    private final RestTemplate restTemplate;

    @Autowired
    public PotvrdaVebServis(PotvrdaServis potvrdaServis, RestTemplate restTemplate) {
        this.potvrdaServis = potvrdaServis;
        this.restTemplate = restTemplate;
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
            this.slanjePotvrdeGradjaninu(potvrda);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    public void slanjePotvrdeGradjaninu(Potvrda entitet){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<Potvrda> request = new HttpEntity<Potvrda>(entitet, headers);

        ResponseEntity<Potvrda> entity = restTemplate.postForEntity("http://localhost:8080/api/gradjani/potvrde/upis", request, Potvrda.class);
    }
}
