package lprs.consola.profesor.learningPath.actividad;

import lprs.consola.profesor.learningPath.ConsolaActividadProfesor;
import lprs.logica.contenido.Tarea;
import lprs.logica.learningPath.LearningPath;
import lprs.logica.contenido.Seccion;
import java.util.Scanner;

public class ConsolaTareaProfesor {

    private final ConsolaActividadProfesor consolaProfesor;

    public ConsolaTareaProfesor(ConsolaActividadProfesor consolaP) {
        this.consolaProfesor = consolaP;
    }

    public void crearTarea(LearningPath lp) {
        Tarea tareaCreada = crearTareaBase(lp);
        if (deseaAnadirSecciones()) {
            anadirSeccionesATarea(tareaCreada);
        }
        consolaProfesor.aniadirPrerequisitos(lp, tareaCreada);
        consolaProfesor.aniadirActividadesSeguimiento(lp, tareaCreada);
        System.out.println("Tarea creada con éxito.");
    }

    private Tarea crearTareaBase(LearningPath lp) {
        String titulo = consolaProfesor.pedirTitulo();
        String descripcion = consolaProfesor.pedirDescripcion();
        String objetivo = consolaProfesor.pedirObjetivo();
        int duracion = consolaProfesor.pedirDuracion();
        boolean obligatoria = consolaProfesor.pedirObligatoria();
        String fechaEntrega = consolaProfesor.pedirFechaEntrega();
        return lp.crearTarea(titulo, descripcion, objetivo, duracion, obligatoria, fechaEntrega);
    }

    private boolean deseaAnadirSecciones() {
        String respuesta = consolaProfesor.getConsolaProfesor().pedirString("¿Desea añadir secciones a la tarea? (s/n)");
        return respuesta.equalsIgnoreCase("s");
    }

    private void anadirSeccionesATarea(Tarea tareaCreada ) {
        int numeroSecciones = consolaProfesor.getConsolaProfesor().pedirInt("Ingrese el número de secciones que desea añadir: ");
        for (int i = 0; i < numeroSecciones; i++) {
            anadirSeccion(tareaCreada, i);
        }
    }

    private void anadirSeccion(Tarea tareaCreada, int index) {
        String tituloSeccion = pedirTituloSeccion();
        String descripcionSeccion = pedirDescripcionSeccion();
        String tipoContenido = pedirTipoContenido();
        String contenido = pedirContenido();
        String ejemplo = pedirEjemplo();
        String explicacion = pedirExplicacion();
        String pista = pedirPista();
        String resultadoEsperado = pedirResultadoEsperado();
        tareaCreada.crearSeccion(index, tituloSeccion, tipoContenido, descripcionSeccion, contenido, ejemplo, explicacion, pista, resultadoEsperado);
        System.out.println("Sección creada con éxito.");
    }

    private String pedirTituloSeccion() {
        return consolaProfesor.getConsolaProfesor().pedirString("Ingrese el título de la sección: ");
    }

    private String pedirDescripcionSeccion() {
        return consolaProfesor.getConsolaProfesor().pedirString("Ingrese la descripción de la sección: ");
    }

    private String pedirTipoContenido() {
        return consolaProfesor.getConsolaProfesor().pedirString("Ingrese el tipo de contenido de la sección: ");
    }

    private String pedirContenido() {
        return consolaProfesor.getConsolaProfesor().pedirString("Ingrese el contenido de la sección: ");
    }

    private String pedirEjemplo() {
        String respuesta = consolaProfesor.getConsolaProfesor().pedirString("¿Desea añadir un ejemplo? (s/n)");
        if (respuesta.equalsIgnoreCase("s")) {
            return consolaProfesor.getConsolaProfesor().pedirString("Ingrese el ejemplo: ");
        }
        return null;
    }

    private String pedirExplicacion() {
        String respuesta = consolaProfesor.getConsolaProfesor().pedirString("¿Desea añadir una explicación? (s/n)");
        if (respuesta.equalsIgnoreCase("s")) {
            return consolaProfesor.getConsolaProfesor().pedirString("Ingrese la explicación: ");
        }
        return null;
    }

    private String pedirPista( ) {
        String respuesta = consolaProfesor.getConsolaProfesor().pedirString("¿Desea añadir una pista? (s/n)");
        if (respuesta.equalsIgnoreCase("s")) {
            String pista = consolaProfesor.getConsolaProfesor().pedirString("Ingrese la pista: ");
            return pista;
        }
        return null;
    }

    private String pedirResultadoEsperado() {
        String respuesta = consolaProfesor.getConsolaProfesor().pedirString("¿Desea añadir un resultado esperado? (s/n)");
        if (respuesta.equalsIgnoreCase("s")) {
            String resultadoEsperado = consolaProfesor.getConsolaProfesor().pedirString("Ingrese el resultado esperado: ");
            return resultadoEsperado;
        }
        return null;
    }

    public void modificarTarea(Tarea tarea) {
        System.out.println("Ingrese el número de la tarea que desea modificar: ");

        if (tarea == null) {
            System.out.println("Tarea no encontrada.");
            return;
        }
        String[] opciones = {"Título", "Descripción", "Objetivo", "Duración", "Obligatoria", "Fecha de entrega", "Secciones", "Salir"};
        System.out.println("¿Qué desea modificar?");
        consolaProfesor.getConsolaProfesor().mostrarOpciones(opciones.length, opciones);
        int opcion = consolaProfesor.getConsolaProfesor().pedirInt("Seleccione una opción: ");
        if (opcion == 1) {
            tarea.setTitulo(consolaProfesor.pedirTitulo());
        } else if (opcion == 2) {
            tarea.setDescripcion(consolaProfesor.pedirDescripcion());
        } else if (opcion == 3) {
            tarea.setObjetivo(consolaProfesor.pedirObjetivo());
        } else if (opcion == 4) {
            tarea.setDuracionEsperada(consolaProfesor.pedirDuracion());
        } else if (opcion == 5) {
            tarea.setObligatoria(consolaProfesor.pedirObligatoria());
        } else if (opcion == 6) {
            tarea.setFechaLimite(consolaProfesor.pedirFechaEntrega());
        } else if (opcion == 7) {
            String[] opcionesSecciones = {"Añadir sección", "Editar sección", "Eliminar sección", "Salir"};
            consolaProfesor.getConsolaProfesor().mostrarOpciones(opcionesSecciones.length, opcionesSecciones);
            int opcionSecciones = consolaProfesor.getConsolaProfesor().pedirInt("Seleccione una opción: ");
            if (opcionSecciones == 1) {
                anadirSeccion(tarea, tarea.getSecciones().size());
            } else if (opcionSecciones == 2) {
                editarSeccion(tarea);
            } else if (opcionSecciones == 3) {
                eliminarSeccion(tarea);
            }
        }
        System.out.println("Tarea modificada con éxito.");
    }
    public void editarSeccion(Tarea tarea) {
        for (int i = 0; i < tarea.getSecciones().size(); i++) {
            System.out.println(i + 1 + ". " + tarea.getSecciones().get(i).getTitulo());
        }
        int numeroSeccion = consolaProfesor.getConsolaProfesor().pedirInt("Ingrese el número de la sección que desea editar: ");
        String[] opcionesSeccion = {"Título", "Descripción", "Tipo de contenido", "Contenido", "Ejemplo", "Explicación", "Pista", "Resultado esperado"};
        consolaProfesor.getConsolaProfesor().mostrarOpciones(opcionesSeccion.length, opcionesSeccion);
        int opcionSeccion = consolaProfesor.getConsolaProfesor().pedirInt("Seleccione una opción: ");
        if (opcionSeccion == 1) {
            tarea.getSecciones().get(numeroSeccion - 1).setTitulo(pedirTituloSeccion());
        } else if (opcionSeccion == 2) {
            tarea.getSecciones().get(numeroSeccion - 1).setDescripcion(pedirDescripcionSeccion());
        } else if (opcionSeccion == 3) {
            tarea.getSecciones().get(numeroSeccion - 1).setTipo(pedirTipoContenido());
        } else if (opcionSeccion == 4) {
            tarea.getSecciones().get(numeroSeccion - 1).setContenido(pedirContenido());
        } else if (opcionSeccion == 5) {
            tarea.getSecciones().get(numeroSeccion - 1).setEjemplo(pedirEjemplo());
        } else if (opcionSeccion == 6) {
            tarea.getSecciones().get(numeroSeccion - 1).setExplicacion(pedirExplicacion());
        } else if (opcionSeccion == 7) {
            tarea.getSecciones().get(numeroSeccion - 1).setPista(pedirPista());
        } else if (opcionSeccion == 8) {
            tarea.getSecciones().get(numeroSeccion - 1).setResultadoEsperado(pedirResultadoEsperado());
        }
    }
    public void eliminarSeccion(Tarea tarea) {
        for (int i = 0; i < tarea.getSecciones().size(); i++) {
            System.out.println(i + 1 + ". " + tarea.getSecciones().get(i).getTitulo());
        }
        int numeroSeccion = consolaProfesor.getConsolaProfesor().pedirInt("Ingrese el número de la sección que desea eliminar: ");
        Seccion seccion = tarea.getSecciones().get(numeroSeccion - 1);
        tarea.eliminarSeccion(seccion);
        System.out.println("Sección eliminada con éxito.");
    }
    public void verTarea(Tarea tarea) {
        System.out.println("Título: " + tarea.getTitulo());
        System.out.println("Descripción: " + tarea.getDescripcion());
        System.out.println("Objetivo: " + tarea.getObjetivo());
        System.out.println("Duración: " + tarea.getDuracionEsperada());
        System.out.println("Obligatoria: " + tarea.isObligatoria());
        System.out.println("Fecha de entrega: " + tarea.getFechaLimite());
        System.out.println("Secciones: ");
        for (int i = 0; i < tarea.getSecciones().size(); i++) {
            Seccion seccion = tarea.getSecciones().get(i);
            System.out.println(i + 1 + ". " + seccion.getTitulo());
            System.out.println("Descripción: " + seccion.getDescripcion());
            System.out.println("Tipo de contenido: " + seccion.getTipo());
            System.out.println("Contenido: " + seccion.getContenido());
            System.out.println("Ejemplo: " + seccion.getEjemplo());
            System.out.println("Explicación: " + seccion.getExplicacion());
            System.out.println("Pista: " + seccion.getPista());
            System.out.println("Resultado esperado: " + seccion.getResultadoEsperado());
        }
    }
}
