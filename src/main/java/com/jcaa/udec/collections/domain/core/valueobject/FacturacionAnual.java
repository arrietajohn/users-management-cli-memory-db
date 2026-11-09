package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EmpresaInvalidaException;
import java.util.Objects;

public record FacturacionAnual(String valor) {
    public FacturacionAnual {
        if (Objects.isNull(valor) || valor.isBlank()) {
            throw new EmpresaInvalidaException("La facturación anual no puede ser vacía.");
        }
        try {
            double monto = Double.parseDouble(valor.trim());
            if (monto < 0) {
                throw new EmpresaInvalidaException("La facturación anual no puede ser negativa.");
            }
        } catch (NumberFormatException e) {
            throw new EmpresaInvalidaException("La facturación anual debe ser un número válido.");
        }
    }
}