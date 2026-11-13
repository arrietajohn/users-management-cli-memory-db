package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NombreTest {
    @Test
    void deberiaCrearNombreValidoYRecortarEspacios() {
        // Arrange
        // Act
        Nombre nombre = new Nombre("  Buenos Dias Cartagena  ");

        // Assert
        assertThat(nombre.valor()).isEqualTo("Buenos Dias Cartagena");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "ab", "  a  "})
    void deberiaRechazarNombreInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Nombre(valor)).isInstanceOf(DatoInvalidoException.class);
    }
}
