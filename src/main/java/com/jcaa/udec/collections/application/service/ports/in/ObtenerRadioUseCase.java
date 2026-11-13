package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.query.ObtenerRadioConsulta;
import com.jcaa.udec.collections.domain.core.model.Radio;

import java.util.List;

public interface ObtenerRadioUseCase {
    List<Radio> obtenerTodos();

    Radio obtenerPorId(ObtenerRadioConsulta consulta);
}
