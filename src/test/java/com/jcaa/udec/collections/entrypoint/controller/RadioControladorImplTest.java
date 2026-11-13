package com.jcaa.udec.collections.entrypoint.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarRadioComando;
import com.jcaa.udec.collections.application.service.dto.command.CrearRadioComando;
import com.jcaa.udec.collections.application.service.dto.command.EliminarRadioComando;
import com.jcaa.udec.collections.application.service.dto.query.ObtenerRadioConsulta;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarRadioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.AgregarRadioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.EliminarRadioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerRadioUseCase;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerRadioResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.RadioResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class RadioControladorImplTest {
    private static final String ID = "123";
    private static final String NOMBRE = "La Radio Caribe";
    private static final String FRECUENCIA = "98.5";
    private static final String TIPO_TRANSMISION = "FM";

    @Test
    void deberiaRegistrarRadio() {
        // Arrange
        AgregarRadioUseCaseStub agregarRadioUseCase = new AgregarRadioUseCaseStub();
        RadioControlador controlador = crearControlador(agregarRadioUseCase);
        RegistrarRadioPeticion peticion =
                new RegistrarRadioPeticion(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);

        // Act
        controlador.registrar(peticion);

        // Assert
        assertThat(agregarRadioUseCase.getComandos())
                .singleElement()
                .extracting(
                        CrearRadioComando::id,
                        CrearRadioComando::nombre,
                        CrearRadioComando::frecuencia,
                        CrearRadioComando::tipoTransmision)
                .containsExactly(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);
    }

    @Test
    void deberiaObtenerRadioPorId() {
        // Arrange
        ObtenerRadioUseCaseStub obtenerRadioUseCase = new ObtenerRadioUseCaseStub();
        RadioControlador controlador = new RadioControladorImpl(
                new AgregarRadioUseCaseStub(),
                obtenerRadioUseCase,
                new ActualizarRadioUseCaseStub(),
                new EliminarRadioUseCaseStub());

        // Act
        ObtenerRadioResponse response = controlador.obtenerPorId(ID);

        // Assert
        assertThat(obtenerRadioUseCase.getConsultas())
                .singleElement()
                .extracting(ObtenerRadioConsulta::id)
                .isEqualTo(ID);
        assertThat(response.toString())
                .contains(
                        "ID: " + ID,
                        "NOMBRE: " + NOMBRE,
                        "FRECUENCIA: " + FRECUENCIA,
                        "TIPO: " + TIPO_TRANSMISION);
    }

    @Test
    void deberiaObtenerTodasLasRadios() {
        // Arrange
        RadioControlador controlador = crearControlador(new AgregarRadioUseCaseStub());

        // Act
        ObtenerRadioResponse response = controlador.obtenerTodos();

        // Assert
        assertThat(response.estaVacia()).isFalse();
        assertThat(response.radios())
                .singleElement()
                .extracting(
                        RadioResponse::id,
                        RadioResponse::nombre,
                        RadioResponse::frecuencia,
                        RadioResponse::tipoTransmision)
                .containsExactly(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);
    }

    @Test
    void deberiaActualizarRadio() {
        // Arrange
        ActualizarRadioUseCaseStub actualizarRadioUseCase = new ActualizarRadioUseCaseStub();
        RadioControlador controlador = new RadioControladorImpl(
                new AgregarRadioUseCaseStub(),
                new ObtenerRadioUseCaseStub(),
                actualizarRadioUseCase,
                new EliminarRadioUseCaseStub());
        ActualizarRadioPeticion peticion =
                new ActualizarRadioPeticion(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);

        // Act
        controlador.actualizar(peticion);

        // Assert
        assertThat(actualizarRadioUseCase.getComandos())
                .singleElement()
                .extracting(
                        ActualizarRadioComando::id,
                        ActualizarRadioComando::nombre,
                        ActualizarRadioComando::frecuencia,
                        ActualizarRadioComando::tipoTransmision)
                .containsExactly(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);
    }

    @Test
    void deberiaEliminarRadio() {
        // Arrange
        EliminarRadioUseCaseStub eliminarRadioUseCase = new EliminarRadioUseCaseStub();
        RadioControlador controlador = new RadioControladorImpl(
                new AgregarRadioUseCaseStub(),
                new ObtenerRadioUseCaseStub(),
                new ActualizarRadioUseCaseStub(),
                eliminarRadioUseCase);

        // Act
        controlador.eliminar(ID);

        // Assert
        assertThat(eliminarRadioUseCase.getComandos())
                .singleElement()
                .extracting(EliminarRadioComando::id)
                .isEqualTo(ID);
    }

    private static RadioControlador crearControlador(AgregarRadioUseCase agregarRadioUseCase) {
        return new RadioControladorImpl(
                agregarRadioUseCase,
                new ObtenerRadioUseCaseStub(),
                new ActualizarRadioUseCaseStub(),
                new EliminarRadioUseCaseStub());
    }

    private static Radio crearRadio() {
        return new Radio(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);
    }

    private static final class AgregarRadioUseCaseStub implements AgregarRadioUseCase {
        private final List<CrearRadioComando> comandos = new ArrayList<>();

        @Override
        public void guardar(CrearRadioComando comando) {
            comandos.add(comando);
        }

        private List<CrearRadioComando> getComandos() {
            return List.copyOf(comandos);
        }
    }

    private static final class ObtenerRadioUseCaseStub implements ObtenerRadioUseCase {
        private final List<ObtenerRadioConsulta> consultas = new ArrayList<>();

        @Override
        public List<Radio> obtenerTodos() {
            return List.of(crearRadio());
        }

        @Override
        public Radio obtenerPorId(ObtenerRadioConsulta consulta) {
            consultas.add(consulta);
            return crearRadio();
        }

        private List<ObtenerRadioConsulta> getConsultas() {
            return List.copyOf(consultas);
        }
    }

    private static final class ActualizarRadioUseCaseStub implements ActualizarRadioUseCase {
        private final List<ActualizarRadioComando> comandos = new ArrayList<>();

        @Override
        public void actualizar(ActualizarRadioComando comando) {
            comandos.add(comando);
        }

        private List<ActualizarRadioComando> getComandos() {
            return List.copyOf(comandos);
        }
    }

    private static final class EliminarRadioUseCaseStub implements EliminarRadioUseCase {
        private final List<EliminarRadioComando> comandos = new ArrayList<>();

        @Override
        public void eliminar(EliminarRadioComando comando) {
            comandos.add(comando);
        }

        private List<EliminarRadioComando> getComandos() {
            return List.copyOf(comandos);
        }
    }
}
