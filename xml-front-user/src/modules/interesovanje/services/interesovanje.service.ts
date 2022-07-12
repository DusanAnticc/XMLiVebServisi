import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PrijavaService } from 'src/modules/prijava/services/prijava.service';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root',
})
export class InteresovanjeService {
  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient, public prijavaService: PrijavaService) {}

  getAllInteresovanjeByEmail(): Observable<any> {
    var email = this.prijavaService.getLoggedIn()["ns2:Korisnik"]["Licni_podaci"][0]["Kontakt"][0]["Email"];
    return this.http.get<any>("/api/gradjani/interesovanja/sva/"+email,{
      headers: this.headers,
      responseType: 'test/xml' as 'json',
    })
  }

  create(interesovanje: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(interesovanje), 'text/xml');
    const licniPodaciNode = xmlDoc?.getElementsByTagName('Licni_podaci')[0];
    licniPodaciNode?.setAttribute(
      'xmlns',
      'http://www.xmlproj.rs/gradjanin/licniPodaci'
    );
    const interesovanjeNode = xmlDoc?.getElementsByTagName('Interesovanje')[0];
    interesovanjeNode?.setAttribute(
      'xmlns',
      'http://www.xmlproj.rs/gradjanin/interesovanje'
    );
    interesovanjeNode?.setAttribute(
      'xmlns:xsi',
      'http://www.w3.org/2001/XMLSchema-instance'
    );
    interesovanjeNode?.setAttribute(
      'xmlns:pred',
      'http://http://www.xmlproj.rs/saglasnost/predikati/'
    );
    interesovanjeNode?.setAttribute(
      'xsi:schemaLocation',
      'http://www.xmlproj.rs/gradjanin/interesovanje schema/interesovanje.xsd'
    );

    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>('/api/gradjani/interesovanja/upis', xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json',
    });
  }
}
