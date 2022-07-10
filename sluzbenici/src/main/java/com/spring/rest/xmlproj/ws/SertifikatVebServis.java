package com.spring.rest.xmlproj.ws;

import com.spring.rest.xmlproj.bservisi.impl.SertifikatServis;
import com.spring.rest.xmlproj.obj.liste.Sertifikati;
import com.spring.rest.xmlproj.obj.sertifikat.Sertifikat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "sluzbenici/sertifikati", produces = {"application/xml"})
public class SertifikatVebServis {

    private final SertifikatServis sertifikatServis;

    @Autowired
    public SertifikatVebServis(SertifikatServis sertifikatServis) {
        this.sertifikatServis = sertifikatServis;
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
}
