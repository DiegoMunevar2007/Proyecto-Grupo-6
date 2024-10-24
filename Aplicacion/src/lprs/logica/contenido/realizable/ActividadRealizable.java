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

	public ActividadRealizable(Estudiante estudiante) {
		this.comentarios = "";
		this.estado = "";
		this.estudiante = estudiante;
	}

	public abstract void calificarActividad();

	public abstract void realizarActividad();

	public abstract void guardarActividad();

	public abstract void enviarActividad();

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getEstado() {
		return estado;
	}

	public abstract void setEstado(String estado) throws EstadoException;

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}

	public abstract Actividad getActividadBase();

	public boolean verificarEligibilidad() throws Exception {
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
			for (ActividadRealizable actividadCompletada : avanceEstudiante.getActividadesRealizadas()) {
				System.out.println("Actividad realizada " + actividadCompletada.getActividadBase().getTitulo());
			}
			if (avanceEstudiante.obtenerActividadObligatoria(actividadPrevia.getNumeroActividad()) == null) {
				todasActividadesPreviasCompletas = false;
				actividadesNoCompletadas.add(actividadPrevia);
			}
		}
		// Si no se completaron todas las actividades previas, se lanza una excepcion
		if (!todasActividadesPreviasCompletas)

		{
			throw new ActividadPreviaException(actividadesNoCompletadas);
		}
		return todasActividadesPreviasCompletas;
	}
}
