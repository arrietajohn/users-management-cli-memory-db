package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import org.junit.jupiter.api.Test;

class EstadisticaProgramaTest {
    private static final ProgramaId PROGRAMA = new ProgramaId("1");

    @Test
    void deberiaIniciarVaciaYSinPorcentajes() {
        // Arrange
        // Act
        EstadisticaPrograma estadistica = EstadisticaPrograma.vacia(PROGRAMA);

        // Assert
        assertThat(estadistica.totalEncuestados()).isEqualTo(0);
        assertThat(estadistica.porcentajeAprobacion()).isEqualTo(0.0);
        assertThat(estadistica.porcentajeRechazo()).isEqualTo(0.0);
    }

    @Test
    void deberiaAcumularResultadosDeEncuestas() {
        // Arrange
        EstadisticaPrograma estadistica = EstadisticaPrograma.vacia(PROGRAMA);

        // Act
        EstadisticaPrograma acumulada = estadistica
                .acumular(new ResultadoEncuesta(10, 6, 3, 1))
                .acumular(new ResultadoEncuesta(10, 4, 5, 1));

        // Assert
        assertThat(acumulada.totalEncuestados()).isEqualTo(20);
        assertThat(acumulada.aprobaciones()).isEqualTo(10);
        assertThat(acumulada.rechazos()).isEqualTo(8);
        assertThat(acumulada.indiferencias()).isEqualTo(2);
        assertThat(acumulada.porcentajeAprobacion()).isEqualTo(50.0);
        assertThat(acumulada.porcentajeRechazo()).isEqualTo(40.0);
    }

    @Test
    void deberiaRechazarDatosInvalidos() {
        // Arrange
        EstadisticaPrograma estadistica = EstadisticaPrograma.vacia(PROGRAMA);

        // Act
        // Assert
        assertThatThrownBy(() -> estadistica.acumular(null)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new EstadisticaPrograma(null, 0, 0, 0, 0))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new EstadisticaPrograma(PROGRAMA, -1, 0, 0, 0))
                .isInstanceOf(DatoInvalidoException.class);
    }
}
