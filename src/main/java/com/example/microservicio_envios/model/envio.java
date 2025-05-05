package com.example.microservicio_envios.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ENVIOS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class envio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinatario;
    private String paisDestino;
    private String estado;
    private String ubicacionActual;
}
