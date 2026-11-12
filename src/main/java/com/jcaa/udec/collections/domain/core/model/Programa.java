package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.PersonalMinimoProgramaException;
import com.jcaa.udec.collections.domain.core.exception.RadioNoEmiteProgramaException;
import com.jcaa.udec.collections.domain.core.valueobject.Cedula;
import com.jcaa.udec.collections.domain.core.valueobject.Consorcio;
import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.GeneroPrograma;
import com.jcaa.udec.collections.domain.core.valueobject.Nombre;
import com.jcaa.udec.collections.domain.core.valueobject.Participacion;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.RolPersona;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Programa {
    private static final String MENSAJE_DATOS =
            "El programa requiere id, nombre, genero, consorcio y personal.";
    private static final String MENSAJE_PARTICIPACION = "La participación es obligatoria.";
    private static final String MENSAJE_YA_PARTICIPA = "La persona ya participa en el programa.";
    private static final String MENSAJE_NO_PARTICIPA = "La persona no participa en el programa.";
    private static final String MENSAJE_RADIO = "La radio emisora es obligatoria.";
    private static final String MENSAJE_RESUMEN =
            "Un programa de resumen debe tomar notas de al menos otro programa.";
    private static final String MENSAJE_AUTO_RESUMEN = "Un programa no puede resumirse a si mismo.";
    private final ProgramaId id;
    private final Nombre nombre;
    private final GeneroPrograma genero;
    private final Consorcio consorcio;
    private final Set<RadioId> radiosEmisoras = new LinkedHashSet<>();
    private final Map<Cedula, Participacion> participaciones = new LinkedHashMap<>();
    private final Set<ProgramaId> programasFuente = new LinkedHashSet<>();

    public Programa(
            ProgramaId id,
            Nombre nombre,
            GeneroPrograma genero,
            Consorcio consorcio,
            List<Participacion> personal) {
        if (id == null || nombre == null || genero == null || consorcio == null || personal == null) {
            throw new DatoInvalidoException(MENSAJE_DATOS);
        }
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.consorcio = consorcio;
        personal.forEach(this::registrarParticipacion);
        for (RolPersona rol : RolPersona.values()) {
            if (cantidadConRol(rol) == 0) {
                throw new PersonalMinimoProgramaException();
            }
        }
    }

    public void agregarParticipacion(Participacion participacion) {
        registrarParticipacion(participacion);
    }

    public void quitarParticipacion(Cedula cedula) {
        Participacion actual = participaciones.get(cedula);
        if (actual == null) {
            throw new DatoInvalidoException(MENSAJE_NO_PARTICIPA);
        }
        if (cantidadConRol(actual.rol()) == 1) {
            throw new PersonalMinimoProgramaException();
        }
        participaciones.remove(cedula);
    }

    public void agregarRadioEmisora(RadioId radioId) {
        if (radioId == null) {
            throw new DatoInvalidoException(MENSAJE_RADIO);
        }
        radiosEmisoras.add(radioId);
    }

    public void marcarComoResumenDe(Set<ProgramaId> fuentes) {
        if (fuentes == null || fuentes.isEmpty() || fuentes.stream().anyMatch(Objects::isNull)) {
            throw new DatoInvalidoException(MENSAJE_RESUMEN);
        }
        if (fuentes.contains(id)) {
            throw new DatoInvalidoException(MENSAJE_AUTO_RESUMEN);
        }
        programasFuente.clear();
        programasFuente.addAll(fuentes);
    }

    public Emision programarEmision(
            EmisionId emisionId,
            RadioId radioId,
            LocalDate fecha,
            LocalTime horaInicio,
            int duracionMinutos,
            boolean repeticion) {
        if (!radiosEmisoras.contains(radioId)) {
            throw new RadioNoEmiteProgramaException();
        }
        return new Emision(emisionId, id, radioId, fecha, horaInicio, duracionMinutos, repeticion);
    }

    public boolean esEmitidoPor(RadioId radioId) {
        return radiosEmisoras.contains(radioId);
    }

    public boolean esResumen() {
        return !programasFuente.isEmpty();
    }

    public ProgramaId id() {
        return id;
    }

    public Nombre nombre() {
        return nombre;
    }

    public GeneroPrograma genero() {
        return genero;
    }

    public Consorcio consorcio() {
        return consorcio;
    }

    public Set<RadioId> radiosEmisoras() {
        return Collections.unmodifiableSet(radiosEmisoras);
    }

    public List<Participacion> participaciones() {
        return List.copyOf(participaciones.values());
    }

    public Set<ProgramaId> programasFuente() {
        return Collections.unmodifiableSet(programasFuente);
    }

    private void registrarParticipacion(Participacion participacion) {
        if (participacion == null) {
            throw new DatoInvalidoException(MENSAJE_PARTICIPACION);
        }
        if (participaciones.containsKey(participacion.cedula())) {
            throw new DatoInvalidoException(MENSAJE_YA_PARTICIPA);
        }
        participaciones.put(participacion.cedula(), participacion);
    }

    private long cantidadConRol(RolPersona rol) {
        return participaciones.values().stream().filter(p -> p.rol() == rol).count();
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Programa)) {
            return false;
        }
        return id.equals(((Programa) otro).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
