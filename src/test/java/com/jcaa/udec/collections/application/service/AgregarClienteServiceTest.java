package com.jcaa.udec.collections.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.jcaa.udec.collections.application.service.dto.command.CrearClienteComando;
import com.jcaa.udec.collections.domain.core.exception.ClienteInvalidoException;
import com.jcaa.udec.collections.domain.core.model.Cliente;
import com.jcaa.udec.collections.domain.core.valueobject.TipoActividad;
import com.jcaa.udec.collections.domain.port.out.GuardarClientePort;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AgregarClienteServiceTest {
  private static final String CODIGO = "CLI-001";
  private static final String NOMBRE = "Moda Caribe S.A.S.";
  private static final String DIRECCION = "Calle 30 # 17-25, Cartagena";
  private static final String TELEFONO = "3001234567";
  private static final String CONTACTO = "Laura Gomez";

  @Test
  void deberiaMapearYGuardarCliente() {
    // Arrange
    GuardarClientePortStub guardarClientePort = new GuardarClientePortStub();
    AgregarClienteService service = new AgregarClienteService(guardarClientePort);
    CrearClienteComando comando =
        new CrearClienteComando(CODIGO, NOMBRE, DIRECCION, TELEFONO, CONTACTO, "moda");

    // Act
    service.guardar(comando);

    // Assert
    assertThat(guardarClientePort.getClientesGuardados())
        .singleElement()
        .extracting(
            Cliente::getCodigo,
            Cliente::getNombre,
            Cliente::getDireccion,
            Cliente::getTelefono,
            Cliente::getPersonaContacto,
            Cliente::getTipoActividad)
        .containsExactly(CODIGO, NOMBRE, DIRECCION, TELEFONO, CONTACTO, TipoActividad.MODA);
  }

  @Test
  void deberiaRechazarComandoConTipoActividadInvalido() {
    // Arrange
    GuardarClientePortStub guardarClientePort = new GuardarClientePortStub();
    AgregarClienteService service = new AgregarClienteService(guardarClientePort);
    CrearClienteComando comando =
        new CrearClienteComando(CODIGO, NOMBRE, DIRECCION, TELEFONO, CONTACTO, "deportes");

    // Act
    // Assert
    assertThatThrownBy(() -> service.guardar(comando))
        .isInstanceOf(ClienteInvalidoException.class);
    assertThat(guardarClientePort.getClientesGuardados()).isEmpty();
  }

  private static final class GuardarClientePortStub implements GuardarClientePort {
    private final List<Cliente> clientesGuardados = new ArrayList<>();

    @Override
    public void guardar(Cliente cliente) {
      clientesGuardados.add(cliente);
    }

    private List<Cliente> getClientesGuardados() {
      return List.copyOf(clientesGuardados);
    }
  }
}
