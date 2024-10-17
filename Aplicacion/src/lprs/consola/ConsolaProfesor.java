package lprs.consola;

import lprs.logica.cuentas.Profesor;

public class ConsolaProfesor extends ConsolaPrincipal{
	Profesor profesor;
	public ConsolaProfesor(Profesor usuarioEncontrado) {
		super();
		profesor= usuarioEncontrado;
	}
    public void mostrarConsolaEstudiante() {
        System.out.println("Bienvenido + " + profesor.getUsuario());
        String[] opciones = { "Ver mis Leaning Paths hechos", "Ver los learning Paths disponibles", "Salir" };
        mostrarOpciones(3, opciones);
        int opcion = lectura.nextInt();
        if (opcion == 1) {
            
        } else if (opcion == 2) {
        }
    }
}
