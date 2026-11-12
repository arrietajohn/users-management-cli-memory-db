package com.jcaa.udec.collections.domain.core.specification;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.FixturesDominio;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.model.CompaniaProductora;
import com.jcaa.udec.collections.domain.core.model.Emision;
import com.jcaa.udec.collections.domain.core.model.Programa;
import com.jcaa.udec.collections.domain.core.valueobject.Cedula;
import com.jcaa.udec.collections.domain.core.valueobject.Participacion;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.Test;

class EspecificacionesTest {
    private static final LocalDate HOY = LocalDate.of(2026, 11, 10);
    private final CompaniaProductora productora = FixturesDominio.productoraConPersonal();
    private final Programa programa = FixturesDominio.programa("1");

    @Test
    void deberiaCumplirseCuandoTodoElPersonalTrabajaEnLaProductoraConSuRol() {
        // Arrange
        PersonalPerteneceAProductoraEspecificacion especificacion =
                new PersonalPerteneceAProductoraEspecificacion(productora);

        // Act
        // Assert
        assertThat(especificacion.esSatisfechaPor(FixturesDominio.participacionesValidas())).isTrue();
    }

    @Test
    void noDeberiaCumplirseSiAlguienNoTrabajaEnLaProductoraOTieneOtroRol() {
        // Arrange
        PersonalPerteneceAProductoraEspecificacion especificacion =
                new PersonalPerteneceAProductoraEspecificacion(productora);
        List<Participacion> ajeno = List.of(Participacion.conductor(new Cedula("9999999")));
        List<Participacion> rolDistinto =
                List.of(Participacion.operador(new Cedula(FixturesDominio.CEDULA_CONDUCTOR)));

        // Act
        // Assert
        assertThat(especificacion.esSatisfechaPor(ajeno)).isFalse();
        assertThat(especificacion.esSatisfechaPor(rolDistinto)).isFalse();
        assertThat(especificacion.esSatisfechaPor((Collection<Participacion>) null)).isFalse();
        assertThat(especificacion.esSatisfechaPor(Arrays.asList((Participacion) null))).isFalse();
    }

    @Test
    void deberiaRechazarUnaProductoraNula() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new PersonalPerteneceAProductoraEspecificacion(null))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaIdentificarLasEmisionesDentroDelRangoIncluyendoLosExtremos() {
        // Arrange
        EmisionEnRangoDeFechasEspecificacion ultimaSemana =
                new EmisionEnRangoDeFechasEspecificacion(HOY.minusDays(7), HOY);
        Emision dentro = FixturesDominio.emision(programa, "1", HOY.minusDays(3), false);
        Emision extremoInicial = FixturesDominio.emision(programa, "2", HOY.minusDays(7), false);
        Emision extremoFinal = FixturesDominio.emision(programa, "3", HOY, false);
        Emision antes = FixturesDominio.emision(programa, "4", HOY.minusDays(8), false);
        Emision despues = FixturesDominio.emision(programa, "5", HOY.plusDays(1), false);

        // Act
        // Assert
        assertThat(ultimaSemana.esSatisfechaPor(dentro)).isTrue();
        assertThat(ultimaSemana.esSatisfechaPor(extremoInicial)).isTrue();
        assertThat(ultimaSemana.esSatisfechaPor(extremoFinal)).isTrue();
        assertThat(ultimaSemana.esSatisfechaPor(antes)).isFalse();
        assertThat(ultimaSemana.esSatisfechaPor(despues)).isFalse();
    }

    @Test
    void deberiaRechazarRangosInvalidos() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> new EmisionEnRangoDeFechasEspecificacion(HOY, HOY.minusDays(1)))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new EmisionEnRangoDeFechasEspecificacion(null, HOY))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> new EmisionEnRangoDeFechasEspecificacion(HOY, null))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaCombinarEspecificaciones() {
        // Arrange
        Especificacion<Emision> repetida = Emision::esRepeticion;
        Especificacion<Emision> ultimaSemana = new EmisionEnRangoDeFechasEspecificacion(HOY.minusDays(7), HOY);
        Emision repetidaReciente = FixturesDominio.emision(programa, "1", HOY, true);
        Emision repetidaAntigua = FixturesDominio.emision(programa, "2", HOY.minusDays(30), true);
        Emision originalReciente = FixturesDominio.emision(programa, "3", HOY, false);

        // Act
        Especificacion<Emision> repetidasDeLaSemana = repetida.y(ultimaSemana);
        Especificacion<Emision> repetidasOSemana = repetida.o(ultimaSemana);
        Especificacion<Emision> noRepetidas = repetida.no();

        // Assert
        assertThat(repetidasDeLaSemana.esSatisfechaPor(repetidaReciente)).isTrue();
        assertThat(repetidasDeLaSemana.esSatisfechaPor(repetidaAntigua)).isFalse();
        assertThat(repetidasDeLaSemana.esSatisfechaPor(originalReciente)).isFalse();
        assertThat(repetidasOSemana.esSatisfechaPor(repetidaAntigua)).isTrue();
        assertThat(repetidasOSemana.esSatisfechaPor(originalReciente)).isTrue();
        assertThat(noRepetidas.esSatisfechaPor(originalReciente)).isTrue();
        assertThat(noRepetidas.esSatisfechaPor(repetidaReciente)).isFalse();
    }
}
