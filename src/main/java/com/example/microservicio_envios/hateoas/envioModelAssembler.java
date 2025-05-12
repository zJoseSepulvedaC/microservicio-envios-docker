package com.example.microservicio_envios.hateoas;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.microservicio_envios.controller.enviocontroller;
import com.example.microservicio_envios.model.envio;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class envioModelAssembler implements RepresentationModelAssembler<envio, EntityModel<envio>> {

    @Override
    @NonNull
    public EntityModel<envio> toModel(@NonNull envio envio) {
        return EntityModel.of(envio,
                linkTo(methodOn(enviocontroller.class).obtenerPorId(envio.getId())).withSelfRel(),
                linkTo(methodOn(enviocontroller.class).obtenerTodos()).withRel("todos"));
    }
}
