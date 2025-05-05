package com.example.microservicio_envios.repositorios;

import com.example.microservicio_envios.model.envio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface enviorepositorio extends JpaRepository<envio, Long> {
}
