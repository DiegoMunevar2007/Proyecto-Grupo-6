package lprs.logica.contenido.realizable;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Tarea;
import lprs.logica.cuentas.Estudiante;

public abstract class ActividadRealizable {
	protected String comentarios;
	protected String estado;
	protected Actividad actividadBase;
	protected Estudiante estudiante;
	
	public ActividadRealizable(Actividad actividadBase, Estudiante estudiante) {
		this.comentarios="";
		this.estado="";
		this.actividadBase=actividadBase;
		this.estudiante=estudiante;
	}
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

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Actividad getActividadBase() {
		return actividadBase;
	}

	public void setActividadBase(Actividad actividadBase) {
		this.actividadBase = actividadBase;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	
}
