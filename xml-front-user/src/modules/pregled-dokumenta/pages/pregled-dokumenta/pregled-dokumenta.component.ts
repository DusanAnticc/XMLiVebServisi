import { Component, OnInit } from '@angular/core';
import { InteresovanjeService } from 'src/modules/interesovanje/services/interesovanje.service';
import { SaglasnostService } from 'src/modules/saglasnost/services/saglasnost.service';
import * as x2js from 'xml2js';
import * as xml2js from 'xml2js';

@Component({
  selector: 'app-pregled-dokumenta',
  templateUrl: './pregled-dokumenta.component.html',
  styleUrls: ['./pregled-dokumenta.component.scss']
})
export class PregledDokumentaComponent implements OnInit {

  interesovanja: any = [];
  saglasnosti: any = [];
  displayedColumns: string[] = ['dokument','jmbg', 'datum'];
  parser = new x2js.Parser();
  constructor(
    private interesovanjeService: InteresovanjeService,
    private saglasnostService: SaglasnostService
    ) { }

  ngOnInit(): void {
    this.getInteresovanje();
    this.getSaglasnost();
  }

  getInteresovanje(): void {
    const _this = this;
    this.interesovanjeService.getAllInteresovanjeByEmail().subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          console.log(result);
          _this.interesovanja = result.interesovanja["ns4:Interesovanje"];
        })
      }
    )
  }

  getSaglasnost(): void {
    const _this = this;
    this.saglasnostService.getAllSaglasnostiByEmail().subscribe(
      (result) => {
        this.parser.parseString(result, function(err: any, res: any) {
          result = res;
          console.log(result);
          _this.saglasnosti = result.saglasnosti["ns4:Saglasnost"];
        })
      }
    )
  }
}
