package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.RadioYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.domain.port.out.GuardarRadioPort;

import java.util.List;
import java.util.Objects;

public class GuardarRadioAdapter implements GuardarRadioPort {
    private final List<Radio> radios = RadiosMemoria.obtenerRadios();

    @Override
    public void guardar(Radio radio) {
        for (Radio radioRegistrada : radios) {
            if (Objects.equals(radioRegistrada.getId(), radio.getId())) {
                throw new RadioYaExisteException();
            }
        }
        radios.add(radio);
    }
}
