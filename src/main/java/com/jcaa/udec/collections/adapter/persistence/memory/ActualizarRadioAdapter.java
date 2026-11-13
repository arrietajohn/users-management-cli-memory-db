package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.RadioNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.ActualizarRadioPort;

import java.util.List;
import java.util.Objects;

public class ActualizarRadioAdapter implements ActualizarRadioPort {
    private final List<Radio> radios = RadiosMemoria.obtenerRadios();

    @Override
    public void actualizar(Radio radio) {
        for (int indice = 0; indice < radios.size(); indice++) {
            if (Objects.equals(radios.get(indice).getId(), radio.getId())) {
                radios.set(indice, radio);
                return;
            }
        }
        throw new RadioNoExisteException();
    }
}
