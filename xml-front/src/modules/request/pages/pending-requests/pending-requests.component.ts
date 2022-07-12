import { Component, OnInit } from '@angular/core';
import { PendingRequest } from 'src/app/model/PendingRequest';
import * as x2js from 'xml2js';
import { RequestService } from '../../service/request.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ResponseModalComponent } from '../../response-modal/response-modal.component';

@Component({
  selector: 'app-pending-requests',
  templateUrl: './pending-requests.component.html',
  styleUrls: ['./pending-requests.component.scss']
})
export class PendingRequestsComponent implements OnInit {
  pendingRequests: PendingRequest[] = [];
  parser = new x2js.Parser();
  domParser = new DOMParser();
  o2x = require('object-to-xml');

  zahtevi: any[] = [];

  constructor( public dialog: MatDialog, public zahtevService: RequestService) {
    const that = this;
    this.zahtevService.getZahteve().subscribe(
      (result) => {
        this.parser.parseString(result, function (err: any, res: any) {
          result = res;
          that.zahtevi = result.zahtevi["ns4:Zahtev"];
        })
      }
    )
  }

  ngOnInit(): void {

  }

  confirmRequest(id: number) {

    var obj = {
      razlogdto: {
        '#': {
          razlog: "",
          odobren: true,
          zahtev: id,
        }
      }
    };
    this.zahtevService.sendResponse(obj).subscribe(
      (result) => {
        this.parser.parseString(result, function (err: any, res: any) {
        });
        this.zahtevi = this.zahtevi.filter(zahev => zahev.id !== id);
      }
    )
  }

  declineRequest(id: number) {
  
      const dialogConfig = new MatDialogConfig();
      dialogConfig.disableClose = true;
      dialogConfig.autoFocus = true;
      
      const d = this.dialog.open(ResponseModalComponent, dialogConfig);
      d.afterClosed().subscribe((result) => {
        if (result.event === 'send') {
          var obj = {
            razlogdto: {
              '#': {
                razlog: result.data,
                odobren: false,
                zahtev: id,
              }
            }
          };
  
          this.zahtevService.sendResponse(obj).subscribe(
            (result) => {
              this.parser.parseString(result, function (err: any, res: any) {
              });
              this.zahtevi = this.zahtevi.filter(zahtev => zahtev.id !== id);
            }
          )
        }
      });
  
    }
  }

