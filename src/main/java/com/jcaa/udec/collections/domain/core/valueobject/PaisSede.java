package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EmpresaInvalidaException;
import java.util.Objects;

public record PaisSede(String valor) {
    public PaisSede {
        if (Objects.isNull(valor) || valor.isBlank()) {
            throw new EmpresaInvalidaException("El país de la sede principal es obligatorio.");
        }
    }
}