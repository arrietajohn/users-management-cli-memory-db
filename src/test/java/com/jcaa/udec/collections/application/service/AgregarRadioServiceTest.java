package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.CrearRadioComando;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.GuardarRadioPort;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AgregarRadioServiceTest {
    private static final String ID = "123";
    private static final String NOMBRE = "La Radio Caribe";
    private static final String FRECUENCIA = "98.5";
    private static final String TIPO_TRANSMISION = "FM";

    @Test
    void deberiaMapearYGuardarRadio() {
        // Arrange
        GuardarRadioPortStub guardarRadioPort = new GuardarRadioPortStub();
        AgregarRadioService service = new AgregarRadioService(guardarRadioPort);
        CrearRadioComando comando = new CrearRadioComando(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);

        // Act
        service.guardar(comando);

        // Assert
        assertThat(guardarRadioPort.getRadiosGuardadas())
                .singleElement()
                .extracting(
                        Radio::getId, Radio::getNombre, Radio::getFrecuencia, Radio::getTipoTransmision)
                .containsExactly(ID, NOMBRE, FRECUENCIA, TIPO_TRANSMISION);
    }

    private static final class GuardarRadioPortStub implements GuardarRadioPort {
        private final List<Radio> radiosGuardadas = new ArrayList<>();

        @Override
        public void guardar(Radio radio) {
            radiosGuardadas.add(radio);
        }

        private List<Radio> getRadiosGuardadas() {
            return List.copyOf(radiosGuardadas);
        }
    }
}
