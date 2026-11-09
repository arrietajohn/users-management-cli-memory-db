/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.adapter.persistence.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.jcaa.udec.collections.domain.core.model.Proyecto;
import com.jcaa.udec.collections.domain.core.valueobject.DenominacionComercial;
import com.jcaa.udec.collections.domain.core.valueobject.EstadoProyecto;
import com.jcaa.udec.collections.domain.core.valueobject.NombreClave;
import com.jcaa.udec.collections.domain.core.valueobject.ProyectoId;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 *
 * @author ASUS
 */
public class ProyectoMemoryRepositoryTest {

    private ProyectoMemoryRepository repositorio;

    @BeforeEach
    void iniciar() {
        repositorio = new ProyectoMemoryRepository();
    }

    private Proyecto proyecto(String codigo) {
        return new Proyecto(new ProyectoId(codigo), new NombreClave("ORION"),
                new DenominacionComercial("Plataforma Orion"), LocalDate.of(2026, 1, 15),
                null, EstadoProyecto.EN_CURSO, "EMP-001");
    }

    @Test
    void guardaYBuscaPorId() {
        repositorio.guardar(proyecto("PRY-001"));
        assertTrue(repositorio.buscarPorId(new ProyectoId("PRY-001")).isPresent());
    }

    @Test
    void listaTodosLosProyectos() {
        repositorio.guardar(proyecto("PRY-001"));
        repositorio.guardar(proyecto("PRY-002"));
        assertEquals(2, repositorio.listarTodos().size());
    }

    @Test
    void eliminaProyectoExistente() {
        repositorio.guardar(proyecto("PRY-001"));
        assertTrue(repositorio.eliminarPorId(new ProyectoId("PRY-001")));
        assertFalse(repositorio.existePorId(new ProyectoId("PRY-001")));
    }

    @Test
    void eliminarInexistenteRetornaFalso() {
        assertFalse(repositorio.eliminarPorId(new ProyectoId("NO-EXISTE")));
    }    
}
