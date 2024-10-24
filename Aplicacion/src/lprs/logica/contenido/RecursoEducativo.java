package lprs.logica.contenido;

import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.RecursoRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class RecursoEducativo extends Actividad {
	private String tipoRecurso;
	private String url;

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

	public void setEstado(String estado) {
		// TODO Auto-generated method stub

	}

	@Override
	public ActividadRealizable crearActividadRealizable(Estudiante estudiante) {
		// TODO Auto-generated method stub
		RecursoRealizable recursoR = new RecursoRealizable(this, estudiante);
		actividadesRealizablesCreadas.add(recursoR);
		return recursoR;
	}

}
