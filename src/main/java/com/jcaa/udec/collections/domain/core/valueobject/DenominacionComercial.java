/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.jcaa.udec.collections.domain.core.valueobject;

/**
 *
 * @author ASUS
 */
public record DenominacionComercial(String valor) {
private static final int LONGITUD_MAXIMA = 100;

    public DenominacionComercial {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException("La denominación comercial es obligatoria.");
        }
        valor = valor.trim();
        if (valor.length() > LONGITUD_MAXIMA) {
            throw new IllegalArgumentException(
                "La denominación comercial no puede superar " + LONGITUD_MAXIMA + " caracteres.");
        }
    }

    @Override
    public String toString() {
        return valor;
    }
}
