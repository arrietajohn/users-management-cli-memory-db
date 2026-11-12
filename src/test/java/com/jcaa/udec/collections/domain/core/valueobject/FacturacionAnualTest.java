package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EmpresaInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FacturacionAnualTest {

    @Test
    void debeCrearFacturacionCuandoElMontoEsValido() {
        assertDoesNotThrow(() -> new FacturacionAnual("1500000.50"));
    }

    @Test
    void debeLanzarExcepcionCuandoMontoEsNegativo() {
        assertThrows(EmpresaInvalidaException.class, () -> new FacturacionAnual("-500.00"));
    }

    @Test
    void debeLanzarExcepcionCuandoNoEsNumeroValido() {
        assertThrows(EmpresaInvalidaException.class, () -> new FacturacionAnual("cien mil"));
    }

    @Test
    void debeLanzarExcepcionCuandoEsVacio() {
        assertThrows(EmpresaInvalidaException.class, () -> new FacturacionAnual(""));
    }
}