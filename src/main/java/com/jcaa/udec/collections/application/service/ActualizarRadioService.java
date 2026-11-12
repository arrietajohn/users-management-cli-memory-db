package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarRadioComando;
import com.jcaa.udec.collections.application.service.mapper.RadioMapper;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarRadioUseCase;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.ActualizarRadioPort;

public class ActualizarRadioService implements ActualizarRadioUseCase {
    private final ActualizarRadioPort actualizarRadioPort;

    public ActualizarRadioService(ActualizarRadioPort actualizarRadioPort) {
        this.actualizarRadioPort = actualizarRadioPort;
    }

    @Override
    public void actualizar(ActualizarRadioComando comando) {
        Radio radio = RadioMapper.mapearARadio(comando);
        actualizarRadioPort.actualizar(radio);
    }
}
