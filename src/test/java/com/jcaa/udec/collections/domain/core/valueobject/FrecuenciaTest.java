package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.RadioInvalidaException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class FrecuenciaTest {
    private static final String FRECUENCIA_VALIDA = "98.5";

    @Test
    void deberiaCrearFrecuenciaValida() {
        // Arrange
        // Act
        Frecuencia frecuencia = new Frecuencia(FRECUENCIA_VALIDA);

        // Assert
        assertThat(frecuencia.valor()).isEqualTo(FRECUENCIA_VALIDA);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "abc", "0", "-98.5"})
    void deberiaRechazarFrecuenciaInvalida(String frecuencia) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Frecuencia(frecuencia))
                .isInstanceOf(RadioInvalidaException.class);
    }
}
