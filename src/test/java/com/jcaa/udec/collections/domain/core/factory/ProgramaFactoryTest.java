package com.jcaa.udec.collections.domain.core.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.FixturesDominio;
import com.jcaa.udec.collections.domain.core.exception.DatoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.PersonalMinimoProgramaException;
import com.jcaa.udec.collections.domain.core.model.CompaniaProductora;
import com.jcaa.udec.collections.domain.core.model.Programa;
import com.jcaa.udec.collections.domain.core.valueobject.Cedula;
import com.jcaa.udec.collections.domain.core.valueobject.GeneroPrograma;
import com.jcaa.udec.collections.domain.core.valueobject.Participacion;
import com.jcaa.udec.collections.domain.core.valueobject.ProgramaId;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.Rfc;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProgramaFactoryTest {
    private final CompaniaProductora productora = FixturesDominio.productoraConPersonal();
    private final RadioId radio = new RadioId("1");

    @Test
    void deberiaCrearUnProgramaConElConsorcioDeLaProductora() {
        // Arrange
        // Act
        Programa programa = ProgramaFactory.crear(
                "5", "Deportes al Dia", "deportivo", radio, productora, FixturesDominio.participacionesValidas());

        // Assert
        assertThat(programa.id()).isEqualTo(new ProgramaId("5"));
        assertThat(programa.genero()).isEqualTo(GeneroPrograma.DEPORTIVO);
        assertThat(programa.consorcio().radioId()).isEqualTo(radio);
        assertThat(programa.consorcio().productoraRfc()).isEqualTo(new Rfc(FixturesDominio.RFC));
    }

    @Test
    void deberiaRechazarProgramaSinProductora() {
        // Arrange
        // Act
        // Assert
        assertThatThrownBy(() -> ProgramaFactory.crear(
                        "5", "Deportes al Dia", "deportivo", radio, null, FixturesDominio.participacionesValidas()))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaRechazarPersonalQueNoTrabajaEnLaProductora() {
        // Arrange
        List<Participacion> ajeno = List.of(
                Participacion.conductor(new Cedula("9999999")),
                Participacion.operador(new Cedula(FixturesDominio.CEDULA_OPERADOR)));

        // Act
        // Assert
        assertThatThrownBy(() -> ProgramaFactory.crear("5", "Deportes al Dia", "deportivo", radio, productora, ajeno))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> ProgramaFactory.crear("5", "Deportes al Dia", "deportivo", radio, productora, null))
                .isInstanceOf(DatoInvalidoException.class);
    }

    @Test
    void deberiaExigirElPersonalMinimo() {
        // Arrange
        List<Participacion> soloConductor =
                List.of(Participacion.conductor(new Cedula(FixturesDominio.CEDULA_CONDUCTOR)));

        // Act
        // Assert
        assertThatThrownBy(() -> ProgramaFactory.crear(
                        "5", "Deportes al Dia", "deportivo", radio, productora, soloConductor))
                .isInstanceOf(PersonalMinimoProgramaException.class);
    }

    @Test
    void deberiaRechazarDatosInvalidosDelPrograma() {
        // Arrange
        List<Participacion> personal = FixturesDominio.participacionesValidas();

        // Act
        // Assert
        assertThatThrownBy(() -> ProgramaFactory.crear("5", "ab", "deportivo", radio, productora, personal))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> ProgramaFactory.crear("5", "Deportes al Dia", "terror", radio, productora, personal))
                .isInstanceOf(DatoInvalidoException.class);
        assertThatThrownBy(() -> ProgramaFactory.crear("x", "Deportes al Dia", "deportivo", radio, productora, personal))
                .isInstanceOf(DatoInvalidoException.class);
    }
}
