package com.jcaa.udec.collections.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.FixturesDominio;
import com.jcaa.udec.collections.domain.core.event.EmisionProgramada;
import com.jcaa.udec.collections.domain.core.event.EncuestaRegistrada;
import com.jcaa.udec.collections.domain.core.event.EventoDominio;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.ResultadoEncuesta;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;

class EmisionTest {
    private static final LocalDate FECHA = LocalDate.of(2026, 11, 2);

    private static Emision crearEmision(String id) {
        return FixturesDominio.emision(FixturesDominio.programa("1"), id, FECHA, false);
    }

    @Test
    void deberiaRechazarEmisionConDatosInvalidos() {
        // Arrange
        Programa programa = FixturesDominio.programa("1");
        RadioId radio = new RadioId("1");

        // Act
        // Assert
        assertThatThrownBy(() -> programa.programarEmision(null, radio, FECHA, LocalTime.of(7, 0), 60, false))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.programarEmision(new EmisionId("1"), radio, null, LocalTime.of(7, 0), 60, false))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.programarEmision(new EmisionId("1"), radio, FECHA, null, 60, false))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.programarEmision(new EmisionId("1"), radio, FECHA, LocalTime.of(7, 0), 0, false))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.programarEmision(new EmisionId("1"), radio, FECHA, LocalTime.of(7, 0), 1441, false))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaRegistrarUnaEncuestaYTotalizarSusCifras() {
        // Arrange
        Emision emision = crearEmision("1");

        // Act
        emision.registrarEncuesta(new ResultadoEncuesta(10, 6, 3, 1));

        // Assert
        assertThat(emision.encuestas()).hasSize(1);
        assertThat(emision.totalEncuestados()).isEqualTo(10);
        assertThat(emision.totalAprobaciones()).isEqualTo(6);
        assertThat(emision.totalRechazos()).isEqualTo(3);
        assertThat(emision.totalIndiferencias()).isEqualTo(1);
    }

    @Test
    void deberiaSumarVariasEncuestasDeLaMismaEmision() {
        // Arrange
        Emision emision = crearEmision("1");

        // Act
        emision.registrarEncuesta(new ResultadoEncuesta(10, 6, 3, 1));
        emision.registrarEncuesta(new ResultadoEncuesta(20, 5, 10, 5));

        // Assert
        assertThat(emision.encuestas()).hasSize(2);
        assertThat(emision.totalEncuestados()).isEqualTo(30);
        assertThat(emision.totalAprobaciones()).isEqualTo(11);
        assertThat(emision.totalRechazos()).isEqualTo(13);
        assertThat(emision.totalIndiferencias()).isEqualTo(6);
    }

    @Test
    void deberiaRechazarUnaEncuestaNula() {
        // Arrange
        Emision emision = crearEmision("1");

        // Act
        // Assert
        assertThatThrownBy(() -> emision.registrarEncuesta(null)).isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaEntregarLasEncuestasSinPermitirModificarlas() {
        // Arrange
        Emision emision = crearEmision("1");
        emision.registrarEncuesta(new ResultadoEncuesta(10, 6, 3, 1));
        List<ResultadoEncuesta> encuestas = emision.encuestas();

        // Act
        // Assert
        assertThatThrownBy(encuestas::clear).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void deberiaPublicarEventosEnElOrdenEnQueOcurrieron() {
        // Arrange
        Emision emision = crearEmision("1");
        emision.registrarEncuesta(new ResultadoEncuesta(10, 6, 3, 1));

        // Act
        List<EventoDominio> eventos = emision.extraerEventos();

        // Assert
        assertThat(eventos).hasSize(2);
        assertThat(eventos.get(0)).isInstanceOf(EmisionProgramada.class);
        assertThat(eventos.get(1)).isInstanceOf(EncuestaRegistrada.class);
        assertThat(((EmisionProgramada) eventos.get(0)).fecha()).isEqualTo(FECHA);
        assertThat(((EncuestaRegistrada) eventos.get(1)).totalEncuestados()).isEqualTo(10);
        assertThat(eventos.get(0).ocurridoEn()).isNotNull();
        assertThat(emision.extraerEventos()).isEmpty();
    }

    @Test
    void deberiaIdentificarsePorId() {
        // Arrange
        Emision una = crearEmision("1");
        Emision mismoId = crearEmision("1");
        Emision otra = crearEmision("2");

        // Act
        // Assert
        assertThat(una.equals(mismoId)).isTrue();
        assertThat(una.hashCode()).isEqualTo(mismoId.hashCode());
        assertThat(una.equals(otra)).isFalse();
        assertThat(una.equals(una)).isTrue();
        assertThat(una.equals("texto")).isFalse();
    }
}
