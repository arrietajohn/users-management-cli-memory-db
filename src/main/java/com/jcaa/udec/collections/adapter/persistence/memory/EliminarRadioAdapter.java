package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.RadioNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.EliminarRadioPort;

import java.util.List;
import java.util.Objects;

public class EliminarRadioAdapter implements EliminarRadioPort {
    private final List<Radio> radios = RadiosMemoria.obtenerRadios();

    @Override
    public void eliminar(String id) {
        for (Radio radio : radios) {
            if (Objects.equals(radio.getId(), id)) {
                radios.remove(radio);
                return;
            }
        }
        throw new RadioNoExisteException();
    }
}
