package com.jcaa.udec.collections.entrypoint.controller.dto.response;

import lombok.Builder;

@Builder
public record RadioResponse(String id, String nombre, String frecuencia, String tipoTransmision) {
    private static final String FORMATO_DATOS = """
            ID: %s
            NOMBRE: %s
            FRECUENCIA: %s
            TIPO: %s
            """;

    @Override
    public String toString() {
        return FORMATO_DATOS.formatted(id, nombre, frecuencia, tipoTransmision);
    }
}
