package com.jcaa.udec.collections.entrypoint.cli;

import com.jcaa.udec.collections.domain.core.exception.RadioInvalidaException;
import com.jcaa.udec.collections.domain.core.exception.RadioNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.RadioYaExisteException;
import com.jcaa.udec.collections.domain.core.exception.UsuarioInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.UsuarioNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.UsuarioYaExisteException;
import com.jcaa.udec.collections.domain.core.valueobject.Email;
import com.jcaa.udec.collections.domain.core.valueobject.Frecuencia;
import com.jcaa.udec.collections.domain.core.valueobject.NombreRadio;
import com.jcaa.udec.collections.domain.core.valueobject.NombreUsuario;
import com.jcaa.udec.collections.domain.core.valueobject.Password;
import com.jcaa.udec.collections.domain.core.valueobject.RadioId;
import com.jcaa.udec.collections.domain.core.valueobject.TipoTransmision;
import com.jcaa.udec.collections.domain.core.valueobject.UsuarioId;
import com.jcaa.udec.collections.entrypoint.controller.RadioControlador;
import com.jcaa.udec.collections.entrypoint.controller.UsuarioControlador;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarRadioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarUsuarioPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerRadioResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerUsuarioResponse;

import java.util.Scanner;

public class GuiCli {
    private static final int OPCION_AGREGAR_USUARIO = 1;
    private static final int OPCION_BUSCAR_USUARIO = 2;
    private static final int OPCION_LISTAR_USUARIOS = 3;
    private static final int OPCION_AGREGAR_RADIO = 4;
    private static final int OPCION_BUSCAR_RADIO = 5;
    private static final int OPCION_LISTAR_RADIOS = 6;
    private static final int OPCION_ACTUALIZAR_RADIO = 7;
    private static final int OPCION_ELIMINAR_RADIO = 8;
    private static final int OPCION_SALIR = 9;
    private static final String TEXTO_TITULO = "** EJEMPLO DE USO DE LISTAS Y HEXAGONAL **";
    private static final String TITULO_REGISTRO_USUARIO = "** INGRESE LOS DATOS DEL NUEVO USUARIO **";
    private static final String TITULO_REGISTRO_RADIO = "** INGRESE LOS DATOS DE LA NUEVA RADIO **";
    private static final String TITULO_ACTUALIZAR_RADIO = "** INGRESE LOS NUEVOS DATOS DE LA RADIO **";
    private static final String SEPARADOR = "- - - - - - - - - ";
    private static final String OPCIONES = "Opciones:";
    private static final String TEXTO_OPCION_AGREGAR_USUARIO = "1 - Agregar Usuario";
    private static final String TEXTO_OPCION_BUSCAR_USUARIO = "2 - Buscar Usuario por Id";
    private static final String TEXTO_OPCION_LISTAR_USUARIOS = "3 - Ver todos los Usuarios";
    private static final String TEXTO_OPCION_AGREGAR_RADIO = "4 - Agregar Radio";
    private static final String TEXTO_OPCION_BUSCAR_RADIO = "5 - Buscar Radio por Id";
    private static final String TEXTO_OPCION_LISTAR_RADIOS = "6 - Ver todas las Radios";
    private static final String TEXTO_OPCION_ACTUALIZAR_RADIO = "7 - Actualizar Radio";
    private static final String TEXTO_OPCION_ELIMINAR_RADIO = "8 - Eliminar Radio";
    private static final String TEXTO_OPCION_SALIR = "9 - Salir";
    private static final String TEXTO_SOLICITUD_OPCION = "Ingrese el numero de la opcion: ";
    private static final String SOLICITUD_ID = "ID: ";
    private static final String SOLICITUD_PASSWORD = "PASSWORD: ";
    private static final String SOLICITUD_NOMBRE = "NOMBRE: ";
    private static final String SOLICITUD_EMAIL = "EMAIL: ";
    private static final String SOLICITUD_ID_RADIO = "ID RADIO: ";
    private static final String SOLICITUD_NOMBRE_RADIO = "NOMBRE RADIO: ";
    private static final String SOLICITUD_FRECUENCIA = "FRECUENCIA: ";
    private static final String SOLICITUD_TIPO_TRANSMISION = "TIPO (AM/FM): ";
    private static final String MENSAJE_OPCION_INVALIDA = "Opcion [%s] invalida";
    private static final String MENSAJE_ID_INVALIDO = "ID INVALIDO: debe ser un numero entero";
    private static final String MENSAJE_PASSWORD_INVALIDO =
            "PASSWORD INVALIDO: minimo 10 caracteres, con mayuscula, minuscula, numero y simbolo";
    private static final String MENSAJE_NOMBRE_INVALIDO = "NOMBRE INVALIDO: minimo 3 caracteres";
    private static final String MENSAJE_EMAIL_INVALIDO = "EMAIL INVALIDO: ingrese un correo valido";
    private static final String MENSAJE_ID_RADIO_INVALIDO = "ID INVALIDO: debe ser un numero entero";
    private static final String MENSAJE_NOMBRE_RADIO_INVALIDO = "NOMBRE INVALIDO: minimo 3 caracteres";
    private static final String MENSAJE_FRECUENCIA_INVALIDA = "FRECUENCIA INVALIDA: debe ser un numero positivo";
    private static final String MENSAJE_TIPO_TRANSMISION_INVALIDO = "TIPO INVALIDO: ingrese AM o FM";
    private static final String MENSAJE_ERROR = "ERROR: ";
    private static final String MENSAJE_REGISTRO_EXITOSO = "Usuario registrado correctamente.";
    private static final String MENSAJE_REGISTRO_RADIO_EXITOSO = "Radio registrada correctamente.";
    private static final String MENSAJE_ACTUALIZACION_RADIO_EXITOSA = "Radio actualizada correctamente.";
    private static final String MENSAJE_ELIMINACION_RADIO_EXITOSA = "Radio eliminada correctamente.";
    private static final String MENSAJE_LISTA_VACIA = "No hay usuarios registrados.";
    private static final String MENSAJE_LISTA_RADIOS_VACIA = "No hay radios registradas.";
    private static final String MENSAJE_DESPEDIDA = "Esperamos tu regreso. Bye, Bye";
    private static final String MARCA_ORDEN_BYTES = "\uFEFF";
    private static final String TEXTO_VACIO = "";
    private final UsuarioControlador usuarioControlador;
    private final RadioControlador radioControlador;
    private final Scanner entrada;

    public GuiCli(UsuarioControlador usuarioControlador, RadioControlador radioControlador) {
        this(usuarioControlador, radioControlador, new Scanner(System.in));
    }

    GuiCli(UsuarioControlador usuarioControlador, RadioControlador radioControlador, Scanner entrada) {
        this.usuarioControlador = usuarioControlador;
        this.radioControlador = radioControlador;
        this.entrada = entrada;
    }

    public int obtenerOpcionMenu() {
        do {
            mostrarMenu();
            String valorIngresado = limpiarEntrada(entrada.nextLine());
            try {
                int opcion = Integer.parseInt(valorIngresado);
                if (opcion >= OPCION_AGREGAR_USUARIO && opcion <= OPCION_SALIR) {
                    return opcion;
                }
            } catch (NumberFormatException exception) {
                // El flujo informa el valor invalido y vuelve a mostrar el menu.
            }
            System.out.printf(MENSAJE_OPCION_INVALIDA + "%n", valorIngresado);
        } while (true);
    }

    public void ejecutarAccion() {
        boolean continuar = true;
        while (continuar) {
            int opcion = obtenerOpcionMenu();
            try {
                switch (opcion) {
                    case OPCION_AGREGAR_USUARIO -> registrarUsuario();
                    case OPCION_BUSCAR_USUARIO -> mostrarUsuarioPorId();
                    case OPCION_LISTAR_USUARIOS -> mostrarTodosLosUsuarios();
                    case OPCION_AGREGAR_RADIO -> registrarRadio();
                    case OPCION_BUSCAR_RADIO -> mostrarRadioPorId();
                    case OPCION_LISTAR_RADIOS -> mostrarTodasLasRadios();
                    case OPCION_ACTUALIZAR_RADIO -> actualizarRadio();
                    case OPCION_ELIMINAR_RADIO -> eliminarRadio();
                    case OPCION_SALIR -> continuar = false;
                }
            } catch (UsuarioInvalidoException
                    | UsuarioNoExisteException
                    | UsuarioYaExisteException
                    | RadioInvalidaException
                    | RadioNoExisteException
                    | RadioYaExisteException exception) {
                System.out.println(MENSAJE_ERROR + exception.getMessage());
            }
        }
        System.out.println(MENSAJE_DESPEDIDA);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println(TEXTO_TITULO);
        System.out.println(SEPARADOR);
        System.out.println(OPCIONES);
        System.out.println(SEPARADOR);
        System.out.println(TEXTO_OPCION_AGREGAR_USUARIO);
        System.out.println(TEXTO_OPCION_BUSCAR_USUARIO);
        System.out.println(TEXTO_OPCION_LISTAR_USUARIOS);
        System.out.println(TEXTO_OPCION_AGREGAR_RADIO);
        System.out.println(TEXTO_OPCION_BUSCAR_RADIO);
        System.out.println(TEXTO_OPCION_LISTAR_RADIOS);
        System.out.println(TEXTO_OPCION_ACTUALIZAR_RADIO);
        System.out.println(TEXTO_OPCION_ELIMINAR_RADIO);
        System.out.println(TEXTO_OPCION_SALIR);
        System.out.print(TEXTO_SOLICITUD_OPCION);
    }

    private void registrarUsuario() {
        usuarioControlador.registrar(capturarDatosUsuario());
        System.out.println(MENSAJE_REGISTRO_EXITOSO);
    }

    private void mostrarUsuarioPorId() {
        System.out.println(usuarioControlador.obtenerPorId(capturarId()));
    }

    private void mostrarTodosLosUsuarios() {
        ObtenerUsuarioResponse response = usuarioControlador.obtenerTodos();
        if (response.estaVacia()) {
            System.out.println(MENSAJE_LISTA_VACIA);
            return;
        }
        System.out.println(response);
    }

    private void registrarRadio() {
        radioControlador.registrar(capturarDatosRadio());
        System.out.println(MENSAJE_REGISTRO_RADIO_EXITOSO);
    }

    private void mostrarRadioPorId() {
        System.out.println(radioControlador.obtenerPorId(capturarIdRadio()));
    }

    private void mostrarTodasLasRadios() {
        ObtenerRadioResponse response = radioControlador.obtenerTodos();
        if (response.estaVacia()) {
            System.out.println(MENSAJE_LISTA_RADIOS_VACIA);
            return;
        }
        System.out.println(response);
    }

    private void actualizarRadio() {
        System.out.println();
        System.out.println(TITULO_ACTUALIZAR_RADIO);
        String id = capturarIdRadio();
        radioControlador.actualizar(new ActualizarRadioPeticion(
                id, capturarNombreRadio(), capturarFrecuencia(), capturarTipoTransmision()));
        System.out.println(MENSAJE_ACTUALIZACION_RADIO_EXITOSA);
    }

    private void eliminarRadio() {
        radioControlador.eliminar(capturarIdRadio());
        System.out.println(MENSAJE_ELIMINACION_RADIO_EXITOSA);
    }

    private RegistrarUsuarioPeticion capturarDatosUsuario() {
        System.out.println();
        System.out.println(TITULO_REGISTRO_USUARIO);
        return new RegistrarUsuarioPeticion(
                capturarId(), capturarPassword(), capturarNombre(), capturarEmail());
    }

    private RegistrarRadioPeticion capturarDatosRadio() {
        System.out.println();
        System.out.println(TITULO_REGISTRO_RADIO);
        return new RegistrarRadioPeticion(
                capturarIdRadio(), capturarNombreRadio(), capturarFrecuencia(), capturarTipoTransmision());
    }

    private String capturarId() {
        do {
            System.out.print(SOLICITUD_ID);
            String id = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new UsuarioId(id))) {
                return id;
            }
            System.out.println(MENSAJE_ID_INVALIDO);
        } while (true);
    }

    private String capturarPassword() {
        do {
            System.out.print(SOLICITUD_PASSWORD);
            String password = entrada.nextLine();
            if (esValido(() -> new Password(password))) {
                return password;
            }
            System.out.println(MENSAJE_PASSWORD_INVALIDO);
        } while (true);
    }

    private String capturarNombre() {
        do {
            System.out.print(SOLICITUD_NOMBRE);
            String nombre = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new NombreUsuario(nombre))) {
                return nombre;
            }
            System.out.println(MENSAJE_NOMBRE_INVALIDO);
        } while (true);
    }

    private String capturarEmail() {
        do {
            System.out.print(SOLICITUD_EMAIL);
            String email = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new Email(email))) {
                return email;
            }
            System.out.println(MENSAJE_EMAIL_INVALIDO);
        } while (true);
    }

    private String capturarIdRadio() {
        do {
            System.out.print(SOLICITUD_ID_RADIO);
            String id = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new RadioId(id))) {
                return id;
            }
            System.out.println(MENSAJE_ID_RADIO_INVALIDO);
        } while (true);
    }

    private String capturarNombreRadio() {
        do {
            System.out.print(SOLICITUD_NOMBRE_RADIO);
            String nombre = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new NombreRadio(nombre))) {
                return nombre;
            }
            System.out.println(MENSAJE_NOMBRE_RADIO_INVALIDO);
        } while (true);
    }

    private String capturarFrecuencia() {
        do {
            System.out.print(SOLICITUD_FRECUENCIA);
            String frecuencia = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new Frecuencia(frecuencia))) {
                return frecuencia;
            }
            System.out.println(MENSAJE_FRECUENCIA_INVALIDA);
        } while (true);
    }

    private String capturarTipoTransmision() {
        do {
            System.out.print(SOLICITUD_TIPO_TRANSMISION);
            String tipo = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> TipoTransmision.desde(tipo))) {
                return tipo;
            }
            System.out.println(MENSAJE_TIPO_TRANSMISION_INVALIDO);
        } while (true);
    }

    private static String limpiarEntrada(String valor) {
        return valor.replace(MARCA_ORDEN_BYTES, TEXTO_VACIO).trim();
    }

    private static boolean esValido(Runnable validacion) {
        try {
            validacion.run();
            return true;
        } catch (UsuarioInvalidoException | RadioInvalidaException exception) {
            return false;
        }
    }
}
