import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PrijavaService } from 'src/modules/prijava/services/prijava.service';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root',
})
export class ZahtevService {
  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient, public prijavaService: PrijavaService) {}

  getAllZahteviByEmail(): Observable<any> {
    var email =
      this.prijavaService.getLoggedIn()['ns2:Korisnik']['Licni_podaci'][0][
        'Kontakt'
      ][0]['Email'];
    return this.http.get<any>('/api/gradjani/zahtevi/svi/' + email, {
      headers: this.headers,
      responseType: 'test/xml' as 'json',
    });
  }

  create(zahtev: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(zahtev), 'text/xml');
    const licniPodaciNode = xmlDoc?.getElementsByTagName('Licni_podaci')[0];
    licniPodaciNode?.setAttribute(
      'xmlns',
      'http://www.xmlproj.rs/gradjanin/licniPodaci'
    );
    const zahtevNode = xmlDoc?.getElementsByTagName('Zahtev')[0];
    zahtevNode?.setAttribute('xmlns', 'http://www.xmlproj.rs/gradjanin/zahtev');
    zahtevNode?.setAttribute(
      'xmlns:xsi',
      'http://www.w3.org/2001/XMLSchema-instance'
    );
    zahtevNode?.setAttribute(
      'xsi:schemaLocation',
      'http://www.xmlproj.rs/gradjanin/zahtev schema/zahtev.xsd'
    );
    zahtevNode?.setAttribute(
      'xmlns:pred',
      'http://http://www.xmlproj.rs/saglasnost/predikati/'
    );

    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>('/api/gradjani/zahtevi/upis', xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json',
    });
  }
}
