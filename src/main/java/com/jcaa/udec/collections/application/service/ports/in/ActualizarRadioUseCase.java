package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarRadioComando;

public interface ActualizarRadioUseCase {
    void actualizar(ActualizarRadioComando comando);
}
