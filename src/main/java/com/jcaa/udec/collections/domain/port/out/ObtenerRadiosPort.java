package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Radio;

import java.util.List;

public interface ObtenerRadiosPort {
    List<Radio> obtenerTodos();

    Radio buscarPorId(String id);
}
