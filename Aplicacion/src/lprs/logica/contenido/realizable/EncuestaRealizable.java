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

    public EncuestaRealizable(Encuesta actividadBase, Estudiante estudiante) {
        super(estudiante);
        this.actividadBase = actividadBase;
    }

    @Override
    public void calificarActividad() {

    }

    @Override
    public void realizarActividad() {
        ArrayList<PreguntaAbierta> preguntasEncuesta = actividadBase.getPreguntasEncuesta();
        System.out.println("Realizando encuesta...");
        System.out.println("Titulo: " + actividadBase.getTitulo());
        System.out.println("Descripcion: " + actividadBase.getDescripcion());
        System.out.println("Duracion esperada: " + actividadBase.getDuracionEsperada());
        System.out.println("Preguntas:");
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < preguntasEncuesta.size(); i++) {
            PreguntaAbierta pregunta = preguntasEncuesta.get(i);
            System.out.println("Pregunta " + i + ": " + pregunta.getEnunciado());
            System.out.println("Respuesta: ");
            String respuesta = scanner.nextLine();
            PreguntaAbiertaRealizable preguntaRealizada = new PreguntaAbiertaRealizable(respuesta, pregunta);
            preguntasRealizadas.add(preguntaRealizada);
        }
        scanner.close();
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
        // TODO Auto-generated method stub
        return actividadBase;
    }

}
