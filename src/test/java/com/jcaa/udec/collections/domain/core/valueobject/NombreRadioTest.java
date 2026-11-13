package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.RadioInvalidaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NombreRadioTest {
    private static final String NOMBRE_VALIDO = "La Radio Caribe";

    @Test
    void deberiaCrearNombreValido() {
        // Arrange
        // Act
        NombreRadio nombreRadio = new NombreRadio(NOMBRE_VALIDO);

        // Assert
        assertThat(nombreRadio.valor()).isEqualTo(NOMBRE_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "FM"})
    void deberiaRechazarNombreInvalido(String nombre) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new NombreRadio(nombre))
                .isInstanceOf(RadioInvalidaException.class);
    }
}
