package com.arquitecturasoftware.soaplogs.repository;


import com.arquitecturasoftware.soaplogs.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    // Métodos personalizados si son necesarios
}

