package com.example.microservicio_envios.controller;

import com.example.microservicio_envios.model.envio;
import com.example.microservicio_envios.service.envioservice;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/envios")
public class enviocontroller {

    private final envioservice envioService;

    public enviocontroller(envioservice envioService) {
        this.envioService = envioService;
    }

    // GET → todos los envíos
    @GetMapping
    public List<envio> obtenerTodos() {
        return envioService.obtenerTodos();
    }

    // GET → por ID
    @GetMapping("/{id}")
    public Optional<envio> obtenerPorId(@PathVariable Long id) {
        return envioService.obtenerPorId(id);
    }

    // GET → ubicación actual
    @GetMapping("/{id}/ubicacion")
    public String obtenerUbicacion(@PathVariable Long id) {
        return envioService.consultarUbicacion(id);
    }

    // POST → registrar nuevo envío
    @PostMapping
    public String registrarNuevoEnvio(@RequestBody envio nuevoEnvio) {
        return envioService.registrarEnvio(
                nuevoEnvio.getDestinatario(),
                nuevoEnvio.getPaisDestino(),
                nuevoEnvio.getEstado(),
                nuevoEnvio.getUbicacionActual()
        );
    }

    // PUT → actualizar estado o ubicación
    @PutMapping("/{id}")
    public String actualizarEstadoYUbicacion(@PathVariable Long id,
                                             @RequestBody envio actualizado) {
        return envioService.actualizarEnvio(id,
                actualizado.getEstado(),
                actualizado.getUbicacionActual());
    }

    // DELETE → eliminar por ID
    @DeleteMapping("/{id}")
    public String eliminarEnvio(@PathVariable Long id) {
        return envioService.eliminarEnvio(id);
    }
}
