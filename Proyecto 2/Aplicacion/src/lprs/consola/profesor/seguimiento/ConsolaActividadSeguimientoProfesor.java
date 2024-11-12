package lprs.consola.profesor.seguimiento;

public class ConsolaActividadSeguimientoProfesor {
    private ConsolaProfesorSeguimiento consolaProfesor;
    public ConsolaActividadSeguimientoProfesor(ConsolaProfesorSeguimiento consolaProfesor) {
        this.consolaProfesor = consolaProfesor;
    }
    public void mostrarConsolaActividad(){
        System.out.println("Mostrando actividades pendientes");
    }
}
