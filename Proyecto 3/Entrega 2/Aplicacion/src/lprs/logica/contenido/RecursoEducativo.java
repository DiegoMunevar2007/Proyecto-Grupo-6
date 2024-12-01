package lprs.logica.contenido;

import java.util.ArrayList;

import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.RecursoRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class RecursoEducativo extends Actividad {
	private String tipoRecurso;
	private String url;
	public Boolean estado = false;
	
	
	public RecursoEducativo(String titulo, String descripcion, String objetivo, int duracionEsperada,
			boolean obligatoria, String fechaLimite, LearningPath lP, String dificultad, String tipoRecurso,
			String url) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
		this.tipoRecurso = tipoRecurso;
		this.url = url;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String direccionUrl) {
		this.url = direccionUrl;
	}

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String recurso) {
		this.tipoRecurso = recurso;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Override
	public RecursoRealizable crearActividadRealizable(Estudiante estudiante) {
		RecursoRealizable recursoR = new RecursoRealizable(this, estudiante);
		actividadesRealizablesCreadas.add(recursoR);
		return recursoR;
	}

}
