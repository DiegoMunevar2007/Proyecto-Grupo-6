package lprs.consola.profesor.seguimiento;

import lprs.consola.ConsolaPrincipal;
import lprs.logica.cuentas.Profesor;
import lprs.persistencia.PersistenciaGeneral;
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
       boolean salir = false;
        while (!salir) {
            mostrarOpciones(opciones.length, opciones);
            int opcion = pedirInt("Seleccione una opcion: ");
            if (opcion == 1) {
                consolaLP.mostrarConsolaLP();
            } else if (opcion == 2) {
                consolaActividad.calificarActividadesPendientes();
            } else if (opcion == 3) {
                System.out.println("Hasta luego!");
                salir = true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            }
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
    public ConsolaSesionProfesorSeguimiento getConsolaSesion() {
    	return consolaSesion;
    }
    
    
    public static void main(String[] args) {
        LPRS lprs = new LPRS();
        try {
            lprs = PersistenciaGeneral.cargarDatos("Persistencia.dat");
        } catch (Exception e) {
            System.out.println("Error al cargar los datos");
            e.printStackTrace();
        }
        ConsolaProfesorSeguimiento consola = new ConsolaProfesorSeguimiento(lprs);
        consola.getConsolaSesion().mostrarConsolaSesion();
        try {
            PersistenciaGeneral.guardarDatos(lprs,"Persistencia.dat");
        } catch (Exception e) {
            System.out.println("Error al guardar los datos");
            e.printStackTrace();
        }
        consola.getLectura().close();
        return;
    }

}
