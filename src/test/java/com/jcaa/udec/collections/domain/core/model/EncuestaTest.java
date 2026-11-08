package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.event.EncuestaRegistrada;
import com.jcaa.udec.collections.domain.core.event.EventoDominio;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.ResultadoEncuesta;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncuestaTest {

    @Test
    void registrarUnaEncuestaLeAsignaUnIdYGuardaLaReferenciaALaEmision() {
        EmisionId emision = EmisionId.generar();

        Encuesta encuesta = Encuesta.registrar(emision, new ResultadoEncuesta(100, 70, 20, 10));

        assertNotNull(encuesta.id());
        assertEquals(emision, encuesta.emisionId());
        assertEquals(70.0, encuesta.porcentajeAprobacion());
    }

    @Test
    void registrarUnaEncuestaGeneraElEventoEncuestaRegistrada() {
        Encuesta encuesta = Encuesta.registrar(EmisionId.generar(), new ResultadoEncuesta(50, 25, 15, 10));

        List<EventoDominio> eventos = encuesta.eventosPendientes();

        assertEquals(1, eventos.size());
        assertTrue(eventos.get(0) instanceof EncuestaRegistrada);
        EncuestaRegistrada evento = (EncuestaRegistrada) eventos.get(0);
        assertEquals(encuesta.id(), evento.encuestaId());
        assertEquals(50, evento.totalEncuestados());
        assertNotNull(evento.ocurridoEn());
    }

    @Test
    void limpiarEventosVaciaLosEventosPendientes() {
        Encuesta encuesta = Encuesta.registrar(EmisionId.generar(), new ResultadoEncuesta(10, 5, 3, 2));

        encuesta.limpiarEventos();

        assertTrue(encuesta.eventosPendientes().isEmpty());
    }

    @Test
    void noPermiteRegistrarUnaEncuestaSinEmision() {
        assertThrows(DatoInvalidoException.class,
                () -> Encuesta.registrar(null, new ResultadoEncuesta(10, 5, 3, 2)));
    }

    @Test
    void noPermiteRegistrarUnaEncuestaSinResultados() {
        assertThrows(DatoInvalidoException.class,
                () -> Encuesta.registrar(EmisionId.generar(), null));
    }

    @Test
    void dosEncuestasConLosMismosDatosPeroDistintoIdSonDistintas() {
        EmisionId emision = EmisionId.generar();
        ResultadoEncuesta resultado = new ResultadoEncuesta(10, 5, 3, 2);

        assertNotEquals(Encuesta.registrar(emision, resultado), Encuesta.registrar(emision, resultado));
    }
}
