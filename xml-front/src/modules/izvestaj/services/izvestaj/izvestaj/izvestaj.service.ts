import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class IzvestajService {
  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(public http: HttpClient) {}
}
