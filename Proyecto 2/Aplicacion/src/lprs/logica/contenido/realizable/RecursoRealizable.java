package lprs.logica.contenido.realizable;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;

public class RecursoRealizable extends ActividadRealizable {

	RecursoEducativo actividadBase;
	Scanner lecturaRecurso;

	public RecursoRealizable(RecursoEducativo actividadBase, Estudiante estudiante) {
		super(estudiante);
		this.estado = "No completado";
		this.actividadBase = actividadBase;
		lecturaRecurso = new Scanner(System.in);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList realizarActividad() throws ActividadPreviaException{
		// TODO Auto-generated method stub
		try {
			verificarEligibilidad();
		} catch (ActividadPreviaException e) {
			throw e;
		}
		tiempoTomado = (int) System.currentTimeMillis();
		return new ArrayList();
	}

	@Override
	public void guardarActividad(ArrayList respuestas) {
		LearningPath lP = actividadBase.getLearningPathAsignado();
		estudiante.getAvance(lP.getID()).addActividadRealizada(this);
	}

	@Override
	public void enviarActividad(ArrayList respuestas) {
		System.out.println("¿Desea marcar la actividad como completada? (S/N)");
		String respuesta = lecturaRecurso.nextLine();
		if (respuesta.equalsIgnoreCase("S")) {
			try {
				setEstado("Completado");
			} catch (EstadoException e) {
				System.out.println(e.getMessage());
			}
		}
		guardarActividad(respuestas);
		Profesor profesor = actividadBase.getLearningPathAsignado().getProfesorCreador();
		profesor.addActividadPendiente(this);
		System.out.println("Actividad completada!");
		

	}

	@Override
	public void setEstado(String estado) throws EstadoException {
		try {
			if (estado.equals("No completado") || estado.equals("Completado")) {
				this.estado = estado;
			} else {
				throw new EstadoException(this.getActividadBase(), estado);
			}
		} catch (EstadoException e) {
			e.printStackTrace();
			return;
		}

	}

	@Override
	public Actividad getActividadBase() {
		// TODO Auto-generated method stub
		return actividadBase;
	}

	@Override
	public void calificarActividad() {
		System.out.println("La informacion del recurso es la siguiente: ");
		System.out.println("Titulo del recurso: " + actividadBase.getTitulo());
		System.out.println("Descripcion del recurso: " + actividadBase.getDescripcion());
		System.out.println("Estudiante: " + estudiante.getUsuario());
		System.out.println("Esta actividad no se puede calificar.");

	}

}
