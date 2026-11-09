/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.domain.core.model.Proyecto;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
import java.util.List;
/**
 *
 * @author ASUS
 */
public class ListarProyectosService {
    private final ProyectoRepositoryPort repositorio;

    public ListarProyectosService(ProyectoRepositoryPort repositorio) {
        this.repositorio = repositorio;
    }

    public List<Proyecto> ejecutar() {
        return repositorio.listarTodos();
    }    
}
