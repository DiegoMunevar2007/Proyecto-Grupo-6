package lprs.exceptions;

import lprs.logica.contenido.realizable.ActividadRealizable;

public class EstadoException extends Exception {

	public EstadoException(ActividadRealizable actividad, String estado) {
		super("El estado "+ estado + "no es valido para la actividad" + actividad.getActividadBase().getTitulo());
	}
	
}
