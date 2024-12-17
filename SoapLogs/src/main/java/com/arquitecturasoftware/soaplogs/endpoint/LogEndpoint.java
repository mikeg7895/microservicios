package com.arquitecturasoftware.soaplogs.endpoint;

import com.arquitecturasoftware.soaplogs.entity.Log;
import com.arquitecturasoftware.soaplogs.service.LogService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import softwarearchitecture.project.logs.LogEntry;
import softwarearchitecture.project.logs.GetLogsRequest;
import softwarearchitecture.project.logs.GetLogsResponse;

import java.util.List;

@Endpoint
public class LogEndpoint {

    private static final String NAMESPACE_URI = "http://softwarearchitecture/project/logs";
    private final LogService logService;

    public LogEndpoint(LogService logService) {
        this.logService = logService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getLogsRequest")
    @ResponsePayload
    public GetLogsResponse getLogs(@RequestPayload GetLogsRequest request) {
        List<Log> logs = logService.getAllLogs();
        GetLogsResponse response = new GetLogsResponse();

        logs.forEach(log -> {
            LogEntry entry = new LogEntry();
            entry.setId(log.getId());
            entry.setMensaje(log.getMensaje());
            entry.setFecha(log.getFecha().toString());
            response.getLogs().add(entry);
        });

        return response;
    }
}
