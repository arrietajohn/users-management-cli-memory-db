package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class DireccionClienteTest {
    private static final String VALOR_VALIDO = "Calle 30 # 17-25, Cartagena";

    @Test
    void deberiaCrearDireccionClienteValido() {
        // Arrange
        // Act
        DireccionCliente valueObject = new DireccionCliente(VALOR_VALIDO);

        // Assert
        assertThat(valueObject.valor()).isEqualTo(VALOR_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "Cl 1", "   ab   "})
    void deberiaRechazarDireccionClienteInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new DireccionCliente(valor))
                .isInstanceOf(ClienteInvalidoException.class)
                .hasMessage("Los datos del cliente son invalidos.");
    }
}
