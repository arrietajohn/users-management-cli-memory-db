package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.query.ObtenerRadioConsulta;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerRadioUseCase;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.ObtenerRadiosPort;

import java.util.List;

public class ObtenerRadiosService implements ObtenerRadioUseCase {
    private final ObtenerRadiosPort obtenerRadiosPort;

    public ObtenerRadiosService(ObtenerRadiosPort obtenerRadiosPort) {
        this.obtenerRadiosPort = obtenerRadiosPort;
    }

    @Override
    public List<Radio> obtenerTodos() {
        return obtenerRadiosPort.obtenerTodos();
    }

    @Override
    public Radio obtenerPorId(ObtenerRadioConsulta consulta) {
        return obtenerRadiosPort.buscarPorId(consulta.id());
    }
}
