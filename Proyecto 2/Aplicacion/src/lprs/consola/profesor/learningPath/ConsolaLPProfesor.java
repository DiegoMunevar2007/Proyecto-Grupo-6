package lprs.consola.profesor.learningPath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import lprs.exceptions.ClonarLPException;
import lprs.exceptions.NoLearningPathsException;
import lprs.logica.cuentas.Profesor;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class ConsolaLPProfesor {

    private LPRS lprsActual;
    private ConsolaProfesorLP consolaProfesorLP;

    public ConsolaLPProfesor(LPRS lprsActual, ConsolaProfesorLP consolaProfesorLP) {
        this.lprsActual = lprsActual;
        this.consolaProfesorLP = consolaProfesorLP;
    }

    public void mostrarConsolaLP() {
        Scanner lectura = consolaProfesorLP.getLectura();
        System.out.println("-------------------------------------");
        String[] opciones = { "Ver mis Learning Paths hechos", "Ver los learning Paths disponibles",
                "Crear un learning path", "Volver" };
        boolean salir = false;
        while (!salir) {
            consolaProfesorLP.mostrarOpciones(opciones.length, opciones);
            int opcion = consolaProfesorLP.pedirInt("Seleccione una opción: ");
            if (opcion == 1) {
                mostrarLearningPathsHechos();
            } else if (opcion == 2) {
                try {
                    mostrarLearningPathsDisponibles();
                } catch (NoLearningPathsException e) {
                    System.out.println(e.getMessage());
                    mostrarConsolaLP();
                }
            } else if (opcion == 3) {
                crearLearningPath();
            } else if (opcion == 4) {
                salir=true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
                mostrarConsolaLP();
            }
        }
    }

    public void crearLearningPath() {
        Profesor profesor = consolaProfesorLP.getProfesor();
        String titulo = consolaProfesorLP.pedirString("Ingrese el titulo del Learning Path: ");
        String descripcion = consolaProfesorLP.pedirString("Ingrese la descripcion del Learning Path: ");
        String nivelDificultad = consolaProfesorLP.pedirString("Ingrese el nivel de dificultad del Learning Path: (Principiante, Intermedio, Avanzado)");
        if (!nivelDificultad.equals("Principiante") && !nivelDificultad.equals("Intermedio")
                && !nivelDificultad.equals("Avanzado")) {
            System.out.println("Nivel de dificultad no válido. Por favor, ingrese un nivel de dificultad válido.");
            crearLearningPath();
            return;
        }
        ArrayList<String> objetivos = new ArrayList<>();
        boolean terminado = false;
        while (!terminado) {
            String objetivo = consolaProfesorLP.pedirString("Ingrese un objetivo: ");
            objetivos.add(objetivo);

            String respuesta = consolaProfesorLP.pedirString("¿Desea agregar otro objetivo? (s/n): ");
            if (respuesta.equalsIgnoreCase("n")) {
                terminado = true;
            }
        }
        String ID = profesor.crearLearningPath(titulo, descripcion, nivelDificultad, objetivos);
        System.out.println("El learning path se ha creado exitosamente con el ID: " + ID);
        for (LearningPath lp : lprsActual.getManejadorLP().getLearningPathsDisponibles()) {
            System.out.println(lp.getTitulo());
        }
        return;
    }

    public void mostrarLearningPathsDisponibles() throws  NoLearningPathsException {

        Profesor profesor = consolaProfesorLP.getProfesor();
        List<LearningPath> learningPathsDisponibles = lprsActual.getManejadorLP().getLearningPathsDisponibles();
        if (learningPathsDisponibles.isEmpty()) {
            throw new NoLearningPathsException("No hay Learning Paths disponibles.");
        }
        for (int i = 0; i < learningPathsDisponibles.size(); i++) {
            System.out.println(i + 1 + ". " + learningPathsDisponibles.get(i).getTitulo());
        }
        int opcion = consolaProfesorLP.pedirInt("Seleccione un Learning Path o ingrese 0 para volver: ");
        if (opcion < 0 || opcion > learningPathsDisponibles.size()) {
            System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
            return;

        } else if (opcion == 0) {
            return;
        }
        System.out.println("Esta es la informacion del Learning Path seleccionado: ");
        System.out.println("ID: " + learningPathsDisponibles.get(opcion - 1).getID());
        System.out.println("Titulo: " + learningPathsDisponibles.get(opcion - 1).getTitulo());
        System.out.println("Descripcion: " + learningPathsDisponibles.get(opcion - 1).getDescripcion());
        System.out.println("Nivel de dificultad: " + learningPathsDisponibles.get(opcion - 1).getNivelDificultad());
        System.out.println("Objetivos: ");
        for (String objetivo : learningPathsDisponibles.get(opcion - 1).getObjetivos()) {
            System.out.println(objetivo);
        }
        System.out.println("Duracion: " + learningPathsDisponibles.get(opcion - 1).getDuracion());
        System.out.println("Rating: " + learningPathsDisponibles.get(opcion - 1).getRating());
        System.out.println(
                "Profesor creador: " + learningPathsDisponibles.get(opcion - 1).getProfesorCreador().getUsuario());
        System.out.println("¿Que desea hacer? ");
        String[] opciones = { "Clonar el Learning Path", "Volver" };
        consolaProfesorLP.mostrarOpciones(opciones.length, opciones);
        int opcion2 = consolaProfesorLP.pedirInt("Seleccione una opción: ");
        if (opcion2 == 1) {
            LearningPath lp = learningPathsDisponibles.get(opcion - 1);
            String ID = lp.getID();
            try {
                profesor.clonarLearningPath(ID);
                System.out.println("Learning Path clonado con éxito.");
                mostrarConsolaLP();
            } catch (ClonarLPException e) {
                System.out.println(e.getMessage());
                mostrarLearningPathsDisponibles();
            }
        } else if (opcion2 == 2) {
            mostrarConsolaLP();
        } else {
            System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            mostrarLearningPathsDisponibles();
        }
    }

    public void mostrarLearningPathsHechos() {
        Scanner lectura = consolaProfesorLP.getLectura();
        Profesor profesor = consolaProfesorLP.getProfesor();
        Collection<LearningPath> learningPathsHechos = profesor.getLearningPathsCreados();
        if (learningPathsHechos.isEmpty()) {
            System.out.println("No tienes Learning Paths creados.");
            return;
        }
        int indice = 1;
        for (LearningPath lp : learningPathsHechos) {
            System.out.println(indice + ". " + lp.getTitulo());
            indice++;
        }
        System.out.println("Ingrese el número del Learning Path que desea o ingrese 0 para salir ");
        boolean opcionValida = false;
        int numero =0;
        while (!opcionValida) {
            numero = lectura.nextInt();
            if (numero < 0 || numero > learningPathsHechos.size()) {
                System.out.println("Opción no válida. Por favor, seleccione un Learning Path de la lista.");
                mostrarLearningPathsHechos();
            } else if (numero == 0) {
                mostrarConsolaLP();
                opcionValida = true;
            } else {
                opcionValida = true;
            }
        }
        LearningPath lp = (LearningPath) learningPathsHechos.toArray()[numero - 1];

        System.out.println("¿Que desea hacer?");
        String[] opciones = { "Ver informacion detallada del Learning Path", "Modificar un Learning Path",
                "Eliminar un Learning Path", "Volver" };
        opcionValida = false;
        while (!opcionValida) {
            consolaProfesorLP.mostrarOpciones(opciones.length, opciones);
            int opcion = lectura.nextInt();
            if (opcion == 1) {
                mostrarLearningPathCompleto(lp);
                opcionValida = true;
            } else if (opcion == 2) {
                modificarLearningPath(lp);
                opcionValida = true;
            } else if (opcion == 3) {
                profesor.eliminarLearningPath(lp.getID());
                opcionValida = true;
            } else if (opcion == 4) {
                opcionValida = true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            }
        }

    }

    public void modificarLearningPath(LearningPath lp) {
        Scanner lectura = consolaProfesorLP.getLectura();
        Profesor profesor = consolaProfesorLP.getProfesor();
        System.out.println("¿Qué desea modificar?");
        String[] opciones = { "Título", "Descripción", "Nivel de dificultad", "Objetivos", "Volver" };

        boolean opcionValida = false;
        int opcion = 0;
        while (!opcionValida) {
            consolaProfesorLP.mostrarOpciones(opciones.length, opciones);
            opcion = consolaProfesorLP.pedirInt("Seleccione una opción: ");
            if (opcion == 1) {
                String titulo = consolaProfesorLP.pedirString("Ingrese el nuevo título: ");
                String descripcion = lp.getDescripcion();
                String nivelDificultad = lp.getNivelDificultad();
                ArrayList<String> objetivos = lp.getObjetivos();
                profesor.modificarLearningPath(lp.getID(), titulo, descripcion, nivelDificultad, objetivos);
                mostrarLearningPathsHechos();
            } else if (opcion == 2) {
                String descripcion = consolaProfesorLP.pedirString("Ingrese la nueva descripción: ");
                String titulo = lp.getTitulo();
                String nivelDificultad = lp.getNivelDificultad();
                ArrayList<String> objetivos = lp.getObjetivos();
                profesor.modificarLearningPath(lp.getID(), titulo, descripcion, nivelDificultad, objetivos);
                mostrarLearningPathsHechos();
            } else if (opcion == 3) {
                String nivelDificultad = consolaProfesorLP.pedirString("Ingrese el nuevo nivel de dificultad: ");
                String titulo = lp.getTitulo();
                String descripcion = lp.getDescripcion();
                ArrayList<String> objetivos = lp.getObjetivos();
                profesor.modificarLearningPath(lp.getID(), titulo, descripcion, nivelDificultad, objetivos);
                mostrarLearningPathsHechos();
            } else if (opcion == 4) {
                ArrayList<String> objetivos = new ArrayList<>();
                boolean terminado = false;
                while (!terminado) {
                    System.out.println("Ingrese un objetivo: ");
                    String objetivo = consolaProfesorLP.pedirString("Ingrese un objetivo: ");
                    objetivos.add(objetivo);

                    System.out.println("¿Desea agregar otro objetivo? (s/n): ");
                    String respuesta = consolaProfesorLP.pedirString("¿Desea agregar otro objetivo? (s/n): ");
                    if (respuesta.equalsIgnoreCase("n")) {
                        terminado = true;
                    }
                }
                String titulo = lp.getTitulo();
                String descripcion = lp.getDescripcion();
                String nivelDificultad = lp.getNivelDificultad();
                profesor.modificarLearningPath(lp.getID(), titulo, descripcion, nivelDificultad, objetivos);
                return;
            } else if (opcion == 5) {
                opcionValida = true;
            } else {
                System.out.println("Opción no válida. Por favor, seleccione una opción de la lista.");
            }
        }
    }

    public void mostrarLearningPathCompleto(LearningPath lp) {
        System.out.println("Titulo: " + lp.getTitulo());
        System.out.println("Descripcion: " + lp.getDescripcion());
        System.out.println("Nivel de dificultad: " + lp.getNivelDificultad());
        System.out.println("Objetivos: ");
        for (String objetivo : lp.getObjetivos()) {
            System.out.println(objetivo);
        }
        System.out.println("Duracion: " + lp.getDuracion());
        System.out.println("Rating: " + lp.getRating());
    }

}
