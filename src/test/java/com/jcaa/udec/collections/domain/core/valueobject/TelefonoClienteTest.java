package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TelefonoClienteTest {
    private static final String VALOR_VALIDO = "+573001234567";

    @Test
    void deberiaCrearTelefonoClienteValido() {
        // Arrange
        // Act
        TelefonoCliente valueObject = new TelefonoCliente(VALOR_VALIDO);

        // Assert
        assertThat(valueObject.valor()).isEqualTo(VALOR_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "123456", "3001234567890123", "300-123-4567", "+", "abcdefgh"})
    void deberiaRechazarTelefonoClienteInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new TelefonoCliente(valor))
                .isInstanceOf(ClienteInvalidoException.class)
                .hasMessage("Los datos del cliente son invalidos.");
    }
}
