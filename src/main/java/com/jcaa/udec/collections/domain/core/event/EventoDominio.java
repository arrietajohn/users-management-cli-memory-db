package com.jcaa.udec.collections.domain.core.event;

import java.time.Instant;

/**
 * Contrato común de los eventos de dominio: un hecho importante que ya ocurrió
 * en el negocio y del que se guarda el momento exacto en que sucedió.
 */
public interface EventoDominio {

    Instant ocurridoEn();
}
