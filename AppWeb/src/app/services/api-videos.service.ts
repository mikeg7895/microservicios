import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiVideosService {
  private baseUrl: string = "http://localhost:5206/api";

  constructor(private http: HttpClient) { }

  getData(endpoint: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${endpoint}`);
  }

  download(endpoint: string, id: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${endpoint}/download/${id}`, { responseType: 'blob' })
  }
}
