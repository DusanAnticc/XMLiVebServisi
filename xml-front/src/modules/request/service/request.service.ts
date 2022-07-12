import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root'
})
export class RequestService {

  public headers = new HttpHeaders({ "Content-Type": "application/xml" });
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) { }


  getZahteve(): Observable<any> {

    return this.http.get<any>("/api/sluzbenici/zahtevi", {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }

  sendResponse(reason: any): Observable<any> {
    var xmlDoc = this.parser.parseFromString(o2x(reason), "text/xml");
    var xmlString = this.serializer.serializeToString(xmlDoc);

    return this.http.post<any>("/api/sluzbenici/zahtev/odgovor", xmlString, {
      headers: this.headers,
      responseType: 'test/xml' as 'json'
    })
  }
}
