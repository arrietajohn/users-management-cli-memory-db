package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.valueobject.EmpresaId;
import com.jcaa.udec.collections.domain.core.valueobject.FacturacionAnual;
import com.jcaa.udec.collections.domain.core.valueobject.NombreEmpresa;
import com.jcaa.udec.collections.domain.core.valueobject.PaisSede;
import lombok.Builder;

public class Empresa {
    private final EmpresaId id;
    private final NombreEmpresa nombre;
    private final PaisSede paisSede;
    private final FacturacionAnual facturacionAnual;

    @Builder
    public Empresa(String id, String nombre, String paisSede, String facturacionAnual) {
        this.id = new EmpresaId(id);
        this.nombre = new NombreEmpresa(nombre);
        this.paisSede = new PaisSede(paisSede);
        this.facturacionAnual = new FacturacionAnual(facturacionAnual);
    }

    public String getId() {
        return id.valor();
    }

    public String getNombre() {
        return nombre.valor();
    }

    public String getPaisSede() {
        return paisSede.valor();
    }

    public String getFacturacionAnual() {
        return facturacionAnual.valor();
    }
}