package lprs.logica.contenido.realizable;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Seccion;
import lprs.logica.contenido.Tarea;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.Avance;
import lprs.logica.learningPath.LearningPath;

public class TareaRealizable extends ActividadRealizable {
	private Tarea actividadBase;
	private int seccionActual;
	private Scanner lectura;

	public TareaRealizable(Tarea actividadBase, Estudiante estudiante) {
		super(estudiante);
		this.actividadBase = actividadBase;
		this.estado = "No Exitoso";
		seccionActual = 0;
		seccionActual = 0;
		lectura = new Scanner(System.in);

	}

	@Override
	public void realizarActividad() {

		try {
			verificarEligibilidad();
		} catch (ActividadPreviaException e) {
			System.out.println(e.getMessage());
			System.out.println("¿Desea continuar con la actividad sin realizar las demás? (S/N)");
			String respuesta = lectura.nextLine();
			if (respuesta.equalsIgnoreCase("N")) {
				return;
			} else {
				System.out.println("Continuando con la actividad...");
			}
		} catch (Exception e) {
			System.out.println("Ocurrió un error: " + e.getMessage());
		}
		long tiempoInicial = System.currentTimeMillis();
		System.out.println("Realizando tarea...");
		System.out.println("Titulo: " + actividadBase.getTitulo());
		System.out.println("Descripcion: " + actividadBase.getDescripcion());
		System.out.println("Duracion esperada: " + actividadBase.getDuracionEsperada());
		if (!actividadBase.getSecciones().isEmpty()) {
			System.out.println("Secciones:");
			ArrayList<Seccion> secciones = actividadBase.getSecciones();
			int i = 0;
			i = seccionActual;
			while (i < secciones.size()) {
				Seccion seccion = secciones.get(i);
				System.out.println("Seccion #" + seccion.getNumero());
				System.out.println("Titulo: " + seccion.getTitulo());
				System.out.println("Descripcion: " + seccion.getDescripcion());
				System.out.println("Cotentido: " + seccion.getContenido());
				if (seccion.getEjemplo() != null) {
					System.out.println("Ejemplo: " + seccion.getEjemplo());
				}
				if (seccion.getExplicacion() != null) {
					System.out.println("Explicacion: " + seccion.getExplicacion());
				}

				if (seccion.getPista() != null) {
					System.out.println("Desea ver una pista? (S/N)");
					String respuesta = lectura.nextLine();
					if (respuesta.equalsIgnoreCase("S")) {
						System.out.println("Pista: " + seccion.getPista());
					}
				}
				if (seccion.getResultadoEsperado() != null) {
					System.out.println("Ingrese el resultado esperado: ");
					String resultado = lectura.nextLine();
					if (resultado.equals(seccion.getResultadoEsperado())) {
						System.out.println("Resultado correcto");
					} else {
						System.out.println("Resultado incorrecto");
						System.out.println("El resultado esperado es: " + seccion.getResultadoEsperado());
					}
				}
				System.out.println("Desea continuar con la siguiente seccion? (S/N)");
				String respuesta = lectura.nextLine();
				if (respuesta.equalsIgnoreCase("N")) {
					guardarActividad();
					return;
				}
				i++;
				seccionActual = i;
			}
			System.out.println("¿Ha enviado la tarea? (S/N)");
			String respuesta = lectura.nextLine();
			if (respuesta.equalsIgnoreCase("S")) {
				long tiempoFinal = System.currentTimeMillis();
				tiempoTomado = (int) (tiempoFinal - tiempoInicial) * 1000;
				enviarActividad();
			}
		} else {
			System.out.println("¿Ha enviado la tarea? (S/N)");
			String respuesta = lectura.nextLine();
			if (respuesta.equalsIgnoreCase("S")) {
				long tiempoFinal = System.currentTimeMillis();
				tiempoTomado = (int) (tiempoFinal - tiempoInicial) * 1000;
				enviarActividad();
			}
		}
	}

	@Override
	public void enviarActividad() {
		// TODO Auto-generated method stub
		guardarActividad();
		try {
			setEstado("No Exitoso");
		} catch (EstadoException e) {
			e.printStackTrace();
		}
		Profesor profesor = actividadBase.getLearningPathAsignado().getProfesorCreador();
		profesor.addActividadPendiente(this);

	}

	@Override
	public void setEstado(String estado) throws EstadoException {
		if (estado.equals("No Exitoso") || estado.equals("Exitoso")) {
			this.estado = estado;
		} else {
			throw new EstadoException(this.getActividadBase(), estado);
		}

	}

	public Actividad getActividadBase() {
		return actividadBase;
	}

	public void setActividadBase(Tarea actividadBase) {
		this.actividadBase = actividadBase;
	}

	@Override
	public void guardarActividad() {
		LearningPath lP = actividadBase.getLearningPathAsignado();
		estudiante.getAvance(lP.getID()).addActividadRealizada(this);
	}

	@Override
	public void calificarActividad() {

		System.out.println("Calificacion de la tarea: ");
		System.out.println("Estudiante: " + estudiante.getUsuario());
		System.out.println("Tarea: " + actividadBase.getTitulo());
		System.out.println("Estado de la tarea: " + estado);
		System.out.println("1. No exitoso");
		System.out.println("2. Exitoso");
		int opcion = lectura.nextInt();
		String estado = "";
		if (opcion == 1) {
			estado = "No Exitoso";
		} else if (opcion == 2) {
			estado = "Exitoso";
		}
		try {
			setEstado(estado);
		} catch (EstadoException e) {
			System.out.println(e.getMessage());
		}
	}

}
