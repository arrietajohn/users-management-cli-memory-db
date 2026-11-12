package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.exception.EmpresaInvalidaException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmpresaTest {

    @Test
    void debeCrearEmpresaCorrectamenteConDatosValidos() {
        Empresa empresa = Empresa.builder()
                .id("EMP-001")
                .nombre("Holding Internacional S.A.")
                .paisSede("Colombia")
                .facturacionAnual("2500000")
                .build();

        assertNotNull(empresa);
        assertEquals("EMP-001", empresa.getId());
        assertEquals("Holding Internacional S.A.", empresa.getNombre());
        assertEquals("Colombia", empresa.getPaisSede());
        assertEquals("2500000", empresa.getFacturacionAnual());
    }

    @Test
    void debeLanzarExcepcionCuandoIdEsNuloOInvalido() {
        assertThrows(EmpresaInvalidaException.class, () ->
                Empresa.builder()
                        .id("")
                        .nombre("Empresa Valida")
                        .paisSede("Colombia")
                        .facturacionAnual("1000")
                        .build()
        );
    }

    @Test
    void debeLanzarExcepcionCuandoNombreTieneMenosDeTresLetras() {
        assertThrows(EmpresaInvalidaException.class, () ->
                Empresa.builder()
                        .id("EMP-002")
                        .nombre("AB")
                        .paisSede("Colombia")
                        .facturacionAnual("1000")
                        .build()
        );
    }

    @Test
    void debeLanzarExcepcionCuandoFacturacionEsNegativa() {
        assertThrows(EmpresaInvalidaException.class, () ->
                Empresa.builder()
                        .id("EMP-003")
                        .nombre("Empresa Valida")
                        .paisSede("Colombia")
                        .facturacionAnual("-100")
                        .build()
        );
    }
}