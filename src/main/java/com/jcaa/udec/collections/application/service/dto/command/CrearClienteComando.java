package com.jcaa.udec.collections.application.service.dto.command;

public record CrearClienteComando(
    String codigo,
    String nombre,
    String direccion,
    String telefono,
    String personaContacto,
    String tipoActividad) {}
