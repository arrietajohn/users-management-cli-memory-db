package com.jcaa.udec.collections.application.service.mapper;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarRadioComando;
import com.jcaa.udec.collections.application.service.dto.command.CrearRadioComando;
import com.jcaa.udec.collections.domain.core.model.Radio;

public final class RadioMapper {
    private RadioMapper() {
    }

    public static Radio mapearARadio(CrearRadioComando comando) {
        return Radio.builder()
                .id(comando.id())
                .nombre(comando.nombre())
                .frecuencia(comando.frecuencia())
                .tipoTransmision(comando.tipoTransmision())
                .build();
    }

    public static Radio mapearARadio(ActualizarRadioComando comando) {
        return Radio.builder()
                .id(comando.id())
                .nombre(comando.nombre())
                .frecuencia(comando.frecuencia())
                .tipoTransmision(comando.tipoTransmision())
                .build();
    }
}
