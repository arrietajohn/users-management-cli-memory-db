package com.jcaa.udec.collections.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.FixturesDominio;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.Cedula;
import com.jcaa.udec.collections.domain.core.valueobject.Nombre;
import com.jcaa.udec.collections.domain.core.valueobject.RolPersona;
import org.junit.jupiter.api.Test;

class PersonaTest {
    @Test
    void deberiaCrearPersonaConSusDatos() {
        // Arrange
        // Act
        Persona persona = FixturesDominio.persona("1234567", "Ana Maria", RolPersona.OPERADOR);

        // Assert
        assertThat(persona.cedula()).isEqualTo(new Cedula("1234567"));
        assertThat(persona.nombre()).isEqualTo(new Nombre("Ana Maria"));
        assertThat(persona.rol()).isEqualTo(RolPersona.OPERADOR);
    }

    @Test
    void deberiaRechazarPersonaIncompleta() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new Persona(null, new Nombre("Ana Maria"), RolPersona.OPERADOR))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new Persona(new Cedula("1234567"), null, RolPersona.OPERADOR))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new Persona(new Cedula("1234567"), new Nombre("Ana Maria"), null))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaPermitirQueUnConductorPrefieraUnOperador() {
        // Arrange
        Persona conductor = FixturesDominio.persona("1111111", "Carlos Conductor", RolPersona.CONDUCTOR);
        Persona operador = FixturesDominio.persona("2222222", "Olga Operadora", RolPersona.OPERADOR);

        // Act
        conductor.preferirOperador(operador);

        // Assert
        assertThat(conductor.operadoresPreferidos()).containsExactly(new Cedula("2222222"));
    }

    @Test
    void deberiaRechazarPreferenciasInvalidas() {
        // Arrange
        Persona conductor = FixturesDominio.persona("1111111", "Carlos Conductor", RolPersona.CONDUCTOR);
        Persona otroConductor = FixturesDominio.persona("3333333", "Camila Conductora", RolPersona.CONDUCTOR);
        Persona productor = FixturesDominio.persona("4444444", "Pedro Productor", RolPersona.PRODUCTOR);
        Persona operador = FixturesDominio.persona("2222222", "Olga Operadora", RolPersona.OPERADOR);

        // Act
        // Assert
        assertThatThrownBy(() -> productor.preferirOperador(operador)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> conductor.preferirOperador(otroConductor))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> conductor.preferirOperador(null)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> conductor.operadoresPreferidos().add(new Cedula("2222222")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void deberiaIdentificarsePorCedula() {
        // Arrange
        Persona uno = FixturesDominio.persona("1234567", "Ana Maria", RolPersona.OPERADOR);
        Persona mismaCedula = FixturesDominio.persona("1234567", "Otro Nombre", RolPersona.CONDUCTOR);
        Persona otra = FixturesDominio.persona("7654321", "Ana Maria", RolPersona.OPERADOR);

        // Act
        // Assert
        assertThat(uno.equals(mismaCedula)).isTrue();
        assertThat(uno.hashCode()).isEqualTo(mismaCedula.hashCode());
        assertThat(uno.equals(otra)).isFalse();
        assertThat(uno.equals(uno)).isTrue();
        assertThat(uno.equals("texto")).isFalse();
    }
}
