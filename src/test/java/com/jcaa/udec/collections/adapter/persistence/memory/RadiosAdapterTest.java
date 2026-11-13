package com.jcaa.udec.collections.adapter.persistence.memory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.domain.core.exception.RadioNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.RadioYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Radio;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RadiosAdapterTest {
    private static final AtomicInteger SECUENCIA_ID = new AtomicInteger(1000);
    private final GuardarRadioAdapter guardarRadioAdapter = new GuardarRadioAdapter();
    private final ObtenerRadiosAdapter obtenerRadiosAdapter = new ObtenerRadiosAdapter();
    private final ActualizarRadioAdapter actualizarRadioAdapter = new ActualizarRadioAdapter();
    private final EliminarRadioAdapter eliminarRadioAdapter = new EliminarRadioAdapter();

    @BeforeEach
    void limpiarRadios() {
        RadiosMemoria.obtenerRadios().clear();
    }

    @Test
    void deberiaGuardarYObtenerRadio() {
        // Arrange
        Radio radio = crearRadio();

        // Act
        guardarRadioAdapter.guardar(radio);
        Radio radioEncontrada = obtenerRadiosAdapter.buscarPorId(radio.getId());

        // Assert
        assertThat(radioEncontrada).isSameAs(radio);
    }

    @Test
    void deberiaRechazarRadioDuplicada() {
        // Arrange
        Radio radio = crearRadio();
        guardarRadioAdapter.guardar(radio);

        // Act
        // Assert
        assertThatThrownBy(() -> guardarRadioAdapter.guardar(radio))
                .isInstanceOf(RadioYaExisteException.class)
                .hasMessage("La radio ya existe.");
    }

    @Test
    void deberiaReportarRadioInexistenteAlBuscar() {
        // Arrange
        String idInexistente = String.valueOf(SECUENCIA_ID.incrementAndGet());

        // Act
        // Assert
        assertThatThrownBy(() -> obtenerRadiosAdapter.buscarPorId(idInexistente))
                .isInstanceOf(RadioNoExisteException.class)
                .hasMessage("La radio no existe.");
    }

    @Test
    void deberiaRetornarUnaListaInmutable() {
        // Arrange
        List<Radio> radios = obtenerRadiosAdapter.obtenerTodos();

        // Act
        // Assert
        assertThatThrownBy(() -> radios.add(crearRadio()))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    @Test
    void deberiaObtenerTodasLasRadiosGuardadas() {
        // Arrange
        Radio primeraRadio = crearRadio();
        Radio segundaRadio = crearRadio();
        guardarRadioAdapter.guardar(primeraRadio);
        guardarRadioAdapter.guardar(segundaRadio);

        // Act
        List<Radio> radios = obtenerRadiosAdapter.obtenerTodos();

        // Assert
        assertThat(radios).containsExactly(primeraRadio, segundaRadio);
    }

    @Test
    void deberiaActualizarRadioExistente() {
        // Arrange
        Radio radio = crearRadio();
        guardarRadioAdapter.guardar(radio);
        Radio radioActualizada = new Radio(radio.getId(), "Radio Nueva", "101.5", "AM");

        // Act
        actualizarRadioAdapter.actualizar(radioActualizada);
        Radio radioEncontrada = obtenerRadiosAdapter.buscarPorId(radio.getId());

        // Assert
        assertThat(radioEncontrada).isSameAs(radioActualizada);
    }

    @Test
    void deberiaReportarRadioInexistenteAlActualizar() {
        // Arrange
        Radio radioInexistente = crearRadio();

        // Act
        // Assert
        assertThatThrownBy(() -> actualizarRadioAdapter.actualizar(radioInexistente))
                .isInstanceOf(RadioNoExisteException.class)
                .hasMessage("La radio no existe.");
    }

    @Test
    void deberiaEliminarRadioExistente() {
        // Arrange
        Radio radio = crearRadio();
        guardarRadioAdapter.guardar(radio);

        // Act
        eliminarRadioAdapter.eliminar(radio.getId());

        // Assert
        assertThatThrownBy(() -> obtenerRadiosAdapter.buscarPorId(radio.getId()))
                .isInstanceOf(RadioNoExisteException.class);
    }

    @Test
    void deberiaReportarRadioInexistenteAlEliminar() {
        // Arrange
        String idInexistente = String.valueOf(SECUENCIA_ID.incrementAndGet());

        // Act
        // Assert
        assertThatThrownBy(() -> eliminarRadioAdapter.eliminar(idInexistente))
                .isInstanceOf(RadioNoExisteException.class)
                .hasMessage("La radio no existe.");
    }

    private static Radio crearRadio() {
        String id = String.valueOf(SECUENCIA_ID.incrementAndGet());
        return new Radio(id, "La Radio Caribe", "98.5", "FM");
    }
}
