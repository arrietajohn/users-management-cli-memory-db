package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class IdentificadoresTest {
    @Test
    void deberiaCrearIdsValidos() {
        // Arrange
        // Act
        // Assert
        assertThat(new ProgramaId("15").valor()).isEqualTo("15");
        assertThat(new EmisionId("7").valor()).isEqualTo("7");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "abc", "0", "-4", "999999999999"})
    void deberiaRechazarIdDeProgramaInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new ProgramaId(valor)).isInstanceOf(DatoInvalidoException.class);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "abc", "0", "-4", "999999999999"})
    void deberiaRechazarIdDeEmisionInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new EmisionId(valor)).isInstanceOf(DatoInvalidoException.class);
    }
}
