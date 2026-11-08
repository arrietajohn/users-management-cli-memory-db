package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

import java.util.Objects;
import java.util.UUID;

/**
 * Value Object que representa el identificador único de una Encuesta.
 */
public final class EncuestaId {

    private final String valor;

    public EncuestaId(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new DatoInvalidoException("El id de la encuesta no puede estar vacío.");
        }
        this.valor = valor.trim();
    }

    public static EncuestaId generar() {
        return new EncuestaId(UUID.randomUUID().toString());
    }

    public String valor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EncuestaId)) return false;
        EncuestaId that = (EncuestaId) o;
        return valor.equals(that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}
