/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.model.Proyecto;
import com.jcaa.udec.collections.domain.core.valueobject.ProyectoId;
import com.jcaa.udec.collections.domain.port.out.ProyectoRepositoryPort;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 *
 * @author ASUS
 */
public class ProyectoMemoryRepository implements ProyectoRepositoryPort {
    private final Map<ProyectoId, Proyecto> almacen = new LinkedHashMap<>();

    @Override
    public void guardar(Proyecto proyecto) {
        almacen.put(proyecto.getId(), proyecto);
    }

    @Override
    public Optional<Proyecto> buscarPorId(ProyectoId id) {
        return Optional.ofNullable(almacen.get(id));
    }

    @Override
    public List<Proyecto> listarTodos() {
        return new ArrayList<>(almacen.values());
    }

    @Override
    public boolean eliminarPorId(ProyectoId id) {
        return almacen.remove(id) != null;
    }

    @Override
    public boolean existePorId(ProyectoId id) {
        return almacen.containsKey(id);
    }    
}
