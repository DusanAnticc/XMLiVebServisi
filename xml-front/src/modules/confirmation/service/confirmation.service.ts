import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class ConfirmationService {

  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  create(potvrda: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(potvrda), "text/xml");
    const potvrdaNode = xmlDoc?.getElementsByTagName("Potvrda")[0]
    potvrdaNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
    potvrdaNode?.setAttribute("xsi:schemaLocation", "http://www.xmlproj.rs/gradjanin/potvrda schema/potvrda.xsd")
    potvrdaNode?.setAttribute('xmlns','http://www.xmlproj.rs/gradjanin/potvrda');
    potvrdaNode?.setAttribute('xmlns:pred','http://http://www.xmlproj.rs/potvrda/predikati/');
    const licniPodaciNode = xmlDoc?.getElementsByTagName('Licni_podaci')[0];
    licniPodaciNode?.setAttribute('xmlns','http://www.xmlproj.rs/gradjanin/licniPodaci');
    
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("api/sluzbenici/potvrde/upis", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}
