import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReportService {
  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(public http: HttpClient) {}
}
