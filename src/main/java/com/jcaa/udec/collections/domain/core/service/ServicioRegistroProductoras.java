package com.jcaa.udec.collections.domain.core.service;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.NombreProductoraDuplicadoException;
import com.jcaa.udec.collections.domain.core.exception.PersonaEnVariasProductorasException;
import com.jcaa.udec.collections.domain.core.model.CompaniaProductora;
import com.jcaa.udec.collections.domain.core.model.Persona;
import com.jcaa.udec.collections.domain.core.valueobject.Nombre;
import com.jcaa.udec.collections.domain.core.valueobject.Rfc;
import java.util.Collection;
import java.util.List;

public class ServicioRegistroProductoras {
    private static final String MENSAJE_RFC_DUPLICADO = "Ya existe una compañía productora con ese RFC.";

    public CompaniaProductora registrar(
            Rfc rfc, Nombre nombre, List<String> telefonos, Collection<CompaniaProductora> existentes) {
        validarNombreDisponible(nombre, existentes);
        boolean rfcRepetido = existentes.stream().anyMatch(productora -> productora.rfc().equals(rfc));
        if (rfcRepetido) {
            throw new DatoInvalidoException(MENSAJE_RFC_DUPLICADO);
        }
        return new CompaniaProductora(rfc, nombre, telefonos);
    }

    public void validarNombreDisponible(Nombre nombre, Collection<CompaniaProductora> existentes) {
        boolean repetido = existentes.stream()
                .anyMatch(productora -> productora.nombre().valor().equalsIgnoreCase(nombre.valor()));
        if (repetido) {
            throw new NombreProductoraDuplicadoException();
        }
    }

    public void contratar(CompaniaProductora destino, Persona persona, Collection<CompaniaProductora> todas) {
        boolean trabajaEnOtra = todas.stream()
                .filter(productora -> !productora.rfc().equals(destino.rfc()))
                .anyMatch(productora -> productora.empleaA(persona.cedula()));
        if (trabajaEnOtra) {
            throw new PersonaEnVariasProductorasException();
        }
        destino.contratar(persona);
    }
}
