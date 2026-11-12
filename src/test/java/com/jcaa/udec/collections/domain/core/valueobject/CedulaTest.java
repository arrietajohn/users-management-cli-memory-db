package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CedulaTest {
    @Test
    void deberiaCrearCedulaValida() {
        // Arrange
        // Act
        Cedula cedula = new Cedula(" 1234567 ");

        // Assert
        assertThat(cedula.valor()).isEqualTo("1234567");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "12345", "abcdefgh", "1234567890123", "12 3456"})
    void deberiaRechazarCedulaInvalida(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Cedula(valor)).isInstanceOf(DatoInvalidoException.class);
    }
}
