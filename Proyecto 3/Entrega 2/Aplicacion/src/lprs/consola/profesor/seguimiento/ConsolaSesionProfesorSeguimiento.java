package lprs.consola.profesor.seguimiento;

import java.util.Scanner;

import lprs.exceptions.ContraseniaIncorrectaException;
import lprs.exceptions.TipoUsuarioIncorrectoException;
import lprs.exceptions.UsuarioNotFoundException;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.principal.LPRS;

public class ConsolaSesionProfesorSeguimiento {

    private LPRS lprsActual;
    private ConsolaProfesorSeguimiento consolaProfesor;

    public ConsolaSesionProfesorSeguimiento(LPRS lprsActual, ConsolaProfesorSeguimiento consolaProfesor) {
        this.lprsActual = lprsActual;
        this.consolaProfesor = consolaProfesor;
    }

    public void crearCuenta() {
        String usuario = consolaProfesor.pedirString("Ingrese su nombre de usuario: ");
        String contrasenia = consolaProfesor.pedirString("Ingrese su contraseña: ");
        try {
            lprsActual.getManejadorSesion().crearUsuario(usuario, contrasenia, 2);
        } catch (Exception e) {
            System.out.println("Error al crear la cuenta");
            e.printStackTrace();
        }
        System.out.println("Cuenta creada con éxito.");
        mostrarConsolaSesion();
    }

    public void iniciarSesion() {
        Scanner lectura = consolaProfesor.getLectura();
        String ID = consolaProfesor.pedirString("Ingrese su nombre de usuario: ");
        String contrasenia = consolaProfesor.pedirString("Ingrese su contraseña: ");
        try {
            Usuario usuario = lprsActual.getManejadorSesion().iniciarSesion(ID, contrasenia);
            if (usuario == null) {
                return;
            }
            if (usuario.getTipo().equals("ESTUDIANTE")) {
                throw new TipoUsuarioIncorrectoException("ESTUDIANTE");
            } else {
                Profesor profesor = (Profesor) usuario;
                consolaProfesor.setProfesor(profesor);
                consolaProfesor.mostrarConsolaProfesor();
            }
        } catch (TipoUsuarioIncorrectoException | ContraseniaIncorrectaException | UsuarioNotFoundException e) {
            System.out.println(e.getMessage());
            mostrarConsolaSesion();
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión");
            e.printStackTrace();
        }

    }

    public void mostrarConsolaSesion() {
        System.out.println("Bienvenido a Learning Path Recommendation System - Profesor Seguimiento");
        String[] opciones = { "Iniciar sesión", "Crear una cuenta", "Salir" };
        boolean continuar = true;
        while (continuar) {
            consolaProfesor.mostrarOpciones(opciones.length, opciones);
            int opcion = consolaProfesor.pedirInt("Seleccione una opción: ");
            if (opcion == 1) {
                iniciarSesion();
            } else if (opcion == 2) {
                crearCuenta();
            } else if (opcion == 3) {
                System.out.println("Hasta luego!");
                continuar = false;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            }
        }
    }
}
