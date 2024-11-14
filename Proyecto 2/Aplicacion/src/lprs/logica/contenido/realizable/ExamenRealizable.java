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
    private Scanner lecturaExamen;

    public ExamenRealizable(Estudiante estudiante, Examen examen) {
        super(estudiante);
        this.actividadBase = examen;
        this.preguntasRealizadas = new ArrayList<PreguntaAbiertaRealizable>();
        this.tiempoTomado = 0;
        this.lecturaExamen = new Scanner(System.in);
        // TODO Auto-generated constructor stub
    }

    @Override
    //TODO: Mover esta consola al seguimiento de profesor
    public void calificarActividad() {
        System.out.println("Información del examen:");
        System.out.println("Preguntas: " + preguntasRealizadas.size());
        System.out.println("Titulo: " + actividadBase.getTitulo());
        System.out.println("Estudiante: " + estudiante.getUsuario());
        System.out.println("Estado: " + estado);
        System.out.println("Contenido del examen:");
        for (int i = 0; i < preguntasRealizadas.size(); i++) {
            PreguntaAbiertaRealizable preguntaRealizada = preguntasRealizadas.get(i);
            PreguntaAbierta preguntaBase = preguntaRealizada.getPreguntaBase();
            System.out.println("Pregunta " + i+1 + ": " + preguntaBase.getEnunciado());
            System.out.println("Respuesta: " + preguntaRealizada.getRespuesta());
        }
        System.out.println("Desea calificar el examen como exitoso o no exitoso? (1. Exitoso, 2. No exitoso)");

        int opcion = lecturaExamen.nextInt();
        if (opcion == 1) {
            try {
                setEstado("Exitoso");
            } catch (EstadoException e) {
                e.printStackTrace();
            }
        } else if (opcion == 2) {
            try {
                setEstado("No exitoso");
            } catch (EstadoException e) {
                e.printStackTrace();
            }
        }
        lecturaExamen.close();
        actividadBase.getLearningPathAsignado().getProfesorCreador().actividadCalificada(this);    
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

}
