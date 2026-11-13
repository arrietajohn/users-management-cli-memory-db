package com.jcaa.udec.collections.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class UsuarioTest {
  private static final String ID_VALIDO = "123";
  private static final String PASSWORD_VALIDO = "ClaveSegura1!";
  private static final String NOMBRE_VALIDO = "Ana Perez";
  private static final String EMAIL_VALIDO = "ana_perez@example.com";
  private static final String MENSAJE_DATOS_INVALIDOS = "Los datos del usuario son inválidos.";

  @Test
  void deberiaCrearUsuarioConDatosValidos() {
    // Arrange
    // Act
    Usuario usuario = new Usuario(ID_VALIDO, PASSWORD_VALIDO, NOMBRE_VALIDO, EMAIL_VALIDO);

    // Assert
    assertThat(usuario)
        .extracting(Usuario::getId, Usuario::getNombre, Usuario::getEmail)
        .containsExactly(ID_VALIDO, NOMBRE_VALIDO, EMAIL_VALIDO);
  }

  @Test
  void deberiaCrearUsuarioConBuilder() {
    // Arrange
    // Act
    Usuario usuario =
        Usuario.builder()
            .id(ID_VALIDO)
            .password(PASSWORD_VALIDO)
            .nombre(NOMBRE_VALIDO)
            .email(EMAIL_VALIDO)
            .build();

    // Assert
    assertThat(usuario)
        .extracting(Usuario::getId, Usuario::getNombre, Usuario::getEmail)
        .containsExactly(ID_VALIDO, NOMBRE_VALIDO, EMAIL_VALIDO);
  }

  @ParameterizedTest
  @MethodSource("datosInvalidos")
  void deberiaRechazarUsuarioConDatosInvalidos(
      String id, String password, String nombre, String email) {
    // Arrange
    // Act
    // Assert
    assertThatThrownBy(() -> new Usuario(id, password, nombre, email))
        .isInstanceOf(UsuarioInvalidoException.class)
        .hasMessage(MENSAJE_DATOS_INVALIDOS);
  }

  private static Stream<Arguments> datosInvalidos() {
    return Stream.of(
        Arguments.of("abc", PASSWORD_VALIDO, NOMBRE_VALIDO, EMAIL_VALIDO),
        Arguments.of(ID_VALIDO, "Clave1!", NOMBRE_VALIDO, EMAIL_VALIDO),
        Arguments.of(ID_VALIDO, PASSWORD_VALIDO, "An", EMAIL_VALIDO),
        Arguments.of(ID_VALIDO, PASSWORD_VALIDO, NOMBRE_VALIDO, "correo-invalido"));
  }
}
