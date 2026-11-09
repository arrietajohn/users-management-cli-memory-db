package com.jcaa.udec.collections.domain.core.model;

public class Cliente {

    private final String id;
    private String nombre;
    private String correo;
    private String telefono;

    public Cliente(String id, String nombre, String correo, String telefono) {
        validar(id, nombre, correo, telefono);
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    private void validar(String id, String nombre, String correo, String telefono) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("El id del cliente es obligatorio");
        }

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }

        if (correo == null || !correo.contains("@")) {
            throw new IllegalArgumentException("El correo del cliente no es válido");
        }

        if (telefono == null || telefono.isBlank()) {
            throw new IllegalArgumentException("El teléfono del cliente es obligatorio");
        }
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void actualizar(String nombre, String correo, String telefono) {
        validar(this.id, nombre, correo, telefono);
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }
}
