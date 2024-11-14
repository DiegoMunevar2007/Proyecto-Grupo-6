package lprs.logica.contenido.realizable;

import java.io.Serializable;
import java.util.ArrayList;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;

public abstract class ActividadRealizable implements Serializable {
	protected String comentarios;
	protected String estado;
	protected Estudiante estudiante;
	protected Actividad actividadBase;
	protected int tiempoTomado;
	protected boolean bypassActividadPrevia;

	public ActividadRealizable(Estudiante estudiante) {
		this.comentarios = "";
		this.estado = "";
		this.estudiante = estudiante;
		tiempoTomado = 0;
		bypassActividadPrevia = false;
	}

	public abstract void calificarActividad();

	public abstract ArrayList realizarActividad() throws ActividadPreviaException;

	public abstract void guardarActividad(ArrayList respuestasEsperadas);

	public abstract void enviarActividad(ArrayList respuestasEsperadas);

	public int getTiempoTomado() {
		return tiempoTomado;
	}

	public void setTiempoTomado(int tiempoTomado) {
		this.tiempoTomado = tiempoTomado;
	}

	public void setActividadBase(Actividad actividadBase) {
		this.actividadBase = actividadBase;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getEstado() {
		return estado;
	}
	public boolean isBypassActividadPrevia() {
		return bypassActividadPrevia;
	}
	public void setBypassActividadPrevia(boolean bypassActividadPrevia) {
		this.bypassActividadPrevia = bypassActividadPrevia;
	}

	public abstract void setEstado(String estado) throws EstadoException;

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public abstract Actividad getActividadBase();

	public boolean verificarEligibilidad() throws ActividadPreviaException {
		// Verificar si todas las actividades previas estan completas
		Actividad actividadBase = getActividadBase();
		LearningPath lP = actividadBase.getLearningPathAsignado();
		Avance avanceEstudiante = estudiante.obtenerAvance(lP.getID());
		boolean todasActividadesPreviasCompletas = true;
		// Se crea una lista de actividades no completadas para mostrar al usuario
		ArrayList<Actividad> actividadesNoCompletadas = new ArrayList<Actividad>();
		// Por cada actividad previa, se verifica si esta en el avance del estudiante
		for (Actividad actividadPrevia : actividadBase.getActividadesPrevias()) {
			// Si no esta en el avance del estudiante, se agrega a la lista de actividades
			// no completadas
			if (!avanceEstudiante.getActividadesCompletadasLista().contains(actividadPrevia)) {
				todasActividadesPreviasCompletas = false;
				actividadesNoCompletadas.add(actividadPrevia);
			}
			// Si no se completaron todas las actividades previas, se lanza una excepcion
			if (!todasActividadesPreviasCompletas && !bypassActividadPrevia)

			{
				throw new ActividadPreviaException(actividadesNoCompletadas);
			}

		}
		return todasActividadesPreviasCompletas;
	}
	public Avance getAvance() {
		return estudiante.getAvance(actividadBase.getLearningPathAsignado().getID());
	}
}
