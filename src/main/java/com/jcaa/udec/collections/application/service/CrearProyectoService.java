/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.exception.ProyectoYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Proyecto;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
/**
 *
 * @author ASUS
 */
public class CrearProyectoService {
    private final ProyectoRepositoryPort repositorio;

    public CrearProyectoService(ProyectoRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    public Proyecto ejecutar(ProyectoCommand comando) {
        Proyecto proyecto = comando.aProyecto();

        if (repositorio.existePorId(proyecto.getId())) {
            throw new ProyectoYaExisteException(proyecto.getId());
        }

        repositorio.guardar(proyecto);
        return proyecto;
    }    
}
