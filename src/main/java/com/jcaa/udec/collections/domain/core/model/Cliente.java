package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.CodigoCliente;
import com.jcaa.udec.collections.domain.core.valueobject.DireccionCliente;
import com.jcaa.udec.collections.domain.core.valueobject.NombreCliente;
import com.jcaa.udec.collections.domain.core.valueobject.PersonaContacto;
import com.jcaa.udec.collections.domain.core.valueobject.TelefonoCliente;
import com.jcaa.udec.collections.domain.core.valueobject.TipoActividad;
import java.util.Objects;
import lombok.Builder;

public class Cliente {
    private final CodigoCliente codigo;
    private final NombreCliente nombre;
    private final DireccionCliente direccion;
    private final TelefonoCliente telefono;
    private final PersonaContacto personaContacto;
    private final TipoActividad tipoActividad;

    @Builder
    public Cliente(
            String codigo,
            String nombre,
            String direccion,
            String telefono,
            String personaContacto,
            TipoActividad tipoActividad) {
        if (Objects.isNull(tipoActividad)) {
            throw new ClienteInvalidoException();
        }
        this.codigo = new CodigoCliente(codigo);
        this.nombre = new NombreCliente(nombre);
        this.direccion = new DireccionCliente(direccion);
        this.telefono = new TelefonoCliente(telefono);
        this.personaContacto = new PersonaContacto(personaContacto);
        this.tipoActividad = tipoActividad;
    }

    public String getCodigo() {
        return codigo.valor();
    }

    public String getNombre() {
        return nombre.valor();
    }

    public String getDireccion() {
        return direccion.valor();
    }

    public String getTelefono() {
        return telefono.valor();
    }

    public String getPersonaContacto() {
        return personaContacto.valor();
    }

    public TipoActividad getTipoActividad() {
        return tipoActividad;
    }
}
