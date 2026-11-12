package com.jcaa.udec.collections.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.RadioInvalidaException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class RadioTest {
    private static final String ID_VALIDO = "123";
    private static final String NOMBRE_VALIDO = "La Radio Caribe";
    private static final String FRECUENCIA_VALIDA = "98.5";
    private static final String TIPO_VALIDO = "FM";
    private static final String MENSAJE_DATOS_INVALIDOS = "Los datos de la radio son invalidos.";

    @Test
    void deberiaCrearRadioConDatosValidos() {
        // Arrange
        // Act
        Radio radio = new Radio(ID_VALIDO, NOMBRE_VALIDO, FRECUENCIA_VALIDA, TIPO_VALIDO);

        // Assert
        assertThat(radio)
                .extracting(Radio::getId, Radio::getNombre, Radio::getFrecuencia, Radio::getTipoTransmision)
                .containsExactly(ID_VALIDO, NOMBRE_VALIDO, FRECUENCIA_VALIDA, "FM");
    }

    @Test
    void deberiaCrearRadioConBuilder() {
        // Arrange
        // Act
        Radio radio = Radio.builder()
                .id(ID_VALIDO)
                .nombre(NOMBRE_VALIDO)
                .frecuencia(FRECUENCIA_VALIDA)
                .tipoTransmision(TIPO_VALIDO)
                .build();

        // Assert
        assertThat(radio)
                .extracting(Radio::getId, Radio::getNombre, Radio::getFrecuencia, Radio::getTipoTransmision)
                .containsExactly(ID_VALIDO, NOMBRE_VALIDO, FRECUENCIA_VALIDA, "FM");
    }

    @ParameterizedTest
    @MethodSource("datosInvalidos")
    void deberiaRechazarRadioConDatosInvalidos(
            String id, String nombre, String frecuencia, String tipoTransmision) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Radio(id, nombre, frecuencia, tipoTransmision))
                .isInstanceOf(RadioInvalidaException.class)
                .hasMessage(MENSAJE_DATOS_INVALIDOS);
    }

    private static Stream<Arguments> datosInvalidos() {
        return Stream.of(
                Arguments.of("abc", NOMBRE_VALIDO, FRECUENCIA_VALIDA, TIPO_VALIDO),
                Arguments.of(ID_VALIDO, "FM", FRECUENCIA_VALIDA, TIPO_VALIDO),
                Arguments.of(ID_VALIDO, NOMBRE_VALIDO, "-1", TIPO_VALIDO),
                Arguments.of(ID_VALIDO, NOMBRE_VALIDO, FRECUENCIA_VALIDA, "OM"));
    }
}
