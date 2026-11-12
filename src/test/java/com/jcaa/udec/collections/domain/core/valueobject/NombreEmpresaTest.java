package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EmpresaInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NombreEmpresaTest {

    @Test
    void debeCrearNombreEmpresaCuandoEsValido() {
        assertDoesNotThrow(() -> new NombreEmpresa("Holding Inversiones"));
    }

    @Test
    void debeLanzarExcepcionCuandoNombreEsNulo() {
        assertThrows(EmpresaInvalidaException.class, () -> new NombreEmpresa(null));
    }

    @Test
    void debeLanzarExcepcionCuandoNombreEsVacio() {
        assertThrows(EmpresaInvalidaException.class, () -> new NombreEmpresa("   "));
    }

    @Test
    void debeLanzarExcepcionCuandoTieneMenosDeTresCaracteres() {
        assertThrows(EmpresaInvalidaException.class, () -> new NombreEmpresa("AB"));
    }
}