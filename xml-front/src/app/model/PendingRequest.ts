export class PendingRequest {
    id: number;
    nameAndSurname: string;
    jmbg: string;
    date: string;
    reason: string;
  
    constructor(
      id: number,
      nameAndSurname: string,
      jmbg: string,
      date: string,
      reason: string
    ) {
      this.id = id;
      this.nameAndSurname = nameAndSurname;
      this.jmbg = jmbg;
      this.date = date;
      this.reason = reason;
    }
  }