package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PersonaContactoTest {
    private static final String VALOR_VALIDO = "Laura Gomez";

    @Test
    void deberiaCrearPersonaContactoValido() {
        // Arrange
        // Act
        PersonaContacto valueObject = new PersonaContacto(VALOR_VALIDO);

        // Assert
        assertThat(valueObject.valor()).isEqualTo(VALOR_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "Lu", "  L  "})
    void deberiaRechazarPersonaContactoInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new PersonaContacto(valor))
                .isInstanceOf(ClienteInvalidoException.class)
                .hasMessage("Los datos del cliente son invalidos.");
    }
}
