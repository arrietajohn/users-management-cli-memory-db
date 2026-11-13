package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.command.CrearRadioComando;
import com.jcaa.udec.collections.application.service.mapper.RadioMapper;
import com.jcaa.udec.collections.application.service.ports.in.AgregarRadioUseCase;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.GuardarRadioPort;

public class AgregarRadioService implements AgregarRadioUseCase {
    private final GuardarRadioPort guardarRadioPort;

    public AgregarRadioService(GuardarRadioPort guardarRadioPort) {
        this.guardarRadioPort = guardarRadioPort;
    }

    @Override
    public void guardar(CrearRadioComando comando) {
        Radio radio = RadioMapper.mapearARadio(comando);
        guardarRadioPort.guardar(radio);
    }
}
