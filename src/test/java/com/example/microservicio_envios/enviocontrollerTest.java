package com.example.microservicio_envios;

import com.example.microservicio_envios.controller.enviocontroller;
import com.example.microservicio_envios.hateoas.envioModelAssembler;
import com.example.microservicio_envios.model.envio;
import com.example.microservicio_envios.service.envioservice;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(enviocontroller.class)
public class enviocontrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private envioservice envioService;

    @MockBean
    private envioModelAssembler assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodos() throws Exception {
        envio e = new envio(1L, "Pedro", "Chile", "En tránsito", "Santiago");
        EntityModel<envio> model = EntityModel.of(e);

        Mockito.when(envioService.obtenerTodos()).thenReturn(List.of(e));
        Mockito.when(assembler.toModel(e)).thenReturn(model);

        mockMvc.perform(get("/envios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.envioList[0].destinatario", is("Pedro")));
    }

    @Test
    void testObtenerPorId() throws Exception {
        envio e = new envio(1L, "Ana", "Argentina", "Entregado", "Buenos Aires");
        EntityModel<envio> model = EntityModel.of(e);

        Mockito.when(envioService.obtenerPorId(1L)).thenReturn(Optional.of(e));
        Mockito.when(assembler.toModel(e)).thenReturn(model);

        mockMvc.perform(get("/envios/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.destinatario", is("Ana")))
                .andExpect(jsonPath("$.paisDestino", is("Argentina")));
    }

    @Test
    void testRegistrarNuevoEnvio() throws Exception {
        envio nuevo = new envio(null, "Carlos", "Brasil", "Preparando", "São Paulo");

        Mockito.when(envioService.registrarEnvio("Carlos", "Brasil", "Preparando", "São Paulo"))
                .thenReturn("Envío registrado exitosamente con ID 99");

        mockMvc.perform(post("/envios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Envío registrado exitosamente")));
    }

    @Test
    void testActualizarEnvio() throws Exception {
        envio actualizado = new envio(null, null, null, "En camino", "Lima");

        Mockito.when(envioService.actualizarEnvio(5L, "En camino", "Lima"))
                .thenReturn("Datos actualizados correctamente");

        mockMvc.perform(put("/envios/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actualizado)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Datos actualizados correctamente")));
    }
}
