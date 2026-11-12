package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import java.util.Objects;

public enum TipoActividad {
    MODA("Moda"),
    PUBLICIDAD_CINE("Publicidad y cine");

    private final String descripcion;

    TipoActividad(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public static TipoActividad desde(String valor) {
        if (Objects.isNull(valor) || valor.isBlank()) {
            throw new ClienteInvalidoException();
        }
        for (TipoActividad tipo : values()) {
            if (tipo.name().equalsIgnoreCase(valor.trim())) {
                return tipo;
            }
        }
        throw new ClienteInvalidoException();
    }
}
