package com.example.microservicio_envios.controller;

import com.example.microservicio_envios.model.envio;
import com.example.microservicio_envios.service.envioservice;
import com.example.microservicio_envios.hateoas.envioModelAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/envios", produces = "application/hal+json")  // Fuerza HATEOAS
public class enviocontroller {

    private final envioservice envioService;
    private final envioModelAssembler assembler;

    public enviocontroller(envioservice envioService, envioModelAssembler assembler) {
        this.envioService = envioService;
        this.assembler = assembler;
    }

    // GET → todos los envíos (HATEOAS)
    @GetMapping
    public CollectionModel<EntityModel<envio>> obtenerTodos() {
        List<EntityModel<envio>> envios = envioService.obtenerTodos().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(envios,
                linkTo(methodOn(enviocontroller.class).obtenerTodos()).withSelfRel());
    }

    // GET → por ID (HATEOAS)
    @GetMapping("/{id}")
    public EntityModel<envio> obtenerPorId(@PathVariable Long id) {
        envio e = envioService.obtenerPorId(id)
                .orElseThrow(() -> new RuntimeException("Envío no encontrado"));
        return assembler.toModel(e);
    }

    // GET → ubicación actual (sin HATEOAS)
    @GetMapping("/{id}/ubicacion")
    public String obtenerUbicacion(@PathVariable Long id) {
        return envioService.consultarUbicacion(id);
    }

    // POST → registrar nuevo envío (sin HATEOAS)
    @PostMapping
    public String registrarNuevoEnvio(@RequestBody envio nuevoEnvio) {
        return envioService.registrarEnvio(
                nuevoEnvio.getDestinatario(),
                nuevoEnvio.getPaisDestino(),
                nuevoEnvio.getEstado(),
                nuevoEnvio.getUbicacionActual()
        );
    }

    // PUT → actualizar estado o ubicación (sin HATEOAS)
    @PutMapping("/{id}")
    public String actualizarEstadoYUbicacion(@PathVariable Long id,
                                             @RequestBody envio actualizado) {
        return envioService.actualizarEnvio(id,
                actualizado.getEstado(),
                actualizado.getUbicacionActual());
    }

    // DELETE → eliminar por ID (sin HATEOAS)
    @DeleteMapping("/{id}")
    public String eliminarEnvio(@PathVariable Long id) {
        return envioService.eliminarEnvio(id);
    }
}
