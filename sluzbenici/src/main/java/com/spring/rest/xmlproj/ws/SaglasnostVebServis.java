package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.SaglasnostServis;
import com.spring.rest.xmlproj.obj.interesovanje.Interesovanje;
import com.spring.rest.xmlproj.obj.liste.Saglasnosti;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/sluzbenici/saglasnosti", produces = {"application/xml"})
public class SaglasnostVebServis {

    private final SaglasnostServis saglasnostServis;
    private final RestTemplate restTemplate;

    @Autowired
    public SaglasnostVebServis(SaglasnostServis saglasnostServis, RestTemplate restTemplate) {
        this.saglasnostServis = saglasnostServis;
        this.restTemplate = restTemplate;
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

    @PutMapping("/imunizacija/{jmbg}")
    public ResponseEntity<?> davanjeDoze(@PathVariable String jmbg, @RequestBody Saglasnost.RadnikovDeo.Vakcine.Vakcina vakcina){
        Saglasnost saglasnost = this.dobavljanjeSaglasnostiOdGradjanina(jmbg);

        saglasnost.getRadnikovDeo().getVakcine().getVakcina().add(vakcina);

        this.azuriranjeGradjaninoveSaglasnosti(saglasnost);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public Saglasnost dobavljanjeSaglasnostiOdGradjanina(String jmbg){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);

        ResponseEntity<Saglasnost> entity = restTemplate.getForEntity("http://localhost:8080/api/gradjani/saglasnosti/poJmbg/"+jmbg, Saglasnost.class);

        return entity.getBody();
    }

    public void azuriranjeGradjaninoveSaglasnosti(Saglasnost entitet){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<Saglasnost> request = new HttpEntity<Saglasnost>(entitet, headers);

        ResponseEntity<Saglasnost> entity = restTemplate.postForEntity("http://localhost:8080/api/gradjani/saglasnosti/upis", request, Saglasnost.class);
    }
}
