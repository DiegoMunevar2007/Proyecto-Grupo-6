package lprs.consola.profesor.seguimiento;

import lprs.consola.ConsolaPrincipal;
import lprs.consola.profesor.learningPath.ConsolaActividadProfesor;
import lprs.consola.profesor.learningPath.ConsolaLPProfesor;
import lprs.consola.profesor.learningPath.ConsolaSesionProfesor;
import lprs.logica.cuentas.Profesor;
import lprs.principal.LPRS;
import java.util.Scanner;

public class ConsolaProfesorSeguimiento extends ConsolaPrincipal {

    private Profesor profesor;
    private Scanner lectura;
    private ConsolaActividadSeguimientoProfesor consolaActividad;
    private ConsolaLPSeguimientoProfesor consolaLP;
    private ConsolaSesionProfesorSeguimiento consolaSesion;


    public ConsolaProfesorSeguimiento(LPRS lprsActual) {
        super(lprsActual);
        profesor = null;
        lectura = new Scanner(System.in);
        consolaActividad = new ConsolaActividadSeguimientoProfesor( this);
        consolaLP = new ConsolaLPSeguimientoProfesor( this);
        consolaSesion = new ConsolaSesionProfesorSeguimiento(lprsActual, this);
    }
    public void mostrarConsolaProfesor() {
        System.out.println("-------------------------------------");
        System.out.println("Bienvenido " + profesor.getUsuario());
        String[] opciones = { "Ver mis learning paths",
                "Ver mis actividades pendientes", "Salir" };
        mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            consolaLP.mostrarConsolaLP();
        } else if (opcion == 2) {
            consolaActividad.mostrarConsolaActividad();
        } else if (opcion == 3) {
            System.out.println("Hasta luego!");
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarConsolaProfesor();
        }
    }

    public Scanner getLectura() {
        return lectura;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public ConsolaLPSeguimientoProfesor getConsolaLP() {
        return consolaLP;
    }

}
