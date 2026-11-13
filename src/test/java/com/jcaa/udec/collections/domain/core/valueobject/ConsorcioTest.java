package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import org.junit.jupiter.api.Test;

class ConsorcioTest {
    @Test
    void deberiaCrearConsorcioConRadioYProductora() {
        // Arrange
        RadioId radio = new RadioId("1");
        Rfc rfc = new Rfc("ABC010101AB1");

        // Act
        Consorcio consorcio = new Consorcio(radio, rfc);

        // Assert
        assertThat(consorcio.radioId()).isEqualTo(radio);
        assertThat(consorcio.productoraRfc()).isEqualTo(rfc);
    }

    @Test
    void deberiaRechazarConsorcioIncompleto() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Consorcio(null, new Rfc("ABC010101AB1")))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new Consorcio(new RadioId("1"), null)).isInstanceOf(DatoInvalidoException.class);
    }
}
