package lprs.logica.contenido;

import java.util.ArrayList;

import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.contenido.realizable.TareaRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public class Tarea extends Actividad {
	public ArrayList<Seccion> secciones;

	public Tarea(String titulo, String descripcion, String objetivo, int duracionEsperada, boolean obligatoria,
			String fechaLimite, LearningPath lP, String dificultad) {
		super(titulo, descripcion, objetivo, duracionEsperada, obligatoria, fechaLimite, lP, dificultad);
		this.secciones = new ArrayList<Seccion>();
	}

	public TareaRealizable RealizarActividad(TareaRealizable tarea) {
		return tarea;
	}

	public void crearSeccion(int numero, String titulo, String descripcion, String objetivo,
			String contenido, String ejemplo, String explicacion, String pista, String resultadoEsperado) {
		Seccion seccion = new Seccion(numero, titulo, descripcion, objetivo, contenido, ejemplo,
				explicacion, pista, resultadoEsperado);
		addSeccion(seccion);
	}

	public ArrayList<Seccion> getSecciones() {
		return secciones;
	}

	public void setSecciones(ArrayList<Seccion> secciones) {
		this.secciones = secciones;
	}

	public void addSeccion(Seccion nuevaSeccion) {
		secciones.add(nuevaSeccion);
	}

	public void removeSeccion(Seccion seccion) {
		secciones.remove(seccion);
	}

	public Tarea(Actividad actividad) {
		super(actividad);
	}

	public ActividadRealizable crearActividadRealizable(Estudiante estudiante) {
		return new TareaRealizable(this, estudiante);
	}

}
