package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.Cedula;
import com.jcaa.udec.collections.domain.core.valueobject.Nombre;
import com.jcaa.udec.collections.domain.core.valueobject.RolPersona;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Persona {
    private static final String MENSAJE_DATOS = "La persona requiere cedula, nombre y rol.";
    private static final String MENSAJE_SOLO_CONDUCTOR = "Solo un conductor puede tener preferencia por un operador.";
    private static final String MENSAJE_SOLO_OPERADOR = "La preferencia debe ser una persona con rol de operador.";
    private final Cedula cedula;
    private final Nombre nombre;
    private final RolPersona rol;
    private final Set<Cedula> operadoresPreferidos = new LinkedHashSet<>();

    public Persona(Cedula cedula, Nombre nombre, RolPersona rol) {
        if (cedula == null || nombre == null || rol == null) {
            throw new DatoInvalidoException(MENSAJE_DATOS);
        }
        this.cedula = cedula;
        this.nombre = nombre;
        this.rol = rol;
    }

    public void preferirOperador(Persona operador) {
        if (rol != RolPersona.CONDUCTOR) {
            throw new DatoInvalidoException(MENSAJE_SOLO_CONDUCTOR);
        }
        if (operador == null || operador.rol != RolPersona.OPERADOR) {
            throw new DatoInvalidoException(MENSAJE_SOLO_OPERADOR);
        }
        operadoresPreferidos.add(operador.cedula);
    }

    public Cedula cedula() {
        return cedula;
    }

    public Nombre nombre() {
        return nombre;
    }

    public RolPersona rol() {
        return rol;
    }

    public Set<Cedula> operadoresPreferidos() {
        return Collections.unmodifiableSet(operadoresPreferidos);
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Persona)) {
            return false;
        }
        return cedula.equals(((Persona) otro).cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }
}
