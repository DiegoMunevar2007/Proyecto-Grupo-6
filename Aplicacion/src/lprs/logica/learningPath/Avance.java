package lprs.logica.learningPath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.realizable.ActividadRealizable;

public class Avance implements Serializable {
	private double actividadesCompletadasPorcentaje;
	private int cantidadActividadesObligatorias;
	private String fechaInicio;
	private String fechaFin;
	private double tiempoDedicado;
	private double tasaExito;
	private double tasaFracaso;
	private LearningPath learningPathCorrespondiente;
	private ArrayList<Actividad> actividadesPendientes;
	private HashMap<Actividad, ActividadRealizable> actividadesCompletadas;
	private ArrayList<Actividad> actividadesCompletadasLista;
	

	public Avance(String fechaInicio, LearningPath learningPathCorrespondiente) {
		this.actividadesCompletadasPorcentaje = 0.0;
		this.cantidadActividadesObligatorias = learningPathCorrespondiente.getCantidadObligatorias();
		this.fechaInicio = fechaInicio;
		this.fechaFin = null;
		this.tiempoDedicado = 0.0;
		this.tasaExito = 0.0;
		this.tasaFracaso = 0.0;
		this.learningPathCorrespondiente = learningPathCorrespondiente;
		this.actividadesPendientes = learningPathCorrespondiente.getActividades();
		this.actividadesCompletadas = new HashMap<Actividad,ActividadRealizable>();
		this.actividadesCompletadasLista = new ArrayList<Actividad>();
	}


	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getTiempoDedicado() {
		return tiempoDedicado;
	}

	public void setTiempoDedicado(double tiempoDedicado) {
		this.tiempoDedicado = tiempoDedicado;
	}

	public double getTasaExito() {
		return tasaExito;
	}

	public void setTasaExito(double tasaExito) {
		this.tasaExito = tasaExito;
	}

	public double getTasaFracaso() {
		return tasaFracaso;
	}

	public void setTasaFracaso(double tasaFracaso) {
		this.tasaFracaso = tasaFracaso;
	}

	public LearningPath getLearningPathCorrespondiente() {
		return learningPathCorrespondiente;
	}

	public void setLearningPathCorrespondiente(LearningPath learningPathCorrespondiente) {
		this.learningPathCorrespondiente = learningPathCorrespondiente;
	}
	public void addActividadPendiente(Actividad actividad) {
		actividadesPendientes.add(actividad);
		if (actividad.isObligatoria()) {
			cantidadActividadesObligatorias++;
		}
	}
	
	

	public int getCantidadActividadesObligatorias() {
		return cantidadActividadesObligatorias;
	}


	public void setCantidadActividadesObligatorias(int cantidadActividadesObligatorias) {
		this.cantidadActividadesObligatorias = cantidadActividadesObligatorias;
	}


	public ArrayList<Actividad> getActividadesCompletadasLista() {
		return actividadesCompletadasLista;
	}


	public void setActividadesCompletadasLista(ArrayList<Actividad> actividadesCompletadasLista) {
		this.actividadesCompletadasLista = actividadesCompletadasLista;
	}


	public HashMap<Actividad, ActividadRealizable> getActividadesCompletadas() {
		return actividadesCompletadas;
	}


	public double getActividadesCompletadasPorcentaje() {
		return actividadesCompletadasPorcentaje;
	}

	public void setActividadesCompletadasPorcentaje(double actividadesCompletadasPorcentaje) {
		this.actividadesCompletadasPorcentaje = actividadesCompletadasPorcentaje;
	}

	public ArrayList<Actividad> getActividadesPendientes() {
		return actividadesPendientes;
	}

	public void setActividadesPendientes(ArrayList<Actividad> actividadesPendientes) {
		this.actividadesPendientes = actividadesPendientes;
	}

	public void setActividadesCompletadas(HashMap<Actividad, ActividadRealizable> actividadesCompletadas) {
		this.actividadesCompletadas = actividadesCompletadas;
	}

	public void addActividadRealizada(ActividadRealizable actividadRealizada) {
		this.actividadesPendientes.remove(actividadRealizada.getActividadBase());
		Actividad actividadBase = actividadRealizada.getActividadBase();
		if (actividadBase.isObligatoria()) {
			cantidadActividadesObligatorias--;
		}
		actividadesCompletadas.put(actividadBase, actividadRealizada);
		actividadesCompletadasLista.add(actividadBase);
		tiempoDedicado+=actividadRealizada.getTiempoTomado();
		  
	}

}
