import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');
@Injectable({
  providedIn: 'root'
})
export class ConsentService {

  public headers = new HttpHeaders({ "Content-Type": "application/xml"});
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }

  create(saglasnost: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(saglasnost), "text/xml");
    const saglasnostNode = xmlDoc?.getElementsByTagName("Radnikov_deo")[0]
    // saglasnostNode?.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance")
    // saglasnostNode?.setAttribute("xsi:schemaLocation", "http://www.xmlproj.rs/gradjanin/saglasnost schema/saglasnost.xsd")
    saglasnostNode?.setAttribute('xmlns','http://www.xmlproj.rs/gradjanin/saglasnost');
    // saglasnostNode?.setAttribute('xmlns:pred','http://http://www.xmlproj.rs/saglasnost/predikati/');
    // const licniPodaciNode = xmlDoc?.getElementsByTagName('Licni_podaci')[0];
    // licniPodaciNode?.setAttribute('xmlns','http://www.xmlproj.rs/gradjanin/licniPodaci');
    
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("api/sluzbenici/saglasnosti/upis", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}
