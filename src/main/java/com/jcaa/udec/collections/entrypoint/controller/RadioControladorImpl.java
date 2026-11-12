package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarRadioComando;
import com.jcaa.udec.collections.application.service.dto.command.CrearRadioComando;
import com.jcaa.udec.collections.application.service.dto.command.EliminarRadioComando;
import com.jcaa.udec.collections.application.service.dto.query.ObtenerRadioConsulta;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarRadioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.AgregarRadioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.EliminarRadioUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerRadioUseCase;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerRadioResponse;
import com.jcaa.udec.collections.entrypoint.controller.mapper.RadioResponseMapper;

public class RadioControladorImpl implements RadioControlador {
    private final AgregarRadioUseCase agregarRadioUseCase;
    private final ObtenerRadioUseCase obtenerRadioUseCase;
    private final ActualizarRadioUseCase actualizarRadioUseCase;
    private final EliminarRadioUseCase eliminarRadioUseCase;

    public RadioControladorImpl(
            AgregarRadioUseCase agregarRadioUseCase,
            ObtenerRadioUseCase obtenerRadioUseCase,
            ActualizarRadioUseCase actualizarRadioUseCase,
            EliminarRadioUseCase eliminarRadioUseCase) {
        this.agregarRadioUseCase = agregarRadioUseCase;
        this.obtenerRadioUseCase = obtenerRadioUseCase;
        this.actualizarRadioUseCase = actualizarRadioUseCase;
        this.eliminarRadioUseCase = eliminarRadioUseCase;
    }

    @Override
    public void registrar(RegistrarRadioPeticion peticion) {
        CrearRadioComando comando = new CrearRadioComando(
                peticion.id(),
                peticion.nombre(),
                peticion.frecuencia(),
                peticion.tipoTransmision());
        agregarRadioUseCase.guardar(comando);
    }

    @Override
    public ObtenerRadioResponse obtenerPorId(String id) {
        ObtenerRadioConsulta consulta = new ObtenerRadioConsulta(id);
        return RadioResponseMapper.mapearAResponse(obtenerRadioUseCase.obtenerPorId(consulta));
    }

    @Override
    public ObtenerRadioResponse obtenerTodos() {
        return RadioResponseMapper.mapearAResponse(obtenerRadioUseCase.obtenerTodos());
    }

    @Override
    public void actualizar(ActualizarRadioPeticion peticion) {
        ActualizarRadioComando comando = new ActualizarRadioComando(
                peticion.id(),
                peticion.nombre(),
                peticion.frecuencia(),
                peticion.tipoTransmision());
        actualizarRadioUseCase.actualizar(comando);
    }

    @Override
    public void eliminar(String id) {
        eliminarRadioUseCase.eliminar(new EliminarRadioComando(id));
    }
}
