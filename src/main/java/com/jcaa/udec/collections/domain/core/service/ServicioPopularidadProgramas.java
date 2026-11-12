package com.jcaa.udec.collections.domain.core.service;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.model.Emision;
import com.jcaa.udec.collections.domain.core.valueobject.EstadisticaPrograma;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.ResultadoEncuesta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ServicioPopularidadProgramas {
    private static final String MENSAJE_POLITICA = "La política de popularidad es obligatoria.";

    public List<EstadisticaPrograma> estadisticasPorPrograma(Collection<Emision> emisiones) {
        Map<ProgramaId, EstadisticaPrograma> acumuladas = new LinkedHashMap<>();
        for (Emision emision : emisiones) {
            for (ResultadoEncuesta resultado : emision.encuestas()) {
                acumuladas.compute(emision.programaId(), (programaId, actual) -> {
                    EstadisticaPrograma base = actual == null ? EstadisticaPrograma.vacia(programaId) : actual;
                    return base.acumular(resultado);
                });
            }
        }
        return new ArrayList<>(acumuladas.values());
    }

    public Optional<EstadisticaPrograma> masPopular(
            Collection<Emision> emisiones, PoliticaDePopularidad politica) {
        return estadisticasPorPrograma(emisiones).stream().max(comparador(politica));
    }

    public Optional<EstadisticaPrograma> menosPopular(
            Collection<Emision> emisiones, PoliticaDePopularidad politica) {
        return estadisticasPorPrograma(emisiones).stream().min(comparador(politica));
    }

    private Comparator<EstadisticaPrograma> comparador(PoliticaDePopularidad politica) {
        if (politica == null) {
            throw new DatoInvalidoException(MENSAJE_POLITICA);
        }
        return Comparator.comparingDouble(politica::puntaje);
    }
}
