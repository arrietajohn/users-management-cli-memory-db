package com.jcaa.udec.collections.domain.core.specification;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.model.CompaniaProductora;
import com.jcaa.udec.collections.domain.core.valueobject.Participacion;
import java.util.Collection;

public class PersonalPerteneceAProductoraEspecificacion implements Especificacion<Collection<Participacion>> {
    private static final String MENSAJE_ERROR = "La especificación requiere una compañía productora.";
    private final CompaniaProductora productora;

    public PersonalPerteneceAProductoraEspecificacion(CompaniaProductora productora) {
        if (productora == null) {
            throw new DatoInvalidoException(MENSAJE_ERROR);
        }
        this.productora = productora;
    }

    @Override
    public boolean esSatisfechaPor(Collection<Participacion> participaciones) {
        return participaciones != null
                && participaciones.stream()
                        .allMatch(participacion -> participacion != null
                                && productora
                                        .buscarPersona(participacion.cedula())
                                        .map(persona -> persona.rol() == participacion.rol())
                                        .orElse(false));
    }
}
