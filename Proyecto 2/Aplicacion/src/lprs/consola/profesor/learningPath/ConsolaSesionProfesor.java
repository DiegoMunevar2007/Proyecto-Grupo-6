package lprs.consola.profesor.learningPath;

import java.util.Scanner;

import lprs.exceptions.ContraseniaIncorrectaException;
import lprs.exceptions.TipoUsuarioIncorrectoException;
import lprs.logica.cuentas.Profesor;
import lprs.logica.cuentas.Usuario;
import lprs.principal.LPRS;

public class ConsolaSesionProfesor {

    private LPRS lprsActual;
    private ConsolaProfesor consolaProfesor;

    public ConsolaSesionProfesor(LPRS lprsActual, ConsolaProfesor consolaProfesor) {
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

        String ID = consolaProfesor.pedirString("Ingrese su nombre de usuario: ");
        System.out.println("Ingrese su contraseña: ");
        String contrasenia = consolaProfesor.pedirString("Ingrese su contraseña: ");
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
        } catch (TipoUsuarioIncorrectoException | ContraseniaIncorrectaException e) {
            System.out.println(e.getMessage());
            mostrarConsolaSesion();
        } catch (Exception e) {
            System.out.println("Error al iniciar sesión");
            e.printStackTrace();
        }

    }

    public void mostrarConsolaSesion() {
        System.out.println("Bienvenido a Learning Path Recommendation System - Profesor Learning Path");
        String[] opciones = { "Iniciar sesión", "Crear una cuenta", "Salir" };
        consolaProfesor.mostrarOpciones(opciones.length, opciones);
        int opcion = consolaProfesor.pedirInt("Seleccione una opción: ");
        if (opcion == 1) {
            iniciarSesion();
        } else if (opcion == 2) {
            crearCuenta();
        } else if (opcion == 3) {
            System.out.println("Hasta luego!");
            return;
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaSesion();
        }
    }
}
