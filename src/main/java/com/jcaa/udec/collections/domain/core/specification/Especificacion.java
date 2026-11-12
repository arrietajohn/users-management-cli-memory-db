package com.jcaa.udec.collections.domain.core.specification;

public interface Especificacion<T> {
    boolean esSatisfechaPor(T candidato);

    default Especificacion<T> y(Especificacion<T> otra) {
        return candidato -> esSatisfechaPor(candidato) && otra.esSatisfechaPor(candidato);
    }

    default Especificacion<T> o(Especificacion<T> otra) {
        return candidato -> esSatisfechaPor(candidato) || otra.esSatisfechaPor(candidato);
    }

    default Especificacion<T> no() {
        return candidato -> !esSatisfechaPor(candidato);
    }
}
