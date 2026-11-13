package com.jcaa.udec.collections.domain.core.event;

import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import java.time.Instant;

public record EncuestaRegistrada(EmisionId emisionId, int totalEncuestados, Instant ocurridoEn)
        implements EventoDominio {
}
