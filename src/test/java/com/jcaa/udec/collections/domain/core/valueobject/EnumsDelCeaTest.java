package com.jcaa.udec.collections.domain.core.valueobject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class EnumsDelCeaTest {
    @Test
    void deberiaCrearGeneroIgnorandoMayusculasEspaciosYTildes() {
        // Arrange
        // Act
        // Assert
        assertThat(GeneroPrograma.desde("  musical  ")).isEqualTo(GeneroPrograma.MUSICAL);
        assertThat(GeneroPrograma.desde("Periodístico")).isEqualTo(GeneroPrograma.PERIODISTICO);
    }

    @Test
    void deberiaCrearTipoDeProduccionIgnorandoMayusculasEspaciosYTildes() {
        // Arrange
        // Act
        // Assert
        assertThat(TipoProduccion.desde(" comercial ")).isEqualTo(TipoProduccion.COMERCIAL);
        assertThat(TipoProduccion.desde("Periodística")).isEqualTo(TipoProduccion.PERIODISTICA);
    }

    @Test
    void deberiaCrearRolIgnorandoMayusculasYEspacios() {
        // Arrange
        // Act
        // Assert
        assertThat(RolPersona.desde(" conductor ")).isEqualTo(RolPersona.CONDUCTOR);
        assertThat(RolPersona.desde("OPERADOR")).isEqualTo(RolPersona.OPERADOR);
        assertThat(RolPersona.desde("Productor")).isEqualTo(RolPersona.PRODUCTOR);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "inexistente"})
    void deberiaRechazarValoresInvalidos(String valor) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> GeneroPrograma.desde(valor)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> TipoProduccion.desde(valor)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> RolPersona.desde(valor)).isInstanceOf(DatoInvalidoException.class);
    }
}
