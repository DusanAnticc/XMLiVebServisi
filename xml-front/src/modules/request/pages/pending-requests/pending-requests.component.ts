import { Component, OnInit } from '@angular/core';
import { PendingRequest } from 'src/app/model/PendingRequest';

@Component({
  selector: 'app-pending-requests',
  templateUrl: './pending-requests.component.html',
  styleUrls: ['./pending-requests.component.scss']
})
export class PendingRequestsComponent implements OnInit {
  pendingRequests: PendingRequest[] = [];

  constructor() { }

  ngOnInit(): void {

  }

  confirmRequest(id: number) {

  }

  declineRequest(id: number) {

  }

}
