package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.event.EmisionProgramada;
import com.jcaa.udec.collections.domain.core.event.EncuestaRegistrada;
import com.jcaa.udec.collections.domain.core.event.EventoDominio;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.ResultadoEncuesta;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Emision {
    private static final int MINUTOS_POR_DIA = 1440;
    private static final String MENSAJE_DATOS = "La emisión requiere id, programa, radio, fecha y hora de inicio.";
    private static final String MENSAJE_DURACION = "La duración debe estar entre 1 y 1440 minutos.";
    private static final String MENSAJE_ENCUESTA = "La encuesta es obligatoria.";
    private final EmisionId id;
    private final ProgramaId programaId;
    private final RadioId radioId;
    private final LocalDate fecha;
    private final LocalTime horaInicio;
    private final int duracionMinutos;
    private final boolean repeticion;
    private final List<ResultadoEncuesta> encuestas = new ArrayList<>();
    private final List<EventoDominio> eventos = new ArrayList<>();

    Emision(
            EmisionId id,
            ProgramaId programaId,
            RadioId radioId,
            LocalDate fecha,
            LocalTime horaInicio,
            int duracionMinutos,
            boolean repeticion) {
        if (id == null || programaId == null || radioId == null || fecha == null || horaInicio == null) {
            throw new DatoInvalidoException(MENSAJE_DATOS);
        }
        if (duracionMinutos < 1 || duracionMinutos > MINUTOS_POR_DIA) {
            throw new DatoInvalidoException(MENSAJE_DURACION);
        }
        this.id = id;
        this.programaId = programaId;
        this.radioId = radioId;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.duracionMinutos = duracionMinutos;
        this.repeticion = repeticion;
        eventos.add(new EmisionProgramada(id, programaId, radioId, fecha, Instant.now()));
    }

    public void registrarEncuesta(ResultadoEncuesta resultado) {
        if (resultado == null) {
            throw new DatoInvalidoException(MENSAJE_ENCUESTA);
        }
        encuestas.add(resultado);
        eventos.add(new EncuestaRegistrada(id, resultado.total(), Instant.now()));
    }

    public int totalEncuestados() {
        return encuestas.stream().mapToInt(ResultadoEncuesta::total).sum();
    }

    public int totalAprobaciones() {
        return encuestas.stream().mapToInt(ResultadoEncuesta::aprobaciones).sum();
    }

    public int totalRechazos() {
        return encuestas.stream().mapToInt(ResultadoEncuesta::rechazos).sum();
    }

    public int totalIndiferencias() {
        return encuestas.stream().mapToInt(ResultadoEncuesta::indiferencias).sum();
    }

    public List<ResultadoEncuesta> encuestas() {
        return List.copyOf(encuestas);
    }

    public List<EventoDominio> extraerEventos() {
        List<EventoDominio> copia = List.copyOf(eventos);
        eventos.clear();
        return copia;
    }

    public EmisionId id() {
        return id;
    }

    public ProgramaId programaId() {
        return programaId;
    }

    public RadioId radioId() {
        return radioId;
    }

    public LocalDate fecha() {
        return fecha;
    }

    public LocalTime horaInicio() {
        return horaInicio;
    }

    public int duracionMinutos() {
        return duracionMinutos;
    }

    public boolean esRepeticion() {
        return repeticion;
    }

    @Override
    public boolean equals(Object otro) {
        if (this == otro) {
            return true;
        }
        if (!(otro instanceof Emision)) {
            return false;
        }
        return id.equals(((Emision) otro).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
