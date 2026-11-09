/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.domain.core.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.jcaa.udec.collections.domain.core.valueobject.DenominacionComercial;
import com.jcaa.udec.collections.domain.core.valueobject.EstadoProyecto;
import com.jcaa.udec.collections.domain.core.valueobject.NombreClave;
import com.jcaa.udec.collections.domain.core.valueobject.ProyectoId;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
/**
 *
 * @author ASUS
 */
public class ProyectoTest {

    private static Proyecto crear(LocalDate inicio, LocalDate fin, EstadoProyecto estado) {
        return new Proyecto(new ProyectoId("pry-001"), new NombreClave("ORION"),
                new DenominacionComercial("Plataforma Orion"), inicio, fin, estado, "EMP-001");
    }

    @Test
    void creaProyectoValidoYNormalizaElCodigo() {
        Proyecto p = crear(LocalDate.of(2026, 1, 15), null, EstadoProyecto.EN_CURSO);
        assertEquals("PRY-001", p.getId().valor());
        assertEquals(EstadoProyecto.EN_CURSO, p.getEstado());
    }

    @Test
    void rechazaFechaFinAnteriorAFechaInicio() {
        assertThrows(IllegalArgumentException.class,
                () -> crear(LocalDate.of(2026, 5, 1), LocalDate.of(2026, 4, 1), EstadoProyecto.EN_CURSO));
    }

    @Test
    void rechazaProyectoFinalizadoSinFechaFin() {
        assertThrows(IllegalArgumentException.class,
                () -> crear(LocalDate.of(2026, 1, 1), null, EstadoProyecto.FINALIZADO));
    }

    @Test
    void rechazaCodigoVacio() {
        assertThrows(IllegalArgumentException.class, () -> new ProyectoId(" "));
    }

    @Test
    void rechazaEstadoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> EstadoProyecto.desde("INEXISTENTE"));
    }    
}
