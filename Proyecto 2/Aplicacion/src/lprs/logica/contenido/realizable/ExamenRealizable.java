package lprs.logica.contenido.realizable;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Examen;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;

public class ExamenRealizable extends ActividadRealizable {

    private Examen actividadBase;
    private ArrayList<PreguntaAbiertaRealizable> preguntasRealizadas;

    public ExamenRealizable(Estudiante estudiante, Examen examen) {
        super(estudiante);
        this.actividadBase = examen;
        this.preguntasRealizadas = new ArrayList<PreguntaAbiertaRealizable>();
        this.tiempoTomado = 0;
        // TODO Auto-generated constructor stub
    }

    @Override
    //TODO: Mover esta consola al seguimiento de profesor
    public void calificarActividad() {
     
      }

    @Override
    public ArrayList realizarActividad() throws ActividadPreviaException {
        try {
            verificarEligibilidad();
        } catch (ActividadPreviaException e) {
            throw e;
        }
        ArrayList preguntasExamen = actividadBase.getPreguntasExamen();
        tiempoTomado = (int) System.currentTimeMillis();
        return preguntasExamen;
    }

    @Override
    public void guardarActividad(ArrayList respuestas) {
        tiempoTomado = (int) (System.currentTimeMillis() - tiempoTomado)/1000 ;
        LearningPath lP = actividadBase.getLearningPathAsignado();
        estudiante.getAvance(lP.getID()).addActividadRealizada(this);
    }

    @Override
    public void enviarActividad(ArrayList respuestas) {
        guardarActividad(respuestas);
        try {
            setEstado("Enviado");
        } catch (EstadoException e) {
            e.printStackTrace();
        }
        Profesor profesor = actividadBase.getLearningPathAsignado().getProfesorCreador();
        profesor.addActividadPendiente(this);
    }

    @Override
    public void setEstado(String estado) throws EstadoException {
        if (estado.equals("Enviado") || estado.equals("No exitoso") || estado.equals("Exitoso")) {
            this.estado = estado;
        } else {
            throw new EstadoException(this.getActividadBase(), estado);
        }
    }

    @Override
    public Actividad getActividadBase() {
        return actividadBase;
    }
    public ArrayList<PreguntaAbiertaRealizable> getPreguntas() {
        return preguntasRealizadas;
    }

	public ArrayList<PreguntaAbiertaRealizable> getPreguntasRealizadas() {
		return preguntasRealizadas;
	}

	public void setPreguntasRealizadas(ArrayList<PreguntaAbiertaRealizable> preguntasRealizadas) {
		this.preguntasRealizadas = preguntasRealizadas;
	}

	public void setActividadBase(Examen actividadBase) {
		this.actividadBase = actividadBase;
	}
    
    
}
