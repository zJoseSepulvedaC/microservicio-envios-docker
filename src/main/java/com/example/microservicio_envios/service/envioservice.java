package com.example.microservicio_envios.service;

import com.example.microservicio_envios.model.envio;
import com.example.microservicio_envios.repositorios.enviorepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class envioservice {

    private final enviorepositorio envioRepository;

    public envioservice(enviorepositorio envioRepository) {
        this.envioRepository = envioRepository;
    }

    public List<envio> obtenerTodos() {
        return envioRepository.findAll();
    }

    public Optional<envio> obtenerPorId(Long id) {
        return envioRepository.findById(id);
    }

    public String consultarUbicacion(Long id) {
        return envioRepository.findById(id)
                .map(envio::getUbicacionActual)
                .orElse("Envío no encontrado");
    }

    public String actualizarEnvio(Long id, String estado, String ubicacion) {
        Optional<envio> encontrado = envioRepository.findById(id);

        if (encontrado.isPresent()) {
            envio e = encontrado.get();
            if (estado != null && !estado.isBlank()) {
                e.setEstado(estado);
            }
            if (ubicacion != null && !ubicacion.isBlank()) {
                e.setUbicacionActual(ubicacion);
            }
            envioRepository.save(e);  // Guardar cambios
            return "Datos actualizados correctamente";
        } else {
            return "Envío no encontrado";
        }
    }

    public String registrarEnvio(String destinatario, String paisDestino, String estado, String ubicacionActual) {
        if (destinatario == null || paisDestino == null || estado == null || ubicacionActual == null ||
            destinatario.isBlank() || paisDestino.isBlank() || estado.isBlank() || ubicacionActual.isBlank()) {
            return "Todos los campos son obligatorios.";
        }

        envio nuevo = new envio();
        nuevo.setDestinatario(destinatario);
        nuevo.setPaisDestino(paisDestino);
        nuevo.setEstado(estado);
        nuevo.setUbicacionActual(ubicacionActual);
        envioRepository.save(nuevo);

        return "Envío registrado exitosamente con ID " + nuevo.getId();
    }

    public String eliminarEnvio(Long id) {
        if (envioRepository.existsById(id)) {
            envioRepository.deleteById(id);
            return "Envío eliminado correctamente.";
        } else {
            return "Envío no encontrado.";
        }
    }
}
