package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.Cedula;
import com.jcaa.udec.collections.domain.core.valueobject.Nombre;
import com.jcaa.udec.collections.domain.core.valueobject.Rfc;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class CompaniaProductora {
    private static final String FORMATO_TELEFONO = "\\+?[0-9]{7,15}";
    private static final String MENSAJE_DATOS = "La productora requiere RFC, nombre y al menos un teléfono.";
    private static final String MENSAJE_TELEFONO = "Cada teléfono debe tener entre 7 y 15 dígitos.";
    private static final String MENSAJE_PERSONA = "La persona es obligatoria.";
    private static final String MENSAJE_YA_TRABAJA = "La persona ya trabaja en esta productora.";
    private final Rfc rfc;
    private final Nombre nombre;
    private final List<String> telefonos;
    private final Map<Cedula, Persona> personal = new LinkedHashMap<>();

    public CompaniaProductora(Rfc rfc, Nombre nombre, List<String> telefonos) {
        if (rfc == null || nombre == null || telefonos == null || telefonos.isEmpty()) {
            throw new DatoInvalidoException(MENSAJE_DATOS);
        }
        boolean telefonosValidos = telefonos.stream()
                .allMatch(telefono -> telefono != null && telefono.trim().matches(FORMATO_TELEFONO));
        if (!telefonosValidos) {
            throw new DatoInvalidoException(MENSAJE_TELEFONO);
        }
        this.rfc = rfc;
        this.nombre = nombre;
        this.telefonos = telefonos.stream().map(String::trim).toList();
    }

    public void contratar(Persona persona) {
        if (persona == null) {
            throw new DatoInvalidoException(MENSAJE_PERSONA);
        }
        if (personal.containsKey(persona.cedula())) {
            throw new DatoInvalidoException(MENSAJE_YA_TRABAJA);
        }
        personal.put(persona.cedula(), persona);
    }

    public boolean empleaA(Cedula cedula) {
        return personal.containsKey(cedula);
    }

    public Optional<Persona> buscarPersona(Cedula cedula) {
        return Optional.ofNullable(personal.get(cedula));
    }

    public List<Persona> personal() {
        return List.copyOf(personal.values());
    }

    public Rfc rfc() {
        return rfc;
    }

    public Nombre nombre() {
        return nombre;
    }

    public List<String> telefonos() {
        return telefonos;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof CompaniaProductora)) {
            return false;
        }
        return rfc.equals(((CompaniaProductora) otro).rfc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rfc);
    }
}
