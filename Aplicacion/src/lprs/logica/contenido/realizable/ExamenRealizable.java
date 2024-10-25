package lprs.logica.contenido.realizable;

import java.util.ArrayList;
import java.util.Scanner;

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
    private int tiempoTomado;

    public ExamenRealizable(Estudiante estudiante, Examen examen) {
        super(estudiante);
        this.actividadBase = examen;
        this.preguntasRealizadas = new ArrayList<PreguntaAbiertaRealizable>();
        this.tiempoTomado=0;
        // TODO Auto-generated constructor stub
    }

    @Override
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
            System.out.println("Pregunta " + i + ": " + preguntaBase.getEnunciado());
            System.out.println("Respuesta: " + preguntaRealizada.getRespuesta());
        }
        System.out.println("Desea calificar el examen como exitoso o no exitoso? (1. Exitoso, 2. No exitoso)");
        Scanner scanner = new Scanner(System.in);
        int opcion = scanner.nextInt();
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
        scanner.close();

    }

    @Override
    public void realizarActividad() {
        // TODO Auto-generated method stub
        ArrayList<PreguntaAbierta> preguntasExamen = actividadBase.getPreguntasExamen();
        Scanner scanner = new Scanner(System.in);
        long tiempoInicial = System.currentTimeMillis();
        for (int i = 0; i < preguntasExamen.size(); i++) {
            scanner.nextLine();
            PreguntaAbierta pregunta = preguntasExamen.get(i);
            System.out.println("Pregunta " + i + ": " + pregunta.getEnunciado());
            System.out.println("A continuación, ingrese su respuesta: ");
            String respuesta = scanner.nextLine();
            PreguntaAbiertaRealizable preguntaRealizable = new PreguntaAbiertaRealizable(respuesta, pregunta);
            preguntasRealizadas.add(preguntaRealizable);
        }
        scanner.close();
        System.out.println("Examen completado.");
        long tiempoFinal = System.currentTimeMillis();
        tiempoTomado = (int)(tiempoFinal-tiempoInicial)*1000;
        enviarActividad();
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
        return actividadBase;
    }

}
