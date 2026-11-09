/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.jcaa.udec.collections.domain.core.valueobject;

/**
 *
 * @author ASUS
 */
public record ProyectoId(String valor) {
    public ProyectoId {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El código del proyecto es obligatorio.");
        }
        valor = valor.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return valor;
    }
}
