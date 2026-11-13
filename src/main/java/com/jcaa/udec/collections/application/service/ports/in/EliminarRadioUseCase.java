package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.EliminarRadioComando;

public interface EliminarRadioUseCase {
    void eliminar(EliminarRadioComando comando);
}
