package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.SaglasnostServis;
import com.spring.rest.xmlproj.obj.liste.Saglasnosti;
import com.spring.rest.xmlproj.obj.saglasnost.Saglasnost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "gradjani/saglasnosti", produces = {"application/xml"})
public class SaglasnostVebServis {

    private final SaglasnostServis saglasnostServis;

    @Autowired
    public SaglasnostVebServis(SaglasnostServis saglasnostServis) {
        this.saglasnostServis = saglasnostServis;
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
}
