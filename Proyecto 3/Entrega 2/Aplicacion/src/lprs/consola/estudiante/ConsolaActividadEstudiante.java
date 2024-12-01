    package lprs.consola.estudiante;
    
    import lprs.exceptions.ActividadPreviaException;
    import lprs.logica.contenido.*;
    import lprs.logica.contenido.pregunta.Opcion;
    import lprs.logica.contenido.pregunta.PreguntaAbierta;
    import lprs.logica.contenido.pregunta.PreguntaCerrada;
    import lprs.logica.contenido.realizable.*;
    
    import java.util.ArrayList;
    
    public class ConsolaActividadEstudiante {
        private ConsolaEstudiante consolaEstudiante;
    
        public ConsolaActividadEstudiante(ConsolaEstudiante consolaEstudiante) {
            this.consolaEstudiante = consolaEstudiante;
        }
    
        public void recomendarSeguimiento(Actividad actividad){
            ArrayList<Actividad> actividadesSeguimiento = actividad.getActividadesSeguimiento();
            if (!actividadesSeguimiento.isEmpty()) {
                System.out.println("Actividades de seguimiento recomendadas:");
                for (int i = 0; i < actividadesSeguimiento.size(); i++) {
                    System.out.println(i + 1 + ". " + actividadesSeguimiento.get(i).getTitulo());
                }
                int respuesta = consolaEstudiante.pedirInt("¿Desea realizar alguna de estas actividades? (1. Sí, 2. No)");
                if (respuesta == 1) {
                    int actividadSeleccionada = consolaEstudiante.pedirInt("Ingrese el número de la actividadSeleccionada que desea realizar: ");
                    if (actividadSeleccionada > 0 && actividadSeleccionada <= actividadesSeguimiento.size()) {
                        Actividad actividadBase = actividadesSeguimiento.get(actividadSeleccionada - 1);
                        if (actividadBase instanceof Examen) {
                            realizarExamen((Examen) actividadBase);
                        } else if (actividadBase instanceof Quiz) {
                            realizarQuiz((Quiz) actividadBase);
                        } else if (actividadBase instanceof RecursoEducativo) {
                            realizarRecurso((RecursoEducativo) actividadBase);
                        } else if (actividadBase instanceof Tarea) {
                            realizarTarea((Tarea) actividadBase);
                        } else if (actividadBase instanceof Encuesta) {
                            realizarEncuesta((Encuesta) actividadBase);
                        }
                    }
                }
            }
        }
    
    
    
        public void realizarEncuesta(Encuesta actividadBase) {
            EncuestaRealizable encuestaRealizable = new EncuestaRealizable(actividadBase, consolaEstudiante.getEstudiante());
            ArrayList preguntasEncuesta = new ArrayList();
            boolean bypass = false;
            boolean continuar = true;
            while (continuar) {
                try {
                    encuestaRealizable.setBypassActividadPrevia(bypass);
                    preguntasEncuesta = encuestaRealizable.realizarActividad();
                    continuar = false;
                } catch (ActividadPreviaException e) {
                    System.out.println(e.getMessage());
                    int respuestaInt = consolaEstudiante.pedirInt("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
                    if (respuestaInt == 2) {
                        return;
                    } else {
                        System.out.println("Continuando con la actividad...");
                        bypass = true;
                    }
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
                String respuesta = consolaEstudiante.pedirString("Ingrese su respuesta: ");
                PreguntaAbiertaRealizable preguntaRealizada = new PreguntaAbiertaRealizable(respuesta, pregunta);
                respuestasEstudiante.add(preguntaRealizada);
            }
            encuestaRealizable.enviarActividad(respuestasEstudiante);
            System.out.println("Encuesta realizada exitosamente.");
            recomendarSeguimiento(actividadBase);
        }
    
       public void realizarExamen(Examen actividadBase) {
        ExamenRealizable examenRealizable = new ExamenRealizable(consolaEstudiante.getEstudiante(), actividadBase);
        ArrayList preguntasExamen = new ArrayList();
        boolean bypass = false;
        boolean continuar = true;
        while (continuar) {
            try {
                examenRealizable.setBypassActividadPrevia(bypass);
                preguntasExamen = examenRealizable.realizarActividad();
                continuar = false;
            } catch (ActividadPreviaException e) {
                System.out.println(e.getMessage());
                int respuestaInt = consolaEstudiante.pedirInt("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
                if (respuestaInt == 2) {
                    return;
                } else {
                    System.out.println("Continuando con la actividad...");
                    bypass = true;
                }
            }
        }
        ArrayList preguntasRealizadas = new ArrayList();
        System.out.println("Realizando examen...");
        for (int i = 0; i < preguntasExamen.size(); i++) {
            PreguntaAbierta pregunta = (PreguntaAbierta) preguntasExamen.get(i);
            System.out.println("Pregunta " + i + ": " + pregunta.getEnunciado());
            System.out.println("A continuación, ingrese su respuesta: ");
            String respuesta = consolaEstudiante.pedirString("Ingrese su respuesta: ");
            PreguntaAbiertaRealizable preguntaRealizable = new PreguntaAbiertaRealizable(respuesta, pregunta);
            preguntasRealizadas.add(preguntaRealizable);
        }
        examenRealizable.enviarActividad(preguntasRealizadas);
        recomendarSeguimiento(actividadBase);
    }
        public void realizarQuiz(Quiz actividadBase) {
        QuizRealizable quizRealizable = new QuizRealizable(consolaEstudiante.getEstudiante(), actividadBase);
        ArrayList<PreguntaCerrada> preguntasQuiz = new ArrayList();
        boolean bypass = false;
        boolean continuar = true;
        while (continuar) {
            try {
                quizRealizable.setBypassActividadPrevia(bypass);
                preguntasQuiz = quizRealizable.realizarActividad();
                continuar = false;
            } catch (ActividadPreviaException e) {
                System.out.println(e.getMessage());
                int respuestaInt = consolaEstudiante.pedirInt("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
                if (respuestaInt == 2) {
                    return;
                } else {
                    System.out.println("Continuando con la actividad...");
                    bypass = true;
                }
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
            int respuesta = consolaEstudiante.pedirInt("Ingrese el número de la respuesta correcta:");
            PreguntaCerradaRealizable preguntaRealizable = new PreguntaCerradaRealizable(pregunta, opciones[respuesta - 1]);
            preguntasRealizadas.add(preguntaRealizable);
            if (preguntaRealizable.verificarOpcion(opciones[respuesta - 1])) {
                quizRealizable.incCorrectas();
            }
        }
        quizRealizable.enviarActividad(preguntasRealizadas);
        recomendarSeguimiento(actividadBase);
    }
    
        public void realizarRecurso(RecursoEducativo actividadBase) {
            RecursoRealizable recursoRealizable = new RecursoRealizable(actividadBase, consolaEstudiante.getEstudiante());
            ArrayList respuestas = new ArrayList();
            boolean bypass = false;
            boolean continuar = true;
            while (continuar) {
                try {
                    recursoRealizable.setBypassActividadPrevia(bypass);
                    respuestas = recursoRealizable.realizarActividad();
                    continuar = false;
                } catch (ActividadPreviaException e) {
                    System.out.println(e.getMessage());
                    int respuestaInt = consolaEstudiante.pedirInt("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
                    if (respuestaInt == 2) {
                        return;
                    } else {
                        System.out.println("Continuando con la actividad...");
                        bypass = true;
                    }
                }
            }
            System.out.println("Realizando recurso...");
            System.out.println("Titulo del recurso: " + actividadBase.getTitulo());
            System.out.println("Descripcion del recurso: " + actividadBase.getDescripcion());
            System.out.println("Duracion esperada: " + actividadBase.getDuracionEsperada());
            System.out.println("Tipo de recurso a consultar: " + actividadBase.getTipoRecurso());
            System.out.println("URL del recurso: " + actividadBase.getUrl());
            System.out.println("¿Desea entregar el recurso?");
            String respuesta = consolaEstudiante.pedirString("Ingrese S para entregar el recurso o N para no entregarlo: ");
            if (respuesta.equalsIgnoreCase("S")) {
                recursoRealizable.enviarActividad(respuestas);
            }
            recomendarSeguimiento(actividadBase);
        }
       public void realizarTarea(Tarea actividadBase) {
        TareaRealizable tareaRealizable = new TareaRealizable(actividadBase, consolaEstudiante.getEstudiante());
        boolean bypass = false;
        boolean continuar = true;
        while (continuar) {
            try {
                tareaRealizable.setBypassActividadPrevia(bypass);
                tareaRealizable.realizarActividad();
                continuar = false;
            } catch (ActividadPreviaException e) {
                System.out.println(e.getMessage());
                int respuestaInt = consolaEstudiante.pedirInt("¿Desea realizar la actividad de todas formas? (1. Sí, 2. No)");
                if (respuestaInt == 2) {
                    return;
                } else {
                    System.out.println("Continuando con la actividad...");
                    bypass = true;
                }
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
                    String respuesta = consolaEstudiante.pedirString("¿Desea ver una pista? (S/N)");
                    if (respuesta.equalsIgnoreCase("S")) {
                        System.out.println("Pista: " + seccion.getPista());
                    }
                }
                if (seccion.getResultadoEsperado() != null) {
                    String resultado = consolaEstudiante.pedirString("Ingrese el resultado esperado: ");
                    if (resultado.equals(seccion.getResultadoEsperado())) {
                        System.out.println("Resultado correcto");
                    } else {
                        System.out.println("Resultado incorrecto");
                        System.out.println("El resultado esperado es: " + seccion.getResultadoEsperado());
                    }
                }
                System.out.println("Desea continuar con la siguiente seccion? (S/N)");
                String respuesta = consolaEstudiante.pedirString("Ingrese S para continuar o N para salir: ");
                if (respuesta.equalsIgnoreCase("N")) {
                    return;
                }
                i++;
            }
        }
            System.out.println("¿Ha enviado la tarea? (S/N)");
            String respuesta = consolaEstudiante.pedirString("Ingrese S para enviar la tarea o N para no enviarla: ");
            if (respuesta.equalsIgnoreCase("S")) {
                tareaRealizable.enviarActividad(new ArrayList());
            }
        recomendarSeguimiento(actividadBase);
        }
    }
