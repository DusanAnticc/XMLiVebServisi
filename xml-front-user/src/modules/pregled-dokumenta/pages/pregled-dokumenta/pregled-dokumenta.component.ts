import { Component, OnInit } from '@angular/core';
import { InteresovanjeService } from 'src/modules/interesovanje/services/interesovanje.service';
import { SaglasnostService } from 'src/modules/saglasnost/services/saglasnost.service';
import { ZahtevService } from 'src/modules/zahtev/services/zahtev.service';
import { PotvrdaService } from 'src/modules/potvrda/potvrda.service';
import * as x2js from 'xml2js';

@Component({
  selector: 'app-pregled-dokumenta',
  templateUrl: './pregled-dokumenta.component.html',
  styleUrls: ['./pregled-dokumenta.component.scss'],
})
export class PregledDokumentaComponent implements OnInit {
  interesovanja: any = [];
  zahtevi: any = [];
  saglasnosti: any = [];
  potvrde: any = [];
  displayedColumns: string[] = ['dokument', 'jmbg', 'datum'];
  parser = new x2js.Parser();
  constructor(
    private interesovanjeService: InteresovanjeService,
    private saglasnostService: SaglasnostService,
    private zahtevService: ZahtevService,
    private potvrdaService: PotvrdaService
  ) {}

  ngOnInit(): void {
    this.getInteresovanje();
    this.getSaglasnost();
    this.getZahtev();
  }

  getInteresovanje(): void {
    const _this = this;
    this.interesovanjeService
      .getAllInteresovanjeByEmail()
      .subscribe((result) => {
        this.parser.parseString(result, function (err: any, res: any) {
          result = res;
          _this.interesovanja = result.interesovanja['ns4:Interesovanje'];
        });
      });
  }

  getSaglasnost(): void {
    const _this = this;
    this.saglasnostService.getAllSaglasnostiByEmail().subscribe((result) => {
      this.parser.parseString(result, function (err: any, res: any) {
        result = res;
        console.log(result);
        _this.saglasnosti = result.saglasnosti['ns4:Saglasnost'];
      });
    });
  }

  getZahtev(): void {
    const _this = this;
    this.zahtevService.getAllZahteviByEmail().subscribe((result) => {
      this.parser.parseString(result, function (err: any, res: any) {
        result = res;
        console.log(result);
        _this.zahtevi = result.zahtevi['ns4:Zahtev'];
      });
    });
  }

  getPotvrde(): void {
    const _this = this;
    this.potvrdaService.getAllPotvrdeByEmail().subscribe((result) => {
      this.parser.parseString(result, function (err: any, res: any) {
        result = res;
        console.log(result);
        _this.saglasnosti = result.saglasnosti['ns4:Saglasnost'];
      });
    });
  }
}
