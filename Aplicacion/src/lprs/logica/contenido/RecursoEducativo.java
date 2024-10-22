package lprs.logica.contenido;

import java.util.Date;

import lprs.logica.learningPath.LearningPath;

public class RecursoEducativo extends Actividad{
	private String tipoRecurso;
	private String url;
	
	public RecursoEducativo(String titulo, String descripcion, String objetivo, int duracionEsperada,
			boolean obligatoria, Date fechaLimite, LearningPath lP, String dificultad, String tipoRecurso, String url) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
		this.tipoRecurso = tipoRecurso;
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String direccionUrl ) {
		this.url = direccionUrl;
	}

	public String getTipoRecurso() {
		return tipoRecurso;
	}

	public void setTipoRecurso(String recurso ) {
		this.tipoRecurso = recurso;
	}
	
	@Override
	public Actividad crearActividadRealizable(Actividad actividad) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setEstado(String estado) {
		// TODO Auto-generated method stub
		
	}

}
	
