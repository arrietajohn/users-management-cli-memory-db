package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class CodigoClienteTest {
    private static final String VALOR_VALIDO = "CLI-001";

    @Test
    void deberiaCrearCodigoClienteValido() {
        // Arrange
        // Act
        CodigoCliente valueObject = new CodigoCliente(VALOR_VALIDO);

        // Assert
        assertThat(valueObject.valor()).isEqualTo(VALOR_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "AB", "CLI 001", "CLI_001", "CODIGO-DEMASIADO-LARGO"})
    void deberiaRechazarCodigoClienteInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new CodigoCliente(valor))
                .isInstanceOf(ClienteInvalidoException.class)
                .hasMessage("Los datos del cliente son invalidos.");
    }
}
