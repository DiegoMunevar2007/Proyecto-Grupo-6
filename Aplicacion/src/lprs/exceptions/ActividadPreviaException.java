package lprs.exceptions;

import java.util.ArrayList;

import lprs.logica.contenido.Actividad;

public class ActividadPreviaException extends Exception {

	public ActividadPreviaException(ArrayList<Actividad> actividades) {
		super(crearMensaje(actividades));
	}

	private static String crearMensaje(ArrayList<Actividad> actividades) {
		StringBuilder message = new StringBuilder("Las siguientes actividades previas no se han completado: ");
		for (Actividad actividad : actividades) {
			message.append(actividad.getTitulo()).append(", ");
		}
		if (message.length() > 0) {
			message.setLength(message.length() - 2);
		}
		return message.toString();
	}
}