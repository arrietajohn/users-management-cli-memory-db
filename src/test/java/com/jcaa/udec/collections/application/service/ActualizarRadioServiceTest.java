package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarRadioComando;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.ActualizarRadioPort;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class ActualizarRadioServiceTest {
    private static final String ID = "123";
    private static final String NOMBRE = "La Radio Caribe";
    private static final String FRECUENCIA = "98.5";
    private static final String TIPO_TRANSMISION = "FM";

    @Test
    void deberiaMapearYActualizarRadio() {
        // Arrange
        ActualizarRadioPortStub actualizarRadioPort = new ActualizarRadioPortStub();
        ActualizarRadioService service = new ActualizarRadioService(actualizarRadioPort);
        ActualizarRadioComando comando =
                new ActualizarRadioComando(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);

        // Act
        service.actualizar(comando);

        // Assert
        assertThat(actualizarRadioPort.getRadiosActualizadas())
                .singleElement()
                .extracting(
                        Radio::getId, Radio::getNombre, Radio::getFrecuencia, Radio::getTipoTransmision)
                .containsExactly(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);
    }

    private static final class ActualizarRadioPortStub implements ActualizarRadioPort {
        private final List<Radio> radiosActualizadas = new ArrayList<>();

        @Override
        public void actualizar(Radio radio) {
            radiosActualizadas.add(radio);
        }

        private List<Radio> getRadiosActualizadas() {
            return List.copyOf(radiosActualizadas);
        }
    }
}
