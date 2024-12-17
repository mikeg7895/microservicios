import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SoapLogsService {
  private soapUrl = 'http://localhost:9000/ws';
  private headers = new HttpHeaders({
    'Content-Type': 'text/xml',
    'Accept': 'text/xml',
  });

  constructor(private http: HttpClient) { }

  sendSoapRequest(soapRequest: string){
    return this.http.post(this.soapUrl, soapRequest, {
      headers: this.headers,
      responseType: 'text'
    });
  }
}
