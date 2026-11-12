package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;

public record EstadisticaPrograma(
        ProgramaId programaId, int totalEncuestados, int aprobaciones, int rechazos, int indiferencias) {
    private static final double CIEN = 100.0;
    private static final String MENSAJE_ERROR = "La estadística requiere un programa y cifras no negativas.";

    public EstadisticaPrograma {
        if (programaId == null
                || totalEncuestados < 0
                || aprobaciones < 0
                || rechazos < 0
                || indiferencias < 0) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
    }

    public static EstadisticaPrograma vacia(ProgramaId programaId) {
        return new EstadisticaPrograma(programaId, 0, 0, 0, 0);
    }

    public EstadisticaPrograma acumular(ResultadoEncuesta resultado) {
        if (resultado == null) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
        return new EstadisticaPrograma(
                programaId,
                totalEncuestados + resultado.total(),
                aprobaciones + resultado.aprobaciones(),
                rechazos + resultado.rechazos(),
                indiferencias + resultado.indiferencias());
    }

    public double porcentajeAprobacion() {
        return totalEncuestados == 0 ? 0.0 : aprobaciones * CIEN / totalEncuestados;
    }

    public double porcentajeRechazo() {
        return totalEncuestados == 0 ? 0.0 : rechazos * CIEN / totalEncuestados;
    }
}
