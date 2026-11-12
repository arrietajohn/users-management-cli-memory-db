package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.CrearClienteComando;

public interface AgregarClienteUseCase {
  void guardar(CrearClienteComando comando);
}
