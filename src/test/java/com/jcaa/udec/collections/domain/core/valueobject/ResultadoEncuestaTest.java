package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.SumaRespuestasInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ResultadoEncuestaTest {

    @Test
    void rechazaElResultadoCuandoLaSumaNoCoincideConElTotal() {
        // 50 + 30 + 10 = 90, pero se dice que hubo 100 encuestados
        assertThrows(SumaRespuestasInvalidaException.class,
                () -> new ResultadoEncuesta(100, 50, 30, 10));
    }

    @Test
    void rechazaUnResultadoSinEncuestados() {
        assertThrows(SumaRespuestasInvalidaException.class,
                () -> new ResultadoEncuesta(0, 0, 0, 0));
    }

    @Test
    void rechazaRespuestasNegativas() {
        assertThrows(SumaRespuestasInvalidaException.class,
                () -> new ResultadoEncuesta(100, 120, -20, 0));
    }

    @Test
    void aceptaUnResultadoCuyaSumaCuadra() {
        ResultadoEncuesta resultado = new ResultadoEncuesta(100, 60, 30, 10);

        assertEquals(100, resultado.encuestados());
        assertEquals(60, resultado.aprobaciones());
        assertEquals(30, resultado.rechazos());
        assertEquals(10, resultado.indiferencias());
    }

    @Test
    void calculaElPorcentajeDeAprobacion() {
        ResultadoEncuesta resultado = new ResultadoEncuesta(200, 150, 30, 20);

        assertEquals(75.0, resultado.porcentajeAprobacion());
    }

    @Test
    void dosResultadosConLosMismosValoresSonIguales() {
        assertEquals(new ResultadoEncuesta(10, 5, 3, 2), new ResultadoEncuesta(10, 5, 3, 2));
    }
}
