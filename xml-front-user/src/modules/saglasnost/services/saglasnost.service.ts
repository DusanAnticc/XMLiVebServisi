import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');
@Injectable({
  providedIn: 'root',
})
export class SaglasnostService {
  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) {}

  create(saglasnost: any): Observable<any> {
    console.log('majmun/saglasnost');
    console.log(saglasnost);

    var xmlDoc = this.parser.parseFromString(o2x(saglasnost), 'text/xml');
    console.log(saglasnost);
    const licniPodaciNode = xmlDoc?.getElementsByTagName('Licni_podaci')[0];
    console.log(xmlDoc);
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
    console.log('majmun/saglasnost');
    return this.http.post<any>('/api/gradjani/saglasnosti/upis', xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json',
    });
  }
}
