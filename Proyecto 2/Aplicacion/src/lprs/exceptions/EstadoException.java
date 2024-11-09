package lprs.exceptions;

import lprs.logica.contenido.Actividad;

public class EstadoException extends Exception {

	public EstadoException(Actividad actividad, String estado) {
		super("El estado "+ estado + "no es valido para la actividad" + actividad.getTitulo());
	}
	
}
