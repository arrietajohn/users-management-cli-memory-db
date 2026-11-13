package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.RadioNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.ObtenerRadiosPort;

import java.util.List;
import java.util.Objects;

public class ObtenerRadiosAdapter implements ObtenerRadiosPort {
    private final List<Radio> radios = RadiosMemoria.obtenerRadios();

    @Override
    public List<Radio> obtenerTodos() {
        return List.copyOf(radios);
    }

    @Override
    public Radio buscarPorId(String id) {
        for (Radio radio : radios) {
            if (Objects.equals(radio.getId(), id)) {
                return radio;
            }
        }
        throw new RadioNoExisteException();
    }
}
