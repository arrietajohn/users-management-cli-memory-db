package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.model.Cliente;

import java.util.ArrayList;
import java.util.List;

final class ClientesMemoria {
    private static final List<Cliente> CLIENTES = new ArrayList<>();

    private ClientesMemoria() {
    }

    static List<Cliente> obtenerClientes() {
        return CLIENTES;
    }
}
