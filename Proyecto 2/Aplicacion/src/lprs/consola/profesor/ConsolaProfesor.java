package lprs.consola.profesor;

import java.util.Scanner;

import lprs.consola.ConsolaPrincipal;
import lprs.logica.cuentas.Profesor;
import lprs.persistencia.PersistenciaGeneral;
import lprs.principal.LPRS;

public class ConsolaProfesor extends ConsolaPrincipal {

    private Profesor profesor;
    private Scanner lectura;
    private ConsolaActividadProfesor consolaActividad;
    private ConsolaLPProfesor consolaLP;
    private ConsolaSesionProfesor consolaSesion;

    public ConsolaProfesor(LPRS lprsActual) {
        super(lprsActual);
        profesor = null;
        lectura = new Scanner(System.in);
        consolaActividad = new ConsolaActividadProfesor(lprsActual, this);
        consolaLP = new ConsolaLPProfesor(lprsActual, this);
        consolaSesion = new ConsolaSesionProfesor(lprsActual, this);
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

    public ConsolaLPProfesor getConsolaLP() {
        return consolaLP;
    }

    public ConsolaActividadProfesor getConsolaActividad() {
        return consolaActividad;
    }

    public ConsolaSesionProfesor getConsolaSesion() {
        return consolaSesion;
    }

    public void mostrarConsolaProfesor() {
        System.out.println("-------------------------------------");
        System.out.println("Bienvenido " + profesor.getUsuario());
        String[] opciones = { "Manejar Learning Paths",
                "Manejar actividades", "Salir" };
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

    public static void main(String[] args) {
        LPRS lprs = new LPRS();
        try {
            lprs = PersistenciaGeneral.cargarDatos();
        } catch (Exception e) {
            System.out.println("Error al cargar los datos");
            e.printStackTrace();
        }
        ConsolaProfesor consola = new ConsolaProfesor(lprs);
        consola.getConsolaSesion().mostrarConsolaSesion();
        try {
            PersistenciaGeneral.guardarDatos(lprs);
        } catch (Exception e) {
            System.out.println("Error al guardar los datos");
            e.printStackTrace();
        }
        return;
    }
}
