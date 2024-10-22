package lprs.logica.contenido.realizable;

import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Tarea;
import lprs.logica.cuentas.Estudiante;

public abstract class ActividadRealizable {
	protected String comentarios;
	protected String estado;
	protected Estudiante estudiante;
	
	public ActividadRealizable( Estudiante estudiante) {
		this.comentarios="";
		this.estado="";
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

	public abstract void setEstado(String estado) throws EstadoException;


	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiante) {
		this.estudiante = estudiante;
	}
	
}
