import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PrijavaService } from 'src/modules/prijava/services/prijava.service';

@Injectable({
  providedIn: 'root',
})
export class PotvrdaService {
  public headers = new HttpHeaders({ 'Content-Type': 'application/xml' });

  constructor(public http: HttpClient, public prijavaService: PrijavaService) {}
  getAllPotvrdeByEmail(): Observable<any> {
    var email =
      this.prijavaService.getLoggedIn()['ns2:Korisnik']['Licni_podaci'][0][
        'Kontakt'
      ][0]['Email'];
    return this.http.get<any>('/api/gradjani/potvrde/sve/' + email, {
      headers: this.headers,
      responseType: 'test/xml' as 'json',
    });
  }
}
