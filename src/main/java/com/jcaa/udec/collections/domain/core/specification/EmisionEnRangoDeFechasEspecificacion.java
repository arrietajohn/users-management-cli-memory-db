package com.jcaa.udec.collections.domain.core.specification;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.model.Emision;
import java.time.LocalDate;

public class EmisionEnRangoDeFechasEspecificacion implements Especificacion<Emision> {
    private static final String MENSAJE_ERROR = "El rango de fechas es invalido.";
    private final LocalDate desde;
    private final LocalDate hasta;

    public EmisionEnRangoDeFechasEspecificacion(LocalDate desde, LocalDate hasta) {
        if (desde == null || hasta == null || desde.isAfter(hasta)) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    public boolean esSatisfechaPor(Emision emision) {
        return !emision.fecha().isBefore(desde) && !emision.fecha().isAfter(hasta);
    }
}
