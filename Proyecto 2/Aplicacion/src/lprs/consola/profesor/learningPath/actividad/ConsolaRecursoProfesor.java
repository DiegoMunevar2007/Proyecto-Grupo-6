package lprs.consola.profesor.learningPath.actividad;

import lprs.logica.contenido.RecursoEducativo;
import lprs.logica.learningPath.LearningPath;
import lprs.consola.profesor.learningPath.ConsolaActividadProfesor;
import java.util.Scanner;

public class ConsolaRecursoProfesor {

    private ConsolaActividadProfesor consolaProfesor;
    public ConsolaRecursoProfesor(ConsolaActividadProfesor consolaProfesor) {
        this.consolaProfesor = consolaProfesor;
    }
    public void crearRecursoEducativo(LearningPath lp) {
        Scanner lectura = consolaProfesor.getLectura();
        String titulo = consolaProfesor.pedirTitulo();
        String descripcion = consolaProfesor.pedirDescripcion();
        String objetivo = consolaProfesor.pedirObjetivo();
        int duracion = consolaProfesor.pedirDuracion();
        boolean obligatoria = consolaProfesor.pedirObligatoria();
        String fechaEntrega = consolaProfesor.pedirFechaEntrega();
        System.out.println("¿Que tipo de recurso educativo es?");
        String[] opciones = { "Video", "Libro", "Sitio web", "PDF" };
        consolaProfesor.getConsolaProfesor().mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        lectura.nextLine();
        if (opcion < 1 || opcion > opciones.length) {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            crearRecursoEducativo(lp);
            return;
        }
        String tipo = opciones[opcion - 1];
        System.out.println("Ingrese el link del recurso educativo: ");
        String link = lectura.nextLine();
        RecursoEducativo recurso = lp.crearRecursoEducativo(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega, tipo, link);
        consolaProfesor.aniadirPrerequisitos(lp,recurso);
        System.out.println("Recurso educativo creado con éxito.");
        consolaProfesor.aniadirActividadesSeguimiento(lp, recurso);
        consolaProfesor.mostrarConsolaActividad();
    }

    public void modificarRecursoEducativo(RecursoEducativo recurso) {
        Scanner lectura = consolaProfesor.getLectura();
        System.out.println("¿Qué desea modificar?");
        String[] opciones = {"Título", "Descripción", "Objetivo", "Duración", "Obligatoria", "Fecha de entrega", "Tipo", "Link", "Salir"};
        consolaProfesor.getConsolaProfesor().mostrarOpciones(opciones.length, opciones);
        int opcion = lectura.nextInt();
        lectura.nextLine();
        if (opcion == 1) {
            recurso.setTitulo(consolaProfesor.pedirTitulo());
        } else if (opcion == 2) {
            recurso.setDescripcion(consolaProfesor.pedirDescripcion());
        } else if (opcion == 3) {
            recurso.setObjetivo(consolaProfesor.pedirObjetivo());
        } else if (opcion == 4) {
            recurso.setDuracionEsperada(consolaProfesor.pedirDuracion());
        } else if (opcion == 5) {
            recurso.setObligatoria(consolaProfesor.pedirObligatoria());
        } else if (opcion == 6) {
            recurso.setFechaLimite(consolaProfesor.pedirFechaEntrega());
        } else if (opcion == 7) {
            System.out.println("¿Qué tipo de recurso educativo es?");
            String[] opcionesTipo = {"Video", "Libro", "Sitio web", "PDF"};
            consolaProfesor.getConsolaProfesor().mostrarOpciones(opcionesTipo.length, opcionesTipo);
            int opcionTipo = lectura.nextInt();
            lectura.nextLine();
            if (opcionTipo < 1 || opcionTipo > opcionesTipo.length) {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
                modificarRecursoEducativo(recurso);
                return;
            }

            recurso.setTipoRecurso(opcionesTipo[opcionTipo - 1]);
        } else if (opcion == 8) {
            System.out.println("Ingrese el link del recurso educativo: ");
            recurso.setUrl(lectura.nextLine());
        } else if (opcion == 9) {
            return;
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            modificarRecursoEducativo(recurso);
        }
    }
    public void verRecurso(RecursoEducativo recurso) {
        System.out.println("Título: " + recurso.getTitulo());
        System.out.println("Descripción: " + recurso.getDescripcion());
        System.out.println("Objetivo: " + recurso.getObjetivo());
        System.out.println("Duración: " + recurso.getDuracionEsperada());
        System.out.println("Obligatoria: " + recurso.isObligatoria());
        System.out.println("Fecha de entrega: " + recurso.getFechaLimite());
        System.out.println("Tipo: " + recurso.getTipoRecurso());
        System.out.println("Link: " + recurso.getUrl());
        consolaProfesor.mostrarConsolaActividad();
    }
}
