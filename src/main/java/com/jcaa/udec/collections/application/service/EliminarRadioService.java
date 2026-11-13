package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.command.EliminarRadioComando;
import com.jcaa.udec.collections.application.service.ports.in.EliminarRadioUseCase;
import com.jcaa.udec.collections.domain.port.out.EliminarRadioPort;

public class EliminarRadioService implements EliminarRadioUseCase {
    private final EliminarRadioPort eliminarRadioPort;

    public EliminarRadioService(EliminarRadioPort eliminarRadioPort) {
        this.eliminarRadioPort = eliminarRadioPort;
    }

    @Override
    public void eliminar(EliminarRadioComando comando) {
        eliminarRadioPort.eliminar(comando.id());
    }
}
