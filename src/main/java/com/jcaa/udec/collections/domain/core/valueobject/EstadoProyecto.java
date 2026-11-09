/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.jcaa.udec.collections.domain.core.valueobject;

/**
 *
 * @author ASUS
 */
public enum EstadoProyecto {
    PLANIFICADO,
    EN_CURSO,
    FINALIZADO,
    CANCELADO;

    public static EstadoProyecto desde(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("El estado del proyecto es obligatorio.");
        }
        try {
            return valueOf(texto.trim().toUpperCase().replace(' ', '_'));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Estado no válido: " + texto + ". Use PLANIFICADO, EN_CURSO, FINALIZADO o CANCELADO.");
        }
    }    
}
