package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerRadioResponse;

public interface RadioControlador {
    void registrar(RegistrarRadioPeticion peticion);

    ObtenerRadioResponse obtenerPorId(String id);

    ObtenerRadioResponse obtenerTodos();

    void actualizar(ActualizarRadioPeticion peticion);

    void eliminar(String id);
}
