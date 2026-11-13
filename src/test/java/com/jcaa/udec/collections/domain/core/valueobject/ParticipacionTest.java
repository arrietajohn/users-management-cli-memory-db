package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import org.junit.jupiter.api.Test;

class ParticipacionTest {
    private static final Cedula CEDULA = new Cedula("1234567");

    @Test
    void deberiaCrearConductorYOperadorSinTipoDeProduccion() {
        // Arrange
        // Act
        Participacion conductor = Participacion.conductor(CEDULA);
        Participacion operador = Participacion.operador(CEDULA);

        // Assert
        assertThat(conductor.rol()).isEqualTo(RolPersona.CONDUCTOR);
        assertThat(conductor.tipoProduccion()).isNull();
        assertThat(operador.rol()).isEqualTo(RolPersona.OPERADOR);
    }

    @Test
    void deberiaCrearProductorConTipoDeProduccion() {
        // Arrange
        // Act
        Participacion participacion = Participacion.productor(CEDULA, TipoProduccion.COMERCIAL);

        // Assert
        assertThat(participacion.rol()).isEqualTo(RolPersona.PRODUCTOR);
        assertThat(participacion.tipoProduccion()).isEqualTo(TipoProduccion.COMERCIAL);
    }

    @Test
    void deberiaRechazarProductorSinTipoDeProduccion() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> Participacion.productor(CEDULA, null)).isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaRechazarTipoDeProduccionEnUnConductor() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Participacion(CEDULA, RolPersona.CONDUCTOR, TipoProduccion.COMERCIAL))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaRechazarParticipacionSinCedulaOSinRol() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> Participacion.conductor(null)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new Participacion(CEDULA, null, null)).isInstanceOf(DatoInvalidoException.class);
    }
}
