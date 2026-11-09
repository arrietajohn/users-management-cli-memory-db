/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.jcaa.udec.collections.domain.core.valueobject;

/**
 *
 * @author ASUS
 */
public record NombreClave(String valor) {
private static final int LONGITUD_MAXIMA = 50;

    public NombreClave {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("El nombre en clave es obligatorio.");
        }
        valor = valor.trim();
        if (valor.length() > LONGITUD_MAXIMA) {
            throw new IllegalArgumentException(
                "El nombre en clave no puede superar " + LONGITUD_MAXIMA + " caracteres.");
        }
    }

    @Override
    public String toString() {
        return valor;
    }
}
