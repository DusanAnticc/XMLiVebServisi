import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

var o2x = require('object-to-xml');

@Injectable({
  providedIn: 'root',
})
export class PrijavaService {
  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(public http: HttpClient) {}

  login(obj: any): Observable<any> {
    return this.http.post<any>('api/sluzbenici/korisnici/prijava', o2x(obj), {
      headers: this.headers,
      responseType: 'test/xml' as 'json',
    });
  }

  getLoggedIn() {
    return JSON.parse(localStorage.getItem('korisnik')!);
  }
}
