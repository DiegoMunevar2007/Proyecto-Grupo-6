package lprs.logica.learningPath;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.realizable.ActividadRealizable;

public class Avance {
	private double actividadesCompletadas;
	private Date fechaInicio;
	private Date fechaFin;
	private double tiempoDedicado;
	private double tasaExito;
	private double tasaFracaso;
	private LearningPath learningPathCorrespondiente;
	private List<ActividadRealizable> actividadesRealizadas;
	private HashMap<String,Actividad> actividadesObligatorias;
	
	public Avance(Date fechaInicio, LearningPath learningPathCorrespondiente) {
		this.actividadesCompletadas=0.0;
		this.fechaInicio = fechaInicio;
		this.fechaFin= null;
		this.tiempoDedicado=0.0;
		this.tasaExito=0.0;
		this.tasaFracaso=0.0;
		this.learningPathCorrespondiente = learningPathCorrespondiente;
		this.actividadesRealizadas = new ArrayList<ActividadRealizable>();
		this.setActividadesObligatorias(new HashMap<String,Actividad>());
	}

	public double getActividadesCompletadas() {
		return actividadesCompletadas;
	}

	public void setActividadesCompletadas(double actividadesCompletadas) {
		this.actividadesCompletadas = actividadesCompletadas;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
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

	public List<ActividadRealizable> getActividadesRealizadas() {
		return actividadesRealizadas;
	}

	public void setActividadesRealizadas(List<ActividadRealizable> actividadesRealizadas) {
		this.actividadesRealizadas = actividadesRealizadas;
	}

	public HashMap<String,Actividad> getActividadesObligatorias() {
		return actividadesObligatorias;
	}

	public void setActividadesObligatorias(HashMap<String,Actividad> actividadesObligatorias) {
		this.actividadesObligatorias = actividadesObligatorias;
	}
	public Actividad obtenerActividadObligatoria(String numeroActividad) {
		return this.actividadesObligatorias.get(numeroActividad);
	}
	
	
}
