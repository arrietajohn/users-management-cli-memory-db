package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class NombreClienteTest {
    private static final String VALOR_VALIDO = "Moda Caribe S.A.S.";

    @Test
    void deberiaCrearNombreClienteValido() {
        // Arrange
        // Act
        NombreCliente valueObject = new NombreCliente(VALOR_VALIDO);

        // Assert
        assertThat(valueObject.valor()).isEqualTo(VALOR_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "AB", "  A  "})
    void deberiaRechazarNombreClienteInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new NombreCliente(valor))
                .isInstanceOf(ClienteInvalidoException.class)
                .hasMessage("Los datos del cliente son invalidos.");
    }
}
