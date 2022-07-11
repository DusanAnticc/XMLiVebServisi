import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class RegistracijaService {

  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  register(user: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(user), "text/xml");
    const korisnikNode = xmlDoc?.getElementsByTagName("korisnik")[0]
    const licniPodaciNode = xmlDoc?.getElementsByTagName('Licni_podaci')[0];
    licniPodaciNode?.setAttribute(
      'xmlns',
      'http://www.xmlproj.rs/gradjanin/licniPodaci'
    );
    korisnikNode?.setAttribute("xmlns", "http://www.xmlproj.rs/korisnik")
    korisnikNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
  
    var xmlString = this.serializer.serializeToString(xmlDoc);
    
    return this.http.post<any>("/api/gradjani/korisnici/registracija", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}



