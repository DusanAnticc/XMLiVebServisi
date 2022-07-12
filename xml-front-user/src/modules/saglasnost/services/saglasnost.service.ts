import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PrijavaService } from 'src/modules/prijava/services/prijava.service';

var o2x = require('object-to-xml');
@Injectable({
  providedIn: 'root',
})
export class SaglasnostService {
  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient, public prijavaService: PrijavaService) {}

  getAllSaglasnostiByEmail(): Observable<any> {
    var email = this.prijavaService.getLoggedIn()["ns2:Korisnik"]["Licni_podaci"][0]["Kontakt"][0]["Email"];
    return this.http.get<any>("/api/gradjani/saglasnosti/sve/"+email,{
      headers: this.headers,
      responseType: 'test/xml' as 'json',
    })
  }

  create(saglasnost: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(saglasnost), 'text/xml');
    console.log(saglasnost);
    const licniPodaciNode = xmlDoc?.getElementsByTagName('Licni_podaci')[0];
    licniPodaciNode?.setAttribute(
      'xmlns',
      'http://www.xmlproj.rs/gradjanin/licniPodaci'
    );
    const saglasnostNode = xmlDoc?.getElementsByTagName('Saglasnost')[0];
    saglasnostNode?.setAttribute(
      'xmlns',
      'http://www.xmlproj.rs/gradjanin/saglasnost'
    );
    saglasnostNode?.setAttribute(
      'xmlns:xsi',
      'http://www.w3.org/2001/XMLSchema-instance'
    );
    saglasnostNode?.setAttribute(
      'xmlns:pred',
      'http://http://www.xmlproj.rs/saglasnost/predikati/'
    );
    saglasnostNode?.setAttribute(
      'xsi:schemaLocation',
      'http://www.xmlproj.rs/gradjanin/saglasnost schema/saglasnost.xsd'
    );

    var xmlString = this.serializer.serializeToString(xmlDoc);
    return this.http.post<any>('/api/gradjani/saglasnosti/upis', xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json',
    });
  }
}
