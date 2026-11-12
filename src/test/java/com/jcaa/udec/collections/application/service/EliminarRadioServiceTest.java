package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.jcaa.udec.collections.application.service.dto.command.EliminarRadioComando;
import com.jcaa.udec.collections.domain.port.out.EliminarRadioPort;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class EliminarRadioServiceTest {
    private static final String ID = "123";

    @Test
    void deberiaEliminarRadioPorId() {
        // Arrange
        EliminarRadioPortStub eliminarRadioPort = new EliminarRadioPortStub();
        EliminarRadioService service = new EliminarRadioService(eliminarRadioPort);
        EliminarRadioComando comando = new EliminarRadioComando(ID);

        // Act
        service.eliminar(comando);

        // Assert
        assertThat(eliminarRadioPort.getIdsEliminados()).containsExactly(ID);
    }

    private static final class EliminarRadioPortStub implements EliminarRadioPort {
        private final List<String> idsEliminados = new ArrayList<>();

        @Override
        public void eliminar(String id) {
            idsEliminados.add(id);
        }

        private List<String> getIdsEliminados() {
            return List.copyOf(idsEliminados);
        }
    }
}
