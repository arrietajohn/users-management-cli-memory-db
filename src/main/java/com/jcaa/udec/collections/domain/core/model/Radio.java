package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
import lombok.Builder;

public class Radio {
    private final RadioId id;
    private final NombreRadio nombre;
    private final Frecuencia frecuencia;
    private final TipoTransmision tipoTransmision;

    @Builder
    public Radio(String id, String nombre, String frecuencia, String tipoTransmision) {
        this.id = new RadioId(id);
        this.nombre = new NombreRadio(nombre);
        this.frecuencia = new Frecuencia(frecuencia);
        this.tipoTransmision = TipoTransmision.desde(tipoTransmision);
    }

    public String getId() {
        return id.valor();
    }

    public String getNombre() {
        return nombre.valor();
    }

    public String getFrecuencia() {
        return frecuencia.valor();
    }

    public String getTipoTransmision() {
        return tipoTransmision.name();
    }
}
