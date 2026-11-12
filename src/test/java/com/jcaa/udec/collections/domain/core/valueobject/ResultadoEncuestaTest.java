package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.EncuestaDescuadradaException;
import org.junit.jupiter.api.Test;

class ResultadoEncuestaTest {
    @Test
    void deberiaCrearResultadoCuandoLasCifrasCierran() {
        // Arrange
        // Act
        ResultadoEncuesta resultado = new ResultadoEncuesta(10, 6, 3, 1);

        // Assert
        assertThat(resultado.total()).isEqualTo(10);
        assertThat(resultado.aprobaciones()).isEqualTo(6);
        assertThat(resultado.rechazos()).isEqualTo(3);
        assertThat(resultado.indiferencias()).isEqualTo(1);
    }

    @Test
    void deberiaCalcularPorcentajes() {
        // Arrange
        ResultadoEncuesta resultado = new ResultadoEncuesta(10, 6, 3, 1);

        // Act
        // Assert
        assertThat(resultado.porcentajeAprobacion()).isEqualTo(60.0);
        assertThat(resultado.porcentajeRechazo()).isEqualTo(30.0);
        assertThat(resultado.porcentajeIndiferencia()).isEqualTo(10.0);
    }

    @Test
    void deberiaRechazarCifrasQueNoCierran() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new ResultadoEncuesta(10, 5, 3, 1))
                .isInstanceOf(EncuestaDescuadradaException.class)
                .hasMessageContaining("no cierran");
    }

    @Test
    void deberiaRechazarCifrasNegativasOTotalEnCero() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new ResultadoEncuesta(5, 6, -1, 0)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new ResultadoEncuesta(0, 0, 0, 0)).isInstanceOf(DatoInvalidoException.class);
    }
}
