package lprs.logica.contenido.realizable;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Seccion;
import lprs.logica.contenido.Tarea;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;

public class TareaRealizable extends ActividadRealizable {
	private Tarea actividadBase;
	private int seccionActual;

	public TareaRealizable(Tarea actividadBase, Estudiante estudiante) {
		super(actividadBase, estudiante);
		this.actividadBase = actividadBase;
		this.estado = "No Exitoso";
		seccionActual = 0;
	}

	@Override
	public ArrayList realizarActividad() throws ActividadPreviaException {

		try {
			verificarEligibilidad();
		} catch (ActividadPreviaException e) {
			throw e;
		}
		tiempoTomado = (int) System.currentTimeMillis();
		return new ArrayList();
	}

	@Override
	public void enviarActividad(ArrayList respuestas) {
		// TODO Auto-generated method stub
		guardarActividad(respuestas);
		try {
			setEstado("No Exitoso");
		} catch (EstadoException e) {
			e.printStackTrace();
		}
		Profesor profesor = actividadBase.getLearningPathAsignado().getProfesorCreador();
		profesor.addActividadPendiente(this);

	}

	@Override
	public void setEstado(String estado) throws EstadoException {
		if (estado.equals("No Exitoso") || estado.equals("Exitoso")) {
			this.estado = estado;
		} else {
			throw new EstadoException(this.getActividadBase(), estado);
		}

	}

	public Actividad getActividadBase() {
		return actividadBase;
	}

	public void setActividadBase(Tarea actividadBase) {
		this.actividadBase = actividadBase;
	}

	@Override
	public void guardarActividad(ArrayList respuestas) {
		LearningPath lP = actividadBase.getLearningPathAsignado();
		estudiante.getAvance(lP.getID()).addActividadRealizada(this);
	}

	@Override
	public void calificarActividad() {
		if (estado.equals("Exitoso")) {
			actividadBase.getLearningPathAsignado().incCantidadActividadesPorDia();
			estudiante.getAvance(actividadBase.getLearningPathAsignado().getID()).incTasaExito();
		}
		else{
			estudiante.getAvance(actividadBase.getLearningPathAsignado().getID()).incTasaFracaso();
		}
	}

}
