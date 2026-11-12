package com.jcaa.udec.collections.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.TipoActividad;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class ClienteTest {
    private static final String CODIGO_VALIDO = "CLI-001";
    private static final String NOMBRE_VALIDO = "Moda Caribe S.A.S.";
    private static final String DIRECCION_VALIDA = "Calle 30 # 17-25, Cartagena";
    private static final String TELEFONO_VALIDO = "3001234567";
    private static final String CONTACTO_VALIDO = "Laura Gomez";
    private static final TipoActividad TIPO_VALIDO = TipoActividad.MODA;
    private static final String MENSAJE_DATOS_INVALIDOS = "Los datos del cliente son invalidos.";

    @Test
    void deberiaCrearClienteConDatosValidos() {
        // Arrange
        // Act
        Cliente cliente = new Cliente(
                CODIGO_VALIDO,
                NOMBRE_VALIDO,
                DIRECCION_VALIDA,
                TELEFONO_VALIDO,
                CONTACTO_VALIDO,
                TIPO_VALIDO);

        // Assert
        assertThat(cliente)
                .extracting(
                        Cliente::getCodigo,
                        Cliente::getNombre,
                        Cliente::getDireccion,
                        Cliente::getTelefono,
                        Cliente::getPersonaContacto,
                        Cliente::getTipoActividad)
                .containsExactly(
                        CODIGO_VALIDO,
                        NOMBRE_VALIDO,
                        DIRECCION_VALIDA,
                        TELEFONO_VALIDO,
                        CONTACTO_VALIDO,
                        TIPO_VALIDO);
    }

    @Test
    void deberiaCrearClienteConBuilder() {
        // Arrange
        // Act
        Cliente cliente = Cliente.builder()
                .codigo(CODIGO_VALIDO)
                .nombre(NOMBRE_VALIDO)
                .direccion(DIRECCION_VALIDA)
                .telefono(TELEFONO_VALIDO)
                .personaContacto(CONTACTO_VALIDO)
                .tipoActividad(TipoActividad.PUBLICIDAD_CINE)
                .build();

        // Assert
        assertThat(cliente)
                .extracting(Cliente::getCodigo, Cliente::getTipoActividad)
                .containsExactly(CODIGO_VALIDO, TipoActividad.PUBLICIDAD_CINE);
    }

    @ParameterizedTest
    @MethodSource("datosInvalidos")
    void deberiaRechazarClienteConDatosInvalidos(
            String codigo,
            String nombre,
            String direccion,
            String telefono,
            String contacto,
            TipoActividad tipoActividad) {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() ->
                        new Cliente(codigo, nombre, direccion, telefono, contacto, tipoActividad))
                .isInstanceOf(ClienteInvalidoException.class)
                .hasMessage(MENSAJE_DATOS_INVALIDOS);
    }

    private static Stream<Arguments> datosInvalidos() {
        return Stream.of(
                Arguments.of("C 1", NOMBRE_VALIDO, DIRECCION_VALIDA, TELEFONO_VALIDO,
                        CONTACTO_VALIDO, TIPO_VALIDO),
                Arguments.of(CODIGO_VALIDO, "AB", DIRECCION_VALIDA, TELEFONO_VALIDO,
                        CONTACTO_VALIDO, TIPO_VALIDO),
                Arguments.of(CODIGO_VALIDO, NOMBRE_VALIDO, "Cl 1", TELEFONO_VALIDO,
                        CONTACTO_VALIDO, TIPO_VALIDO),
                Arguments.of(CODIGO_VALIDO, NOMBRE_VALIDO, DIRECCION_VALIDA, "123",
                        CONTACTO_VALIDO, TIPO_VALIDO),
                Arguments.of(CODIGO_VALIDO, NOMBRE_VALIDO, DIRECCION_VALIDA, TELEFONO_VALIDO,
                        "Lu", TIPO_VALIDO),
                Arguments.of(CODIGO_VALIDO, NOMBRE_VALIDO, DIRECCION_VALIDA, TELEFONO_VALIDO,
                        CONTACTO_VALIDO, null));
    }
}
