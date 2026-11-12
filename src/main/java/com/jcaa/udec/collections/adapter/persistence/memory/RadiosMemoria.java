package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.model.Radio;

import java.util.ArrayList;
import java.util.List;

final class RadiosMemoria {
    private static final List<Radio> RADIOS = new ArrayList<>();

    private RadiosMemoria() {
    }

    static List<Radio> obtenerRadios() {
        return RADIOS;
    }
}
