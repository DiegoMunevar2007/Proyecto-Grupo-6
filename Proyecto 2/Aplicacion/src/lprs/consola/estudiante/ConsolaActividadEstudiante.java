package lprs.consola.estudiante;

import lprs.exceptions.ActividadPreviaException;
import lprs.logica.contenido.*;
import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaAbierta;
import lprs.logica.contenido.pregunta.PreguntaCerrada;
import lprs.logica.contenido.realizable.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ConsolaActividadEstudiante {
    private ConsolaEstudiante consolaEstudiante;

    public ConsolaActividadEstudiante(ConsolaEstudiante consolaEstudiante) {
        this.consolaEstudiante = consolaEstudiante;
    }
    public void realizarEncuesta(Encuesta actividadBase){
        Scanner lectura = consolaEstudiante.getLectura();
        EncuestaRealizable encuestaRealizable = new EncuestaRealizable(actividadBase, consolaEstudiante.getEstudiante());
        ArrayList preguntasEncuesta = new ArrayList();
        try{
            preguntasEncuesta= encuestaRealizable.realizarActividad();
        }
        catch (ActividadPreviaException e) {
            System.out.println(e.getMessage());
            System.out.println("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
            int respuestaInt = lectura.nextInt();
            if (respuestaInt == 2) {
                return;
            } else {
                System.out.println("Continuando con la actividad...");
                encuestaRealizable.setBypassActividadPrevia(true);
                realizarEncuesta(actividadBase);
            }
        }
        System.out.println("Realizando encuesta...");
        System.out.println("Titulo: " + actividadBase.getTitulo());
        System.out.println("Descripcion: " + actividadBase.getDescripcion());
        System.out.println("Duracion esperada: " + actividadBase.getDuracionEsperada());
        System.out.println("Preguntas:");
        ArrayList respuestasEstudiante = new ArrayList();
        for (int i = 0; i < preguntasEncuesta.size(); i++) {
            PreguntaAbierta pregunta = (PreguntaAbierta) preguntasEncuesta.get(i);
            System.out.println("Pregunta " + (i + 1) + ": " + pregunta.getEnunciado());
            System.out.println("Respuesta: ");
            String respuesta = lectura.nextLine();
            PreguntaAbiertaRealizable preguntaRealizada = new PreguntaAbiertaRealizable(respuesta, pregunta);
            respuestasEstudiante.add(preguntaRealizada);
        }
        encuestaRealizable.enviarActividad(respuestasEstudiante);
        System.out.println("Encuesta realizada exitosamente.");
    }

    public void realizarExamen(Examen actividadBase){
        Scanner lectura = consolaEstudiante.getLectura();
        ExamenRealizable examenRealizable = new ExamenRealizable(consolaEstudiante.getEstudiante(), actividadBase);
        ArrayList preguntasExamen = new ArrayList();
        try {
            preguntasExamen = examenRealizable.realizarActividad();
        } catch (ActividadPreviaException e) {
            System.out.println(e.getMessage());
            System.out.println("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
            int respuestaInt = lectura.nextInt();
            if (respuestaInt == 2) {
                return;
            } else {
                System.out.println("Continuando con la actividad...");
                examenRealizable.setBypassActividadPrevia(true);
                realizarExamen(actividadBase);
            }
        }
        ArrayList preguntasRealizadas = new ArrayList();
        System.out.println("Realizando examen...");
        for (int i = 0; i < preguntasExamen.size(); i++) {
            PreguntaAbierta pregunta = (PreguntaAbierta) preguntasExamen.get(i);
            System.out.println("Pregunta " + i + ": " + pregunta.getEnunciado());
            System.out.println("A continuación, ingrese su respuesta: ");
            String respuesta = lectura.nextLine();
            PreguntaAbiertaRealizable preguntaRealizable = new PreguntaAbiertaRealizable(respuesta, pregunta);
            preguntasRealizadas.add(preguntaRealizable);
        }
        examenRealizable.enviarActividad(preguntasRealizadas);
    }
    public void realizarQuiz(Quiz actividadBase){
        Scanner lectura = consolaEstudiante.getLectura();
        QuizRealizable quizRealizable = new QuizRealizable(consolaEstudiante.getEstudiante(), actividadBase);
        ArrayList<PreguntaCerrada> preguntasQuiz = new ArrayList();
        try {
            preguntasQuiz = quizRealizable.realizarActividad();
        } catch (ActividadPreviaException e) {
            System.out.println(e.getMessage());
            System.out.println("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
            int respuestaInt = lectura.nextInt();
            if (respuestaInt == 2) {
                return;
            } else {
                System.out.println("Continuando con la actividad...");
                quizRealizable.setBypassActividadPrevia(true);
                realizarQuiz(actividadBase);
            }
        }
        ArrayList preguntasRealizadas = new ArrayList();
        for (PreguntaCerrada pregunta : preguntasQuiz) {
            System.out.println(pregunta.getEnunciado());
            System.out.println("Opciones:");
            Opcion[] opciones = pregunta.getOpciones();
            for (int i = 0; i < pregunta.getOpciones().length; i++) {
                System.out.println(i + 1 + ". " + opciones[i].getOpcion());
            }
            System.out.println("Ingrese el número de la respuesta correcta:");
            int respuesta = lectura.nextInt();
            PreguntaCerradaRealizable preguntaRealizable = new PreguntaCerradaRealizable(pregunta, opciones[respuesta - 1]);
            preguntasRealizadas.add(preguntaRealizable);
            if (preguntaRealizable.verificarOpcion(opciones[respuesta - 1])) {
                quizRealizable.incCorrectas();
            }
    }
        quizRealizable.enviarActividad(preguntasRealizadas);
    }
    public void realizarRecurso(RecursoEducativo actividadBase){
        Scanner lectura = consolaEstudiante.getLectura();
        RecursoRealizable recursoRealizable = new RecursoRealizable(actividadBase, consolaEstudiante.getEstudiante());
        ArrayList respuestas = new ArrayList();
        try {
            respuestas = recursoRealizable.realizarActividad();
        } catch (ActividadPreviaException e) {
            System.out.println(e.getMessage());
            System.out.println("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
            int respuestaInt = lectura.nextInt();
            if (respuestaInt == 2) {
                return;
            } else {
                System.out.println("Continuando con la actividad...");
                recursoRealizable.setBypassActividadPrevia(true);
                realizarRecurso(actividadBase);
            }
        }
        System.out.println("Realizando recurso...");
        System.out.println("Titulo del recurso: " + actividadBase.getTitulo());
        System.out.println("Descripcion del recurso: " + actividadBase.getDescripcion());
        System.out.println("Duracion esperada: " + actividadBase.getDuracionEsperada());
        System.out.println("Tipo de recurso a consultar: " + actividadBase.getTipoRecurso());
        System.out.println("URL del recurso: " + actividadBase.getUrl());
        System.out.println("¿Desea entregar el recurso?");
        String respuesta = lectura.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            recursoRealizable.enviarActividad(respuestas);
        }
    }
    public void realizarTarea(Tarea actividadBase){
        Scanner lectura = consolaEstudiante.getLectura();
        TareaRealizable tareaRealizable = new TareaRealizable(actividadBase, consolaEstudiante.getEstudiante());
        try{
            tareaRealizable.realizarActividad();
        }
        catch (ActividadPreviaException e) {
            System.out.println(e.getMessage());
            System.out.println("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
            int respuestaInt = lectura.nextInt();
            if (respuestaInt == 2) {
                return;
            } else {
                System.out.println("Continuando con la actividad...");
                realizarTarea(actividadBase);
            }
        }
        System.out.println("Realizando tarea...");
        System.out.println("Titulo: " + actividadBase.getTitulo());
        System.out.println("Descripcion: " + actividadBase.getDescripcion());
        System.out.println("Duracion esperada: " + actividadBase.getDuracionEsperada());
        if (!actividadBase.getSecciones().isEmpty()) {
            System.out.println("Secciones:");
            ArrayList<Seccion> secciones = actividadBase.getSecciones();
            int i = 0;
            while (i < secciones.size()) {
                Seccion seccion = secciones.get(i);
                System.out.println("Seccion #" + seccion.getNumero());
                System.out.println("Titulo: " + seccion.getTitulo());
                System.out.println("Descripcion: " + seccion.getDescripcion());
                System.out.println("Cotentido: " + seccion.getContenido());
                if (seccion.getEjemplo() != null) {
                    System.out.println("Ejemplo: " + seccion.getEjemplo());
                }
                if (seccion.getExplicacion() != null) {
                    System.out.println("Explicacion: " + seccion.getExplicacion());
                }

                if (seccion.getPista() != null) {
                    System.out.println("Desea ver una pista? (S/N)");
                    String respuesta = lectura.nextLine();
                    if (respuesta.equalsIgnoreCase("S")) {
                        System.out.println("Pista: " + seccion.getPista());
                    }
                }
                if (seccion.getResultadoEsperado() != null) {
                    System.out.println("Ingrese el resultado esperado: ");
                    String resultado = lectura.nextLine();
                    if (resultado.equals(seccion.getResultadoEsperado())) {
                        System.out.println("Resultado correcto");
                    } else {
                        System.out.println("Resultado incorrecto");
                        System.out.println("El resultado esperado es: " + seccion.getResultadoEsperado());
                    }
                }
                System.out.println("Desea continuar con la siguiente seccion? (S/N)");
                String respuesta = lectura.nextLine();
                if (respuesta.equalsIgnoreCase("N")) {
                    return;
                }
                i++;
            }
        }
            System.out.println("¿Ha enviado la tarea? (S/N)");
            String respuesta = lectura.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                tareaRealizable.enviarActividad(new ArrayList());
            }
    }
}
