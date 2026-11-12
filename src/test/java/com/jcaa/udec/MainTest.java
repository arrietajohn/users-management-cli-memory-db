package com.jcaa.udec;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

class MainTest {
    private static final String OPCION_SALIR = "9";

    @Test
    void deberiaInicializarAplicacionYFinalizarAlSeleccionarSalir() {
        // Arrange
        InputStream entradaOriginal = System.in;
        PrintStream salidaOriginal = System.out;
        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        System.setIn(new ByteArrayInputStream(
                (OPCION_SALIR + System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        System.setOut(new PrintStream(salida, true, StandardCharsets.UTF_8));

        try {
            // Act
            Main.main(new String[0]);

            // Assert
            assertThat(salida.toString(StandardCharsets.UTF_8))
                    .contains("Esperamos tu regreso. Bye, Bye");
        } finally {
            System.setIn(entradaOriginal);
            System.setOut(salidaOriginal);
        }
    }
}
