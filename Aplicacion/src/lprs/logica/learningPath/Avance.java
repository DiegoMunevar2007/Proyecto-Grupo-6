package lprs.logica.learningPath;

import java.util.Date;

public class Avance {
	private double actividadesCompletadas;
	private Date fechaInicio;
	private Date fechaFin;
	private double tiempoDedicado;
	private double tasaExito;
	private double tasaFracaso;
	private LearningPath learningPathCorrespondiente;
	
	public Avance(Date fechaInicio, LearningPath learningPathCorrespondiente) {
		this.actividadesCompletadas=0.0;
		this.fechaInicio = fechaInicio;
		this.fechaFin= null;
		this.tiempoDedicado=0.0;
		this.tasaExito=0.0;
		this.tasaFracaso=0.0;
		this.learningPathCorrespondiente = learningPathCorrespondiente;	
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
	
	
}
