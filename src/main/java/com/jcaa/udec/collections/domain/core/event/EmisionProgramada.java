package com.jcaa.udec.collections.domain.core.event;

import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import java.time.Instant;
import java.time.LocalDate;

public record EmisionProgramada(
        EmisionId emisionId, ProgramaId programaId, RadioId radioId, LocalDate fecha, Instant ocurridoEn)
        implements EventoDominio {
}
