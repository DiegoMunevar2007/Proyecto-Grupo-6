package lprs.logica.contenido.realizable;

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

	public RecursoRealizable(RecursoEducativo actividadBase, Estudiante estudiante) {
		super(estudiante);
		this.estado = "No completado";
		// TODO Auto-generated constructor stub
	}

	@Override
	public void realizarActividad() {
		// TODO Auto-generated method stub
		Scanner lectura = new Scanner(System.in);
		try {
			verificarEligibilidad();
		} catch (ActividadPreviaException e) {
			System.out.println(e.getMessage());
			System.out.println("¿Desea continuar con la actividad sin realizar las demás? (S/N)");
			String respuesta = lectura.nextLine();
			if (respuesta.equalsIgnoreCase("N")) {
				lectura.close();
				return;
			} else {
				System.out.println("Continuando con la actividad...");
			}
		} catch (Exception e) {
			System.out.println("Ocurrió un error: " + e.getMessage());
		}
		System.out.println("Realizando recurso...");
		System.out.println("Titulo del recurso: " + actividadBase.getTitulo());
		System.out.println("Descripcion del recurso: " + actividadBase.getDescripcion());
		System.out.println("Duracion esperada: " + actividadBase.getDuracionEsperada());
		System.out.println("Tipo de recurso a consultar" + actividadBase.getTipoRecurso());
		System.out.println("URL del recurso: " + actividadBase.getUrl());
		lectura.close();
		enviarActividad();

	}

	@Override
	public void guardarActividad() {
		LearningPath lP = actividadBase.getLearningPathAsignado();
		estudiante.getAvance(lP.getID()).addActividadRealizada(this);
	}

	@Override
	public void enviarActividad() {
		Scanner lectura = new Scanner(System.in);
		System.out.println("¿Desea marcar la actividad como completada? (S/N)");
		String respuesta = lectura.nextLine();
		if (respuesta.equalsIgnoreCase("S")) {
			try {
				setEstado("Completado");
			} catch (EstadoException e) {
				System.out.println(e.getMessage());
			}
		}
		guardarActividad();
		Profesor profesor = actividadBase.getLearningPathAsignado().getProfesorCreador();
		profesor.addActividadPendiente(this);
		System.out.println("Actividad completada!");
		lectura.close();

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
