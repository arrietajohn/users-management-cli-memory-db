/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jcaa.udec.collections.adapter.persistence.memory.ProyectoMemoryRepository;
import com.jcaa.udec.collections.domain.core.exception.ProyectoNoEncontradoException;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
/**
 *
 * @author ASUS
 */
public class BuscarProyectoServiceTest {

    @Test
    void encuentraProyectoExistente() {
        ProyectoRepositoryPort repo = new ProyectoMemoryRepository();
        new CrearProyectoService(repo).ejecutar(new ProyectoCommand("PRY-001", "ORION",
                "Plataforma Orion", LocalDate.of(2026, 1, 15), null, "EN_CURSO", "EMP-001"));

        assertEquals("PRY-001", new BuscarProyectoService(repo).ejecutar("pry-001").getId().valor());
    }

    @Test
    void lanzaExcepcionSiNoExiste() {
        BuscarProyectoService servicio = new BuscarProyectoService(new ProyectoMemoryRepository());
        assertThrows(ProyectoNoEncontradoException.class, () -> servicio.ejecutar("NO-EXISTE"));
    }    
}
