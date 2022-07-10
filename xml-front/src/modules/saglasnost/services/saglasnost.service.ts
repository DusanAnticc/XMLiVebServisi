import { HttpClient, HttpHeaders } from '@angular/common/http';

export class SaglasnostService {
  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });
  private parser = new DOMParser();
  private serializer = new XMLSerializer();

  constructor(public http: HttpClient) {}

  create(saglasnost: any, jmbg: any) {}
}
