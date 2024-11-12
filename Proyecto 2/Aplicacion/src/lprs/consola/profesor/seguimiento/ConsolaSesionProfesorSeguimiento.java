package lprs.consola.profesor.seguimiento;

import java.util.Scanner;

import lprs.exceptions.TipoUsuarioIncorrectoException;
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
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("Ingrese su nombre de usuario: ");
        String usuario = lectura.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasenia = lectura.nextLine();
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
        System.out.println("Ingrese su nombre de usuario: ");
        String ID = lectura.nextLine();
        System.out.println("Ingrese su contraseña: ");
        String contrasenia = lectura.nextLine();
        try {
            Usuario usuario = lprsActual.getManejadorSesion().iniciarSesion(ID, contrasenia);
            if (usuario == null) {
                mostrarConsolaSesion();
                return;
            }
            if (usuario.getTipo().equals("ESTUDIANTE")) {
                throw new TipoUsuarioIncorrectoException("ESTUDIANTE");
            } else {
                Profesor profesor = (Profesor) usuario;
                consolaProfesor.setProfesor(profesor);
                consolaProfesor.mostrarConsolaProfesor();
            }
        } catch (TipoUsuarioIncorrectoException e) {
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
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        Scanner lectura = consolaProfesor.getLectura();
        int opcion = lectura.nextInt();
        lectura.nextLine();
        if (opcion == 1) {
            iniciarSesion();
        } else if (opcion == 2) {
            crearCuenta();
        } else if (opcion == 3) {
            System.out.println("Hasta luego!");
            try {
                lprsActual.guardarDatos();
            } catch (Exception e) {
                System.out.println("Error al guardar los datos");
                e.printStackTrace();
            }
            lectura.close();
            return;
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            lectura.close();
            mostrarConsolaSesion();
        }
    }
}
