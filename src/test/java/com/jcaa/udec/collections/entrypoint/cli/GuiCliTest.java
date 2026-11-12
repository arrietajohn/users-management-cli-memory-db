package com.jcaa.udec.collections.entrypoint.cli;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.domain.core.exception.UsuarioNoExisteException;
import com.jcaa.udec.collections.entrypoint.controller.RadioControlador;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControlador;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarUsuarioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerRadioResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerUsuarioResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.UsuarioResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class GuiCliTest {
    private static final String ID = "123";
    private static final String PASSWORD = "ClaveSegura1!";
    private static final String NOMBRE = "Ana Perez";
    private static final String EMAIL = "ana_perez@example.com";

    @Test
    void deberiaSolicitarOpcionHastaRecibirValorValido() {
        // Arrange
        UsuarioControladorStub controlador = new UsuarioControladorStub();
        GuiCli guiCli = crearGuiCli(controlador, "texto", "99", "\uFEFF2");

        // Act
        String salida = capturarSalida(() -> assertThat(guiCli.obtenerOpcionMenu()).isEqualTo(2));

        // Assert
        assertThat(salida).contains("Opcion [texto] invalida", "Opcion [99] invalida");
    }

    @Test
    void deberiaRegistrarUsuarioLuegoDeCorregirDatosInvalidos() {
        // Arrange
        UsuarioControladorStub controlador = new UsuarioControladorStub();
        GuiCli guiCli = crearGuiCli(
                controlador,
                "1",
                "abc",
                ID,
                "Clave1!",
                PASSWORD,
                "An",
                NOMBRE,
                "correo-invalido",
                EMAIL,
                "9");

        // Act
        String salida = capturarSalida(guiCli::ejecutarAccion);

        // Assert
        assertThat(controlador.getPeticiones())
                .singleElement()
                .extracting(
                        RegistrarUsuarioPeticion::id,
                        RegistrarUsuarioPeticion::password,
                        RegistrarUsuarioPeticion::nombre,
                        RegistrarUsuarioPeticion::email)
                .containsExactly(ID, PASSWORD, NOMBRE, EMAIL);
        assertThat(salida).contains(
                "ID INVALIDO",
                "PASSWORD INVALIDO",
                "NOMBRE INVALIDO",
                "EMAIL INVALIDO",
                "Usuario registrado correctamente.",
                "Esperamos tu regreso. Bye, Bye");
    }

    @Test
    void deberiaMostrarUsuarioBuscado() {
        // Arrange
        UsuarioControladorStub controlador = new UsuarioControladorStub();
        GuiCli guiCli = crearGuiCli(controlador, "2", ID, "9");

        // Act
        String salida = capturarSalida(guiCli::ejecutarAccion);

        // Assert
        assertThat(salida).contains("ID: " + ID, "NOMBRE: " + NOMBRE, "EMAIL: " + EMAIL);
    }

    @Test
    void deberiaInformarCuandoNoHayUsuariosRegistrados() {
        // Arrange
        UsuarioControladorStub controlador = new UsuarioControladorStub();
        GuiCli guiCli = crearGuiCli(controlador, "3", "9");

        // Act
        String salida = capturarSalida(guiCli::ejecutarAccion);

        // Assert
        assertThat(salida).contains("No hay usuarios registrados.");
    }

    @Test
    void deberiaMostrarTodosLosUsuariosRegistrados() {
        // Arrange
        UsuarioControladorStub controlador = new UsuarioControladorStub();
        controlador.registrar(new RegistrarUsuarioPeticion(ID, PASSWORD, NOMBRE, EMAIL));
        GuiCli guiCli = crearGuiCli(controlador, "3", "9");

        // Act
        String salida = capturarSalida(guiCli::ejecutarAccion);

        // Assert
        assertThat(salida).contains("ID: " + ID, "NOMBRE: " + NOMBRE, "EMAIL: " + EMAIL);
    }

    @Test
    void deberiaInformarErrorDelControlador() {
        // Arrange
        UsuarioControladorStub controlador = new UsuarioControladorStub();
        controlador.reportarUsuarioInexistente();
        GuiCli guiCli = crearGuiCli(controlador, "2", ID, "9");

        // Act
        String salida = capturarSalida(guiCli::ejecutarAccion);

        // Assert
        assertThat(salida).contains("ERROR: El usuario no existe.");
    }

    private static GuiCli crearGuiCli(UsuarioControlador controlador, String... entradas) {
        String contenido = String.join(System.lineSeparator(), entradas) + System.lineSeparator();
        return new GuiCli(controlador, new RadioControladorStub(), new Scanner(contenido));
    }

    private static String capturarSalida(Runnable accion) {
        PrintStream salidaOriginal = System.out;
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida, true, StandardCharsets.UTF_8));
        try {
            accion.run();
            return salida.toString(StandardCharsets.UTF_8);
        } finally {
            System.setOut(salidaOriginal);
        }
    }

    private static UsuarioResponse crearUsuarioResponse() {
        return new UsuarioResponse(ID, NOMBRE, EMAIL);
    }

    private static final class UsuarioControladorStub implements UsuarioControlador {
        private final List<RegistrarUsuarioPeticion> peticiones = new ArrayList<>();
        private boolean usuarioInexistente;

        @Override
        public void registrar(RegistrarUsuarioPeticion peticion) {
            peticiones.add(peticion);
        }

        @Override
        public ObtenerUsuarioResponse obtenerPorId(String id) {
            if (usuarioInexistente) {
                throw new UsuarioNoExisteException();
            }
            return new ObtenerUsuarioResponse(List.of(crearUsuarioResponse()));
        }

        @Override
        public ObtenerUsuarioResponse obtenerTodos() {
            return new ObtenerUsuarioResponse(peticiones.stream()
                    .map(peticion -> crearUsuarioResponse())
                    .toList());
        }

        private List<RegistrarUsuarioPeticion> getPeticiones() {
            return List.copyOf(peticiones);
        }

        private void reportarUsuarioInexistente() {
            usuarioInexistente = true;
        }
    }

    private static final class RadioControladorStub implements RadioControlador {
        @Override
        public void registrar(RegistrarRadioPeticion peticion) {
        }

        @Override
        public ObtenerRadioResponse obtenerPorId(String id) {
            return new ObtenerRadioResponse(List.of());
        }

        @Override
        public ObtenerRadioResponse obtenerTodos() {
            return new ObtenerRadioResponse(List.of());
        }

        @Override
        public void actualizar(ActualizarRadioPeticion peticion) {
        }

        @Override
        public void eliminar(String id) {
        }
    }
}
