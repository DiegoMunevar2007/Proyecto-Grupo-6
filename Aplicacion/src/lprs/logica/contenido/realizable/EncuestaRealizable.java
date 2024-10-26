package lprs.logica.contenido.realizable;

import java.util.ArrayList;
import java.util.Scanner;

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
    private Scanner lecturaEncuesta;

    public EncuestaRealizable(Encuesta actividadBase, Estudiante estudiante) {
        super(estudiante);
        this.actividadBase = actividadBase;
        lecturaEncuesta = new Scanner(System.in);
        preguntasRealizadas = new ArrayList<PreguntaAbiertaRealizable>();
    }

    @Override
    public void calificarActividad() {

    }

    @Override
    public void realizarActividad() {
        try {
            verificarEligibilidad();
        } catch (Exception e) {
            System.out.println("Ocurrió un error: " + e.getMessage());
        }
        long tiempoInicial = System.currentTimeMillis();
        ArrayList<PreguntaAbierta> preguntasEncuesta = actividadBase.getPreguntasEncuesta();
        System.out.println("Realizando encuesta...");
        System.out.println("Titulo: " + actividadBase.getTitulo());
        System.out.println("Descripcion: " + actividadBase.getDescripcion());
        System.out.println("Duracion esperada: " + actividadBase.getDuracionEsperada());
        System.out.println("Preguntas:");
        String respuesta = "";
        for (int i = 0; i < preguntasEncuesta.size(); i++) {
            PreguntaAbierta pregunta = preguntasEncuesta.get(i);
            System.out.println("Pregunta " + (i + 1) + ": " + pregunta.getEnunciado());
            System.out.println("Respuesta: ");
            respuesta = lecturaEncuesta.nextLine();
            PreguntaAbiertaRealizable preguntaRealizada = new PreguntaAbiertaRealizable(respuesta, pregunta);
            preguntasRealizadas.add(preguntaRealizada);
        }
        long tiempoFinal = System.currentTimeMillis();
        tiempoTomado = (int) (tiempoFinal - tiempoInicial) / 1000;
        enviarActividad();
        System.out.println("Encuesta realizada.");
    }

    @Override
    public void guardarActividad() {
        LearningPath lP = actividadBase.getLearningPathAsignado();
        estudiante.getAvance(lP.getID()).addActividadRealizada(this);
    }

    @Override
    public void enviarActividad() {
        guardarActividad();
        try {
            setEstado("Completado");
        } catch (EstadoException e) {
            e.printStackTrace();
        }
        Profesor profesor = actividadBase.getLearningPathAsignado().getProfesorCreador();
        profesor.addActividadPendiente(this);
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
        // TODO Auto-generated method stub
        return actividadBase;
    }

}
