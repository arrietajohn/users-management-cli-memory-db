package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class TipoActividadTest {

    @ParameterizedTest
    @CsvSource({
        "MODA, MODA, Moda",
        "moda, MODA, Moda",
        "' publicidad_cine ', PUBLICIDAD_CINE, Publicidad y cine"
    })
    void deberiaObtenerTipoActividadDesdeTexto(
            String valor, TipoActividad esperado, String descripcion) {
        // Arrange
        // Act
        TipoActividad tipoActividad = TipoActividad.desde(valor);

        // Assert
        assertThat(tipoActividad).isEqualTo(esperado);
        assertThat(tipoActividad.getDescripcion()).isEqualTo(descripcion);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "CINE", "publicidad"})
    void deberiaRechazarTipoActividadInvalido(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> TipoActividad.desde(valor))
                .isInstanceOf(ClienteInvalidoException.class);
    }
}
