package com.example.microservicio_envios.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/conexion")
    public ResponseEntity<String> testConnection() {
        try {
            entityManager.createNativeQuery("SELECT 1 FROM DUAL").getSingleResult();
            return ResponseEntity.ok("✅ Conexión exitosa con Oracle.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("❌ Fallo de conexión: " + e.getMessage());
        }
    }
}
