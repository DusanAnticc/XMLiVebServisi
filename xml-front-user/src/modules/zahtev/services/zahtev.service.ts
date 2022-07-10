import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class ZahtevService {
  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  create(zahtev: any):  Observable<any> {
    
    var xmlDoc = this.parser.parseFromString(o2x(zahtev), "text/xml");
    const licniPodaciNode = xmlDoc?.getElementsByTagName("Licni_podaci")[0]
    licniPodaciNode?.setAttribute("xmlns", "http://www.xmlproj.rs/gradjanin/licniPodaci")
    const zahtevNode = xmlDoc?.getElementsByTagName("Zahtev")[0]
    zahtevNode?.setAttribute("xmlns", "http://www.xmlproj.rs/gradjanin/zahtev")
    zahtevNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
    zahtevNode?.setAttribute("xsi:schemaLocation", "http://www.xmlproj.rs/gradjanin/zahtev schema/zahtev.xsd")
  
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("/api/gradjani/zahtev/upis", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
    
  }

 
}