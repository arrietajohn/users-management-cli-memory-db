package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.CrearRadioComando;

public interface AgregarRadioUseCase {
    void guardar(CrearRadioComando comando);
}
