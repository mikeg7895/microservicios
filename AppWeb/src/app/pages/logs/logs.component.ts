import { Component } from '@angular/core';
import { SoapLogsService } from '../../services/soap-logs.service';

@Component({
  selector: 'app-logs',
  standalone: true,
  imports: [],
  templateUrl: './logs.component.html',
  styleUrl: './logs.component.css'
})
export class LogsComponent {
  data: any[] = [];

  constructor(private soapService: SoapLogsService){
    const soapRequest = `
      <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:gs="http://softwarearchitecture/project/logs">
        <soapenv:Header/>
        <soapenv:Body>
            <gs:getLogsRequest/>
        </soapenv:Body>
      </soapenv:Envelope>
    `;
    this.soapService.sendSoapRequest(soapRequest).subscribe(
      response => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(response, 'text/xml');
        const logs = xml.getElementsByTagName('ns2:logs');
        const parsedLogs = [];

        for (let i = 0; i < logs.length; i++) {
          const log = logs[i];

          const id = log.getElementsByTagName('ns2:id')[0]?.textContent || null;
          const mensaje = log.getElementsByTagName('ns2:mensaje')[0]?.textContent || null;
          const fecha = log.getElementsByTagName('ns2:fecha')[0]?.textContent || null;

          parsedLogs.push({ id, mensaje, fecha });
        }
        parsedLogs.sort((a, b) => {
          return parseInt(b.id!) - parseInt(a.id!);
        });

        this.data = parsedLogs;
      },
      error => {
        console.error(error);
      }
    );
  }
}
