package com.jcaa.udec.collections.domain.core.factory;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.model.CompaniaProductora;
import com.jcaa.udec.collections.domain.core.model.Programa;
import com.jcaa.udec.collections.domain.core.specification.Especificacion;
import com.jcaa.udec.collections.domain.core.specification.PersonalPerteneceAProductoraEspecificacion;
import com.jcaa.udec.collections.domain.core.valueobject.Consorcio;
import com.jcaa.udec.collections.domain.core.valueobject.GeneroPrograma;
import com.jcaa.udec.collections.domain.core.valueobject.Nombre;
import com.jcaa.udec.collections.domain.core.valueobject.Participacion;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import java.util.Collection;
import java.util.List;

public final class ProgramaFactory {
    private static final String MENSAJE_PRODUCTORA = "El programa requiere una compañía productora.";
    private static final String MENSAJE_PERSONAL =
            "El personal del programa debe trabajar en la productora del consorcio con el mismo rol.";

    private ProgramaFactory() {
    }

    public static Programa crear(
            String id,
            String nombre,
            String genero,
            RadioId radioId,
            CompaniaProductora productora,
            List<Participacion> participaciones) {
        if (productora == null) {
            throw new DatoInvalidoException(MENSAJE_PRODUCTORA);
        }
        Especificacion<Collection<Participacion>> personalDeLaProductora =
                new PersonalPerteneceAProductoraEspecificacion(productora);
        if (!personalDeLaProductora.esSatisfechaPor(participaciones)) {
            throw new DatoInvalidoException(MENSAJE_PERSONAL);
        }
        return new Programa(
                new ProgramaId(id),
                new Nombre(nombre),
                GeneroPrograma.desde(genero),
                new Consorcio(radioId, productora.rfc()),
                participaciones);
    }
}
