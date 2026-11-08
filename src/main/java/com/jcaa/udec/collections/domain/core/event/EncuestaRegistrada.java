package com.jcaa.udec.collections.domain.core.event;

import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.EncuestaId;

import java.time.Instant;

/**
 * Evento de dominio: se registraron los resultados de audiencia de una emisión.
 * Es inmutable y se nombra en pasado porque expresa algo que ya ocurrió.
 */
public final class EncuestaRegistrada implements EventoDominio {

    private final EncuestaId encuestaId;
    private final EmisionId emisionId;
    private final int totalEncuestados;
    private final Instant ocurridoEn;

    public EncuestaRegistrada(EncuestaId encuestaId, EmisionId emisionId, int totalEncuestados) {
        this.encuestaId = encuestaId;
        this.emisionId = emisionId;
        this.totalEncuestados = totalEncuestados;
        this.ocurridoEn = Instant.now();
    }

    public EncuestaId encuestaId() {
        return encuestaId;
    }

    public EmisionId emisionId() {
        return emisionId;
    }

    public int totalEncuestados() {
        return totalEncuestados;
    }

    @Override
    public Instant ocurridoEn() {
        return ocurridoEn;
    }
}
