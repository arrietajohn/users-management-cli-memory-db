package com.jcaa.udec.collections.domain.core.service;

import com.jcaa.udec.collections.domain.core.valueobject.EstadisticaPrograma;

public interface PoliticaDePopularidad {
    PoliticaDePopularidad POR_ENCUESTADOS = EstadisticaPrograma::totalEncuestados;
    PoliticaDePopularidad POR_APROBACION = EstadisticaPrograma::porcentajeAprobacion;

    double puntaje(EstadisticaPrograma estadistica);
}
