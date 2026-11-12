package com.jcaa.udec.collections.entrypoint.controller.dto.response;

import java.util.List;

public record ObtenerRadioResponse(List<RadioResponse> radios) {
    public ObtenerRadioResponse {
        radios = List.copyOf(radios);
    }

    public boolean estaVacia() {
        return radios.isEmpty();
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), radios.stream()
                .map(RadioResponse::toString)
                .toList());
    }
}
