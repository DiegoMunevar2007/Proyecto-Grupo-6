package lprs.logica.contenido.realizable;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.exceptions.ActividadPreviaException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Tarea;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;

public class TareaRealizable extends ActividadRealizable {

	public TareaRealizable(Tarea actividadBase, Estudiante estudiante) {
		super(actividadBase, estudiante);
		this.estado = "No Exitoso";
	}

	@Override
	public void realizarActividad() {
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
		System.out.println("Realizando tarea...");
		System.out.println("Titulo: " + actividadBase.getTitulo());
		System.out.println("Descripcion: " + actividadBase.getDescripcion());
		
		// TODO: Implementar la realizacion de la tarea teniendo en cuenta si existen
		// secciones

	}

	public boolean verificarEligibilidad() throws Exception {
		// Verificar si todas las actividades previas estan completas
		LearningPath lP = actividadBase.getLearningPathAsignado();
		Avance avanceEstudiante = estudiante.obtenerAvance(lP.getTitulo());
		boolean todasActividadesPreviasCompletas = true;
		// Se crea una lista de actividades no completadas para mostrar al usuario
		ArrayList<Actividad> actividadesNoCompletadas = new ArrayList<Actividad>();
		// Por cada actividad previa, se verifica si esta en el avance del estudiante
		for (Actividad actividadPrevia : actividadBase.getActividadesPrevias()) {
			// Si no esta en el avance del estudiante, se agrega a la lista de actividades
			// no completadas
			if (avanceEstudiante.obtenerActividadObligatoria(actividadPrevia.getNumeroActividad()) == null) {
				todasActividadesPreviasCompletas = false;
				actividadesNoCompletadas.add(actividadPrevia);
			}
		}
		// Si no se completaron todas las actividades previas, se lanza una excepcion
		if (!todasActividadesPreviasCompletas) {
			throw new ActividadPreviaException(actividadesNoCompletadas);
		}
		return todasActividadesPreviasCompletas;
	}

	@Override
	public void guardarActividad() {
		// TODO Auto-generated method stub

	}

	@Override
	public void enviarActividad() {
		// TODO Auto-generated method stub

	}

}
