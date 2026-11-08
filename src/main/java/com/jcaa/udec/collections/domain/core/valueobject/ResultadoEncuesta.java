package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.SumaRespuestasInvalidaException;

import java.util.Objects;

/**
 * Value Object que representa los resultados de una encuesta de audiencia.
 * Protege la invariante central del CEA: la suma de aprobaciones, rechazos e
 * indiferencias debe ser igual al total de encuestados.
 */
public final class ResultadoEncuesta {

    private final int encuestados;
    private final int aprobaciones;
    private final int rechazos;
    private final int indiferencias;

    public ResultadoEncuesta(int encuestados, int aprobaciones, int rechazos, int indiferencias) {
        if (encuestados <= 0) {
            throw new SumaRespuestasInvalidaException("Debe haber al menos un encuestado.");
        }
        if (aprobaciones < 0 || rechazos < 0 || indiferencias < 0) {
            throw new SumaRespuestasInvalidaException("Las respuestas no pueden ser negativas.");
        }
        long suma = (long) aprobaciones + rechazos + indiferencias;
        if (suma != encuestados) {
            throw new SumaRespuestasInvalidaException(
                    "La suma de aprobaciones, rechazos e indiferencias debe ser igual al total de encuestados.");
        }
        this.encuestados = encuestados;
        this.aprobaciones = aprobaciones;
        this.rechazos = rechazos;
        this.indiferencias = indiferencias;
    }

    public int encuestados() {
        return encuestados;
    }

    public int aprobaciones() {
        return aprobaciones;
    }

    public int rechazos() {
        return rechazos;
    }

    public int indiferencias() {
        return indiferencias;
    }

    public double porcentajeAprobacion() {
        return aprobaciones * 100.0 / encuestados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResultadoEncuesta)) return false;
        ResultadoEncuesta that = (ResultadoEncuesta) o;
        return encuestados == that.encuestados
                && aprobaciones == that.aprobaciones
                && rechazos == that.rechazos
                && indiferencias == that.indiferencias;
    }

    @Override
    public int hashCode() {
        return Objects.hash(encuestados, aprobaciones, rechazos, indiferencias);
    }

    @Override
    public String toString() {
        return "ResultadoEncuesta{encuestados=" + encuestados
                + ", aprobaciones=" + aprobaciones
                + ", rechazos=" + rechazos
                + ", indiferencias=" + indiferencias + "}";
    }
}
