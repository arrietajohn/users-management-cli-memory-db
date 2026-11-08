package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.event.EncuestaRegistrada;
import com.jcaa.udec.collections.domain.core.event.EventoDominio;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.EncuestaId;
import com.jcaa.udec.collections.domain.core.valueobject.ResultadoEncuesta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Raíz de agregado Encuesta.
 * Guarda los resultados de audiencia de una emisión. Referencia a la emisión
 * solo por su identificador (EmisionId), nunca por el objeto completo.
 * Al registrarse genera el evento de dominio EncuestaRegistrada.
 */
public class Encuesta {

    private final EncuestaId id;
    private final EmisionId emisionId;
    private final ResultadoEncuesta resultado;
    private final List<EventoDominio> eventos = new ArrayList<>();

    private Encuesta(EncuestaId id, EmisionId emisionId, ResultadoEncuesta resultado) {
        this.id = id;
        this.emisionId = emisionId;
        this.resultado = resultado;
    }

    public static Encuesta registrar(EmisionId emisionId, ResultadoEncuesta resultado) {
        if (emisionId == null) {
            throw new DatoInvalidoException("La encuesta debe estar asociada a una emisión.");
        }
        if (resultado == null) {
            throw new DatoInvalidoException("La encuesta debe tener resultados.");
        }
        Encuesta encuesta = new Encuesta(EncuestaId.generar(), emisionId, resultado);
        encuesta.eventos.add(new EncuestaRegistrada(encuesta.id, emisionId, resultado.encuestados()));
        return encuesta;
    }

    public EncuestaId id() {
        return id;
    }

    public EmisionId emisionId() {
        return emisionId;
    }

    public ResultadoEncuesta resultado() {
        return resultado;
    }

    public double porcentajeAprobacion() {
        return resultado.porcentajeAprobacion();
    }

    public List<EventoDominio> eventosPendientes() {
        return List.copyOf(eventos);
    }

    public void limpiarEventos() {
        eventos.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Encuesta)) return false;
        Encuesta encuesta = (Encuesta) o;
        return id.equals(encuesta.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Encuesta{id=" + id + ", emisionId=" + emisionId + ", resultado=" + resultado + "}";
    }
}
