package com.arquitecturasoftware.soaplogs.service;


import com.arquitecturasoftware.soaplogs.entity.Log;
import com.arquitecturasoftware.soaplogs.repository.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {

    private final LogRepository logRepository;

    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<Log> getAllLogs() {
        return logRepository.findAll();
    }
}

