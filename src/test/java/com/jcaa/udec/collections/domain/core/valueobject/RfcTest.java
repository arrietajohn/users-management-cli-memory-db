package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class RfcTest {
    @Test
    void deberiaNormalizarAMayusculas() {
        // Arrange
        // Act
        Rfc rfc = new Rfc(" abc010101ab1 ");

        // Assert
        assertThat(rfc.valor()).isEqualTo("ABC010101AB1");
    }

    @Test
    void deberiaAceptarRfcDeCuatroLetras() {
        // Arrange
        // Act
        Rfc rfc = new Rfc("ABCD010101AB1");

        // Assert
        assertThat(rfc.valor()).isEqualTo("ABCD010101AB1");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "12345", "ABC0101AB1", "ABC010101AB", "AB010101AB1"})
    void deberiaRechazarRfcInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Rfc(valor)).isInstanceOf(DatoInvalidoException.class);
    }
}
