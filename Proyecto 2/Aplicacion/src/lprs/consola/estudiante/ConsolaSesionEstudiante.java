package lprs.consola.estudiante;

import java.util.Scanner;

import lprs.exceptions.ContraseniaIncorrectaException;
import lprs.exceptions.TipoUsuarioIncorrectoException;
import lprs.exceptions.UsuarioRepetidoException;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Usuario;
import lprs.consola.estudiante.ConsolaEstudiante;
import lprs.principal.LPRS;

public class ConsolaSesionEstudiante {
    private ConsolaEstudiante consolaEstudiante;
    private LPRS lprsActual;
    public ConsolaSesionEstudiante(LPRS lprsActual, ConsolaEstudiante consolaEstudiante){
        this.consolaEstudiante=consolaEstudiante;
        this.lprsActual=lprsActual;
    }
    public void crearCuenta() throws UsuarioRepetidoException {
        String usuario = consolaEstudiante.pedirString("Ingrese su nombre de usuario: ");
        String contrasenia = consolaEstudiante.pedirString("Ingrese su contraseña: ");
        try {
            lprsActual.getManejadorSesion().crearUsuario(usuario, contrasenia, 1);
        } catch (UsuarioRepetidoException e){
            throw e;
        }
        catch (Exception e) {
            System.out.println("Error al crear la cuenta");
            e.printStackTrace();
        }
        System.out.println("Cuenta creada con éxito.");
        mostrarConsolaSesion();
    }

    public void iniciarSesion() {
        String ID = consolaEstudiante.pedirString("Ingrese su nombre de usuario: ");
        String contrasenia = consolaEstudiante.pedirString("Ingrese su contraseña: ");
        try {
            Usuario usuario = lprsActual.getManejadorSesion().iniciarSesion(ID, contrasenia);
            if (usuario == null) {
                mostrarConsolaSesion();
                return;
            }
            if (usuario.getTipo().equals("Profesor")) {
                throw new TipoUsuarioIncorrectoException("Profesor");
            } else {
                Estudiante estudiante = (Estudiante) usuario;
                consolaEstudiante.setEstudiante(estudiante);
                consolaEstudiante.mostrarConsolaEstudiante();
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
        System.out.println("Bienvenido a Learning Path Recommendation System - Estudiante");
        String[] opciones = { "Iniciar sesión", "Crear una cuenta", "Salir" };
        consolaEstudiante.mostrarOpciones(opciones.length, opciones);
        int opcion = consolaEstudiante.pedirInt("Seleccione una opción: ");
        if (opcion == 1) {
            iniciarSesion();
        } else if (opcion == 2) {
            try {
                crearCuenta();
            }
            catch (UsuarioRepetidoException e){
                System.out.println(e.getMessage());
                mostrarConsolaSesion();
            }
        } else if (opcion == 3) {
            System.out.println("Hasta luego!");
            return;
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaSesion();
        }
    }
}
