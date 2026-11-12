package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.command.CrearClienteComando;
import com.jcaa.udec.collections.application.service.mapper.ClienteMapper;
import com.jcaa.udec.collections.application.service.ports.in.AgregarClienteUseCase;
import com.jcaa.udec.collections.domain.core.model.Cliente;
import com.jcaa.udec.collections.domain.port.out.GuardarClientePort;

public class AgregarClienteService implements AgregarClienteUseCase {
  private final GuardarClientePort guardarClientePort;

  public AgregarClienteService(GuardarClientePort guardarClientePort) {
    this.guardarClientePort = guardarClientePort;
  }

  @Override
  public void guardar(CrearClienteComando comando) {
    Cliente cliente = ClienteMapper.mapearACliente(comando);
    guardarClientePort.guardar(cliente);
  }
}
