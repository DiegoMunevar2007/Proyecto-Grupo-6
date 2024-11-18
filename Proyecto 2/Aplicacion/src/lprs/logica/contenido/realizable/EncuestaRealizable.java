package lprs.logica.contenido.realizable;

import java.util.ArrayList;

import lprs.exceptions.ActividadPreviaException;
import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;

public class EncuestaRealizable extends ActividadRealizable {

    private Encuesta actividadBase;
    private ArrayList<PreguntaAbiertaRealizable> preguntasRealizadas;
    private long tiempoInicial;
    private long tiempoFinal;

    public EncuestaRealizable(Encuesta actividadBase, Estudiante estudiante) {
        super(estudiante);
        this.actividadBase = actividadBase;;
        preguntasRealizadas = new ArrayList<PreguntaAbiertaRealizable>();
    }

    @Override
    public void calificarActividad() {
        System.out.println("Información de la encuesta:");
        System.out.println("Preguntas: " + preguntasRealizadas.size());
        System.out.println("Titulo: " + actividadBase.getTitulo());
        System.out.println("Estudiante: " + estudiante.getUsuario());
        System.out.println("Estado: " + estado);
        System.out.println("Contenido de la encuesta:");
        for (int i = 0; i < preguntasRealizadas.size(); i++) {
            PreguntaAbiertaRealizable preguntaRealizada = preguntasRealizadas.get(i);
            PreguntaAbierta preguntaBase = preguntaRealizada.getPreguntaBase();
            System.out.println("Pregunta " + i + ": " + preguntaBase.getEnunciado());
            System.out.println("Respuesta: " + preguntaRealizada.getRespuesta());
        }

    }

    @Override
    public ArrayList realizarActividad() throws ActividadPreviaException {
        try {
            verificarEligibilidad();
        } catch (ActividadPreviaException e) {
            throw e;
        }
        this.tiempoInicial = System.currentTimeMillis();
        ArrayList preguntasEncuesta = actividadBase.getPreguntasEncuesta();
        return preguntasEncuesta;
    }



    public void guardarActividad(ArrayList respuestas) {
        this.tiempoFinal = System.currentTimeMillis();
        setTiempoTomado((int) (tiempoFinal - tiempoInicial));
        preguntasRealizadas = respuestas;
        LearningPath lP = actividadBase.getLearningPathAsignado();
        estudiante.getAvance(lP.getID()).addActividadRealizada(this);
    }

    public ArrayList<PreguntaAbierta> obtenerPreguntas(){
        ArrayList<PreguntaAbierta> preguntasEncuesta = actividadBase.getPreguntasEncuesta();
        return preguntasEncuesta;
    }

    @Override
    public void enviarActividad(ArrayList respuestas) { //ArrayList de PreguntasAbiertasRealizables
        guardarActividad(respuestas);
        try {
            setEstado("Completado");
        } catch (EstadoException e) {
            e.printStackTrace();
        }
        Profesor profesor = actividadBase.getLearningPathAsignado().getProfesorCreador();
        profesor.addActividadPendiente(this);
    }
    
    public ArrayList<PreguntaAbiertaRealizable> getPreguntasRealizadas(){
    	return this.preguntasRealizadas;
    	
    }
    
    @Override
    public void setEstado(String estado) throws EstadoException {
        if (estado.equals("Completado")) {
            this.estado = estado;
        } else {
            throw new EstadoException(this.getActividadBase(), estado);
        }
    }

    @Override
    public Actividad getActividadBase() {
        return actividadBase;
    }

}
