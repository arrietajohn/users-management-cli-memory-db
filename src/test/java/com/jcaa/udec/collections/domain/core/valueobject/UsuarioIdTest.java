package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class UsuarioIdTest {
    private static final String ID_VALIDO = "123";

    @Test
    void deberiaCrearIdValido() {
        // Arrange
        // Act
        UsuarioId usuarioId = new UsuarioId(ID_VALIDO);

        // Assert
        assertThat(usuarioId.valor()).isEqualTo(ID_VALIDO);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "abc", "99999999999999999999"})
    void deberiaRechazarIdInvalido(String id) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new UsuarioId(id))
                .isInstanceOf(UsuarioInvalidoException.class);
    }
}
