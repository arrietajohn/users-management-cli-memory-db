package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Cliente;

public interface GuardarClientePort {
    void guardar(Cliente cliente);
}
