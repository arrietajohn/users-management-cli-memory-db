package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.query.ObtenerRadioConsulta;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.ObtenerRadiosPort;
import java.util.List;
import org.junit.jupiter.api.Test;

class ObtenerRadiosServiceTest {
    private static final String ID = "123";
    private static final String NOMBRE = "La Radio Caribe";
    private static final String FRECUENCIA = "98.5";
    private static final String TIPO_TRANSMISION = "FM";

    @Test
    void deberiaObtenerTodasLasRadios() {
        // Arrange
        Radio radio = crearRadio();
        List<Radio> radiosEsperadas = List.of(radio);
        ObtenerRadiosPortStub obtenerRadiosPort = new ObtenerRadiosPortStub(radiosEsperadas, radio);
        ObtenerRadiosService service = new ObtenerRadiosService(obtenerRadiosPort);

        // Act
        List<Radio> radios = service.obtenerTodos();

        // Assert
        assertThat(radios).containsExactly(radio);
    }

    @Test
    void deberiaObtenerRadioPorId() {
        // Arrange
        Radio radioEsperada = crearRadio();
        ObtenerRadiosPortStub obtenerRadiosPort =
                new ObtenerRadiosPortStub(List.of(radioEsperada), radioEsperada);
        ObtenerRadiosService service = new ObtenerRadiosService(obtenerRadiosPort);
        ObtenerRadioConsulta consulta = new ObtenerRadioConsulta(ID);

        // Act
        Radio radio = service.obtenerPorId(consulta);

        // Assert
        assertThat(radio).isSameAs(radioEsperada);
        assertThat(obtenerRadiosPort.getIdConsultado()).isEqualTo(ID);
    }

    private static Radio crearRadio() {
        return new Radio(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);
    }

    private static final class ObtenerRadiosPortStub implements ObtenerRadiosPort {
        private final List<Radio> radios;
        private final Radio radio;
        private String idConsultado;

        private ObtenerRadiosPortStub(List<Radio> radios, Radio radio) {
            this.radios = radios;
            this.radio = radio;
        }

        @Override
        public List<Radio> obtenerTodos() {
            return radios;
        }

        @Override
        public Radio buscarPorId(String id) {
            idConsultado = id;
            return radio;
        }

        private String getIdConsultado() {
            return idConsultado;
        }
    }
}
