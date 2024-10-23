package lprs.logica.contenido.realizable;

import java.util.ArrayList;
import java.util.Scanner;

import lprs.exceptions.EstadoException;
import lprs.logica.contenido.Actividad;
import lprs.logica.contenido.Encuesta;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.cuentas.Estudiante;

public class EncuestaRealizable extends ActividadRealizable {

    private Encuesta actividadBase;
    private ArrayList<PreguntaAbiertaRealizable> preguntasRealizadas;

    public EncuestaRealizable(Encuesta actividadBase, Estudiante estudiante) {
        super(estudiante);
        this.actividadBase = actividadBase;
    }

    @Override
    public void calificarActividad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'calificarActividad'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'guardarActividad'");
    }

    @Override
    public void enviarActividad() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'enviarActividad'");
    }

    @Override
    public void setEstado(String estado) throws EstadoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstado'");
    }

    @Override
    public Actividad getActividadBase() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getActividadBase'");
    }

}
