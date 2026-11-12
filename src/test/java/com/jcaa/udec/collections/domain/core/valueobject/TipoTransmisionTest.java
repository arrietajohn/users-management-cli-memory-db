package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.RadioInvalidaException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TipoTransmisionTest {

    @ParameterizedTest
    @ValueSource(strings = {"AM", "am", "FM", "fm", " FM "})
    void deberiaCrearTipoTransmisionValido(String tipo) {
        // Arrange
        // Act
        TipoTransmision tipoTransmision = TipoTransmision.desde(tipo);

        // Assert
        assertThat(tipoTransmision).isIn(TipoTransmision.AM, TipoTransmision.FM);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "OM", "AMFM"})
    void deberiaRechazarTipoTransmisionInvalido(String tipo) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> TipoTransmision.desde(tipo))
                .isInstanceOf(RadioInvalidaException.class);
    }
}
