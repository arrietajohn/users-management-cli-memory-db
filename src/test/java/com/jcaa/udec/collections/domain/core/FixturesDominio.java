package com.jcaa.udec.collections.domain.core;

import com.jcaa.udec.collections.domain.core.factory.ProgramaFactory;
import com.jcaa.udec.collections.domain.core.model.CompaniaProductora;
import com.jcaa.udec.collections.domain.core.model.Emision;
import com.jcaa.udec.collections.domain.core.model.Persona;
import com.jcaa.udec.collections.domain.core.model.Programa;
import com.jcaa.udec.collections.domain.core.valueobject.Cedula;
import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.Nombre;
import com.jcaa.udec.collections.domain.core.valueobject.Participacion;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.Rfc;
import com.jcaa.udec.collections.domain.core.valueobject.RolPersona;
import com.jcaa.udec.collections.domain.core.valueobject.TipoProduccion;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public final class FixturesDominio {
    public static final String RFC = "ABC010101AB1";
    public static final String CEDULA_CONDUCTOR = "1000001";
    public static final String CEDULA_OPERADOR = "1000002";
    public static final String CEDULA_PRODUCTOR = "1000003";
    public static final String ID_RADIO = "1";

    private FixturesDominio() {
    }

    public static CompaniaProductora productoraSinPersonal(String rfc, String nombre) {
        return new CompaniaProductora(new Rfc(rfc), new Nombre(nombre), List.of("3001234567"));
    }

    public static Persona persona(String cedula, String nombre, RolPersona rol) {
        return new Persona(new Cedula(cedula), new Nombre(nombre), rol);
    }

    public static CompaniaProductora productoraConPersonal() {
        CompaniaProductora productora = productoraSinPersonal(RFC, "Caribe Producciones");
        productora.contratar(persona(CEDULA_CONDUCTOR, "Carlos Conductor", RolPersona.CONDUCTOR));
        productora.contratar(persona(CEDULA_OPERADOR, "Olga Operadora", RolPersona.OPERADOR));
        productora.contratar(persona(CEDULA_PRODUCTOR, "Pedro Productor", RolPersona.PRODUCTOR));
        return productora;
    }

    public static List<Participacion> participacionesValidas() {
        return List.of(
                Participacion.conductor(new Cedula(CEDULA_CONDUCTOR)),
                Participacion.operador(new Cedula(CEDULA_OPERADOR)),
                Participacion.productor(new Cedula(CEDULA_PRODUCTOR), TipoProduccion.PERIODISTICA));
    }

    public static Programa programa(String id) {
        Programa programa = ProgramaFactory.crear(
                id,
                "Programa " + id,
                "periodístico",
                new RadioId(ID_RADIO),
                productoraConPersonal(),
                participacionesValidas());
        programa.agregarRadioEmisora(new RadioId(ID_RADIO));
        return programa;
    }

    public static Emision emision(Programa programa, String idEmision, LocalDate fecha, boolean repeticion) {
        return programa.programarEmision(
                new EmisionId(idEmision), new RadioId(ID_RADIO), fecha, LocalTime.of(7, 0), 60, repeticion);
    }
}
