package com.jcaa.udec.collections.domain.core.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.FixturesDominio;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.PersonalMinimoProgramaException;
import com.jcaa.udec.collections.domain.core.exception.RadioNoEmiteProgramaException;
import com.jcaa.udec.collections.domain.core.valueobject.Cedula;
import com.jcaa.udec.collections.domain.core.valueobject.Consorcio;
import com.jcaa.udec.collections.domain.core.valueobject.EmisionId;
import com.jcaa.udec.collections.domain.core.valueobject.GeneroPrograma;
import com.jcaa.udec.collections.domain.core.valueobject.Nombre;
import com.jcaa.udec.collections.domain.core.valueobject.Participacion;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.Rfc;
import com.jcaa.udec.collections.domain.core.valueobject.TipoProduccion;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;

class ProgramaTest {
    private static final ProgramaId ID = new ProgramaId("1");
    private static final Nombre NOMBRE = new Nombre("Buenos Dias Cartagena");
    private static final Consorcio CONSORCIO = new Consorcio(new RadioId("1"), new Rfc("ABC010101AB1"));

    private static Programa crearPrograma(List<Participacion> personal) {
        return new Programa(ID, NOMBRE, GeneroPrograma.PERIODISTICO, CONSORCIO, personal);
    }

    @Test
    void deberiaCrearProgramaConSusDatosYPersonalMinimo() {
        // Arrange
        // Act
        Programa programa = crearPrograma(FixturesDominio.participacionesValidas());

        // Assert
        assertThat(programa.id()).isEqualTo(ID);
        assertThat(programa.nombre()).isEqualTo(NOMBRE);
        assertThat(programa.genero()).isEqualTo(GeneroPrograma.PERIODISTICO);
        assertThat(programa.consorcio()).isEqualTo(CONSORCIO);
        assertThat(programa.participaciones()).hasSize(3);
        assertThat(programa.esResumen()).isFalse();
    }

    @Test
    void deberiaRechazarProgramaConDatosIncompletos() {
        // Arrange
        List<Participacion> personal = FixturesDominio.participacionesValidas();

        // Act
        // Assert
        assertThatThrownBy(() -> new Programa(null, NOMBRE, GeneroPrograma.MUSICAL, CONSORCIO, personal))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new Programa(ID, null, GeneroPrograma.MUSICAL, CONSORCIO, personal))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new Programa(ID, NOMBRE, null, CONSORCIO, personal))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new Programa(ID, NOMBRE, GeneroPrograma.MUSICAL, null, personal))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new Programa(ID, NOMBRE, GeneroPrograma.MUSICAL, CONSORCIO, null))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaExigirAlMenosUnConductorUnProductorYUnOperador() {
        // Arrange
        List<Participacion> sinOperador = List.of(
                Participacion.conductor(new Cedula(FixturesDominio.CEDULA_CONDUCTOR)),
                Participacion.productor(new Cedula(FixturesDominio.CEDULA_PRODUCTOR), TipoProduccion.COMERCIAL));

        // Act
        // Assert
        assertThatThrownBy(() -> crearPrograma(sinOperador)).isInstanceOf(PersonalMinimoProgramaException.class);
        assertThatThrownBy(() -> crearPrograma(List.of())).isInstanceOf(PersonalMinimoProgramaException.class);
    }

    @Test
    void deberiaRechazarParticipacionesRepetidasONulas() {
        // Arrange
        List<Participacion> repetidas = new ArrayList<>(FixturesDominio.participacionesValidas());
        repetidas.add(Participacion.conductor(new Cedula(FixturesDominio.CEDULA_CONDUCTOR)));
        List<Participacion> conNula = Arrays.asList((Participacion) null);

        // Act
        // Assert
        assertThatThrownBy(() -> crearPrograma(repetidas)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> crearPrograma(conNula)).isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaAgregarYQuitarUnSegundoConductor() {
        // Arrange
        Programa programa = crearPrograma(FixturesDominio.participacionesValidas());

        // Act
        programa.agregarParticipacion(Participacion.conductor(new Cedula("5555555")));
        int conSegundoConductor = programa.participaciones().size();
        programa.quitarParticipacion(new Cedula(FixturesDominio.CEDULA_CONDUCTOR));

        // Assert
        assertThat(conSegundoConductor).isEqualTo(4);
        assertThat(programa.participaciones()).hasSize(3);
    }

    @Test
    void deberiaImpedirQuitarAlUnicoDeUnRol() {
        // Arrange
        Programa programa = crearPrograma(FixturesDominio.participacionesValidas());

        // Act
        // Assert
        assertThatThrownBy(() -> programa.quitarParticipacion(new Cedula(FixturesDominio.CEDULA_OPERADOR)))
                .isInstanceOf(PersonalMinimoProgramaException.class);
        assertThat(programa.participaciones()).hasSize(3);
    }

    @Test
    void deberiaRechazarQuitarAQuienNoParticipa() {
        // Arrange
        Programa programa = crearPrograma(FixturesDominio.participacionesValidas());

        // Act
        // Assert
        assertThatThrownBy(() -> programa.quitarParticipacion(new Cedula("9999999")))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.quitarParticipacion(null)).isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaRegistrarRadiosEmisoras() {
        // Arrange
        Programa programa = crearPrograma(FixturesDominio.participacionesValidas());

        // Act
        programa.agregarRadioEmisora(new RadioId("2"));

        // Assert
        assertThat(programa.esEmitidoPor(new RadioId("2"))).isTrue();
        assertThat(programa.esEmitidoPor(new RadioId("3"))).isFalse();
        assertThat(programa.radiosEmisoras()).containsExactly(new RadioId("2"));
        assertThatThrownBy(() -> programa.agregarRadioEmisora(null)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.radiosEmisoras().add(new RadioId("9")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void deberiaMarcarseComoResumenDeOtrosProgramas() {
        // Arrange
        Programa programa = crearPrograma(FixturesDominio.participacionesValidas());

        // Act
        programa.marcarComoResumenDe(Set.of(new ProgramaId("7"), new ProgramaId("8")));

        // Assert
        assertThat(programa.esResumen()).isTrue();
        assertThat(programa.programasFuente()).contains(new ProgramaId("7"), new ProgramaId("8"));
    }

    @Test
    void deberiaRechazarResumenInvalido() {
        // Arrange
        Programa programa = crearPrograma(FixturesDominio.participacionesValidas());
        Set<ProgramaId> conNulo = new HashSet<>(Arrays.asList((ProgramaId) null));

        // Act
        // Assert
        assertThatThrownBy(() -> programa.marcarComoResumenDe(null)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.marcarComoResumenDe(Set.of())).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.marcarComoResumenDe(conNulo)).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.marcarComoResumenDe(Set.of(ID))).isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> programa.programasFuente().add(new ProgramaId("9")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void deberiaProgramarUnaEmisionEnUnaRadioQueLoEmite() {
        // Arrange
        Programa programa = crearPrograma(FixturesDominio.participacionesValidas());
        programa.agregarRadioEmisora(new RadioId("1"));

        // Act
        Emision emision = programa.programarEmision(
                new EmisionId("10"), new RadioId("1"), LocalDate.of(2026, 11, 2), LocalTime.of(7, 30), 45, true);

        // Assert
        assertThat(emision.id()).isEqualTo(new EmisionId("10"));
        assertThat(emision.programaId()).isEqualTo(ID);
        assertThat(emision.radioId()).isEqualTo(new RadioId("1"));
        assertThat(emision.fecha()).isEqualTo(LocalDate.of(2026, 11, 2));
        assertThat(emision.horaInicio()).isEqualTo(LocalTime.of(7, 30));
        assertThat(emision.duracionMinutos()).isEqualTo(45);
        assertThat(emision.esRepeticion()).isTrue();
    }

    @Test
    void deberiaRechazarEmisionEnUnaRadioQueNoLoEmite() {
        // Arrange
        Programa programa = crearPrograma(FixturesDominio.participacionesValidas());

        // Act
        // Assert
        assertThatThrownBy(() -> programa.programarEmision(
                        new EmisionId("10"), new RadioId("1"), LocalDate.of(2026, 11, 2), LocalTime.of(7, 30), 45, false))
                .isInstanceOf(RadioNoEmiteProgramaException.class);
    }

    @Test
    void deberiaIdentificarsePorId() {
        // Arrange
        Programa uno = crearPrograma(FixturesDominio.participacionesValidas());
        Programa mismoId = crearPrograma(FixturesDominio.participacionesValidas());
        Programa otro = new Programa(
                new ProgramaId("2"), NOMBRE, GeneroPrograma.MUSICAL, CONSORCIO, FixturesDominio.participacionesValidas());

        // Act
        // Assert
        assertThat(uno.equals(mismoId)).isTrue();
        assertThat(uno.hashCode()).isEqualTo(mismoId.hashCode());
        assertThat(uno.equals(otro)).isFalse();
        assertThat(uno.equals(uno)).isTrue();
        assertThat(uno.equals("texto")).isFalse();
    }
}
