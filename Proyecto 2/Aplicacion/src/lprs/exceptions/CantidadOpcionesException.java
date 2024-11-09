package lprs.exceptions;

import lprs.logica.contenido.Actividad;

public class CantidadOpcionesException extends Exception {

	public CantidadOpcionesException(int cantidad, Actividad actividad) {
		super(crearMensaje(cantidad,actividad));
	}
	
	public static String crearMensaje(int cantidad,Actividad actividad) {
		return "La cantidad de opciones en " + actividad.getTitulo() + "("+Integer.toString(cantidad)+") es invalida";
	}
}
