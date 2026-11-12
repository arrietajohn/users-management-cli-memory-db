package com.jcaa.udec.collections.entrypoint.controller.mapper;

import com.jcaa.udec.collections.domain.core.model.Radio;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerRadioResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.RadioResponse;
import java.util.List;

public final class RadioResponseMapper {
    private RadioResponseMapper() {
    }

    public static ObtenerRadioResponse mapearAResponse(Radio radio) {
        return new ObtenerRadioResponse(List.of(mapearAResponseRadio(radio)));
    }

    public static ObtenerRadioResponse mapearAResponse(List<Radio> radios) {
        return new ObtenerRadioResponse(radios.stream()
                .map(RadioResponseMapper::mapearAResponseRadio)
                .toList());
    }

    private static RadioResponse mapearAResponseRadio(Radio radio) {
        return RadioResponse.builder()
                .id(radio.getId())
                .nombre(radio.getNombre())
                .frecuencia(radio.getFrecuencia())
                .tipoTransmision(radio.getTipoTransmision())
                .build();
    }
}
