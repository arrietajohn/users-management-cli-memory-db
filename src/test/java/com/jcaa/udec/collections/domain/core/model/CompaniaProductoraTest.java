package com.jcaa.udec.collections.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.FixturesDominio;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.Cedula;
import com.jcaa.udec.collections.domain.core.valueobject.Nombre;
import com.jcaa.udec.collections.domain.core.valueobject.Rfc;
import com.jcaa.udec.collections.domain.core.valueobject.RolPersona;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class CompaniaProductoraTest {
    private static final Rfc RFC = new Rfc("ABC010101AB1");
    private static final Nombre NOMBRE = new Nombre("Caribe Producciones");
    private static final List<String> TELEFONOS = List.of("3001234567");

    @Test
    void deberiaCrearProductoraConSusDatos() {
        // Arrange
        // Act
        CompaniaProductora productora = new CompaniaProductora(RFC, NOMBRE, List.of(" 3001234567 ", "+573001234567"));

        // Assert
        assertThat(productora.rfc()).isEqualTo(RFC);
        assertThat(productora.nombre()).isEqualTo(NOMBRE);
        assertThat(productora.telefonos()).containsExactly("3001234567", "+573001234567");
        assertThat(productora.personal()).isEmpty();
    }

    @Test
    void deberiaRechazarProductoraSinDatosCompletos() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new CompaniaProductora(null, NOMBRE, TELEFONOS))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new CompaniaProductora(RFC, null, TELEFONOS))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new CompaniaProductora(RFC, NOMBRE, null))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new CompaniaProductora(RFC, NOMBRE, List.of()))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaRechazarTelefonosInvalidos() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new CompaniaProductora(RFC, NOMBRE, List.of("123")))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new CompaniaProductora(RFC, NOMBRE, Arrays.asList((String) null)))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaContratarPersonasYBuscarlasPorCedula() {
        // Arrange
        CompaniaProductora productora = new CompaniaProductora(RFC, NOMBRE, TELEFONOS);
        Persona persona = FixturesDominio.persona("1234567", "Ana Maria", RolPersona.OPERADOR);

        // Act
        productora.contratar(persona);

        // Assert
        assertThat(productora.empleaA(new Cedula("1234567"))).isTrue();
        assertThat(productora.empleaA(new Cedula("7654321"))).isFalse();
        assertThat(productora.buscarPersona(new Cedula("1234567"))).contains(persona);
        assertThat(productora.buscarPersona(new Cedula("7654321"))).isEmpty();
        assertThat(productora.personal()).containsExactly(persona);
    }

    @Test
    void deberiaRechazarContratacionesInvalidas() {
        // Arrange
        CompaniaProductora productora = new CompaniaProductora(RFC, NOMBRE, TELEFONOS);
        productora.contratar(FixturesDominio.persona("1234567", "Ana Maria", RolPersona.OPERADOR));
        List<Persona> personal = productora.personal();

        // Act
        // Assert
        assertThatThrownBy(() -> productora.contratar(
                        FixturesDominio.persona("1234567", "Ana Maria", RolPersona.OPERADOR)))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> productora.contratar(null)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> personal.clear()).isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void deberiaIdentificarsePorRfc() {
        // Arrange
        CompaniaProductora una = new CompaniaProductora(RFC, NOMBRE, TELEFONOS);
        CompaniaProductora mismoRfc = new CompaniaProductora(RFC, new Nombre("Otro Nombre"), TELEFONOS);
        CompaniaProductora otra = new CompaniaProductora(new Rfc("XYZ010101XY1"), NOMBRE, TELEFONOS);

        // Act
        // Assert
        assertThat(una.equals(mismoRfc)).isTrue();
        assertThat(una.hashCode()).isEqualTo(mismoRfc.hashCode());
        assertThat(una.equals(otra)).isFalse();
        assertThat(una.equals(una)).isTrue();
        assertThat(una.equals("texto")).isFalse();
    }
}
