package lprs.cuentas;

import java.util.ArrayList;
import java.util.HashMap;

import lprs.learningPaths.LearningPath;

public class Profesor extends Usuario {
    private HashMap<String, LearningPath> learningPathsCreados;

    /**
     * Constructor para crear un objeto Profesor.
     *
     * @param usuario     el nombre de usuario del profesor
     * @param contrasenia la contraseña del profesor
     */
    public Profesor(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.tipo = PROFESOR;
        learningPathsCreados = new HashMap<String, LearningPath>();
    }

    /**
     * Crea una nueva ruta de aprendizaje y la añade a la lista y al hash de rutas
     * de aprendizaje.
     *
     * @param titulo          el título de la ruta de aprendizaje
     * @param descripcion     la descripción de la ruta de aprendizaje
     * @param nivelDificultad el nivel de dificultad de la ruta de aprendizaje
     * @param objetivos       una lista de los objetivos de la ruta de aprendizaje
     * @param duracion        la duración de la ruta de aprendizaje
     * @param rating          la calificación de la ruta de aprendizaje
     */
    public void crearLearningPath(String titulo, String descripcion, String nivelDificultad,
            ArrayList<String> objetivos, int duracion, float rating) {
        LearningPath lP = new LearningPath(titulo, descripcion, nivelDificultad, objetivos, duracion, rating, this);
        LearningPath.addLearningPath(lP);
        ;
    }

    /**
     * Modifica una ruta de aprendizaje existente.
     *
     * @param ID              el ID de la ruta de aprendizaje a modificar
     * @param titulo          el nuevo título de la ruta de aprendizaje
     * @param descripcion     la nueva descripción de la ruta de aprendizaje
     * @param nivelDificultad el nuevo nivel de dificultad de la ruta de aprendizaje
     * @param objetivos       la nueva lista de objetivos de la ruta de aprendizaje
     * @param duracion        la nueva duración de la ruta de aprendizaje
     * @param rating          la nueva calificación de la ruta de aprendizaje
     */
    public void modificarLearningPath(String ID, String titulo, String descripcion, String nivelDificultad,
            ArrayList<String> objetivos) {

        LearningPath lP = LearningPath.getLearningPath(ID);
        if (lP.getProfesorCreador() != this) {
            System.out.println("No tienes permiso para modificar esta ruta de aprendizaje.");
            return;
        }
        lP.editarLearningPath(titulo, descripcion, nivelDificultad, objetivos, this);
    }

    /**
     * Elimina una ruta de aprendizaje existente.
     *
     * @param ID el ID de la ruta de aprendizaje a eliminar
     */
    public void eliminarLearningPath(String ID) {
        LearningPath lP = LearningPath.getLearningPath(ID);
        if (lP.getProfesorCreador() != this) {
            System.out.println("No tienes permiso para eliminar esta ruta de aprendizaje.");
            return;
        }
        ArrayList<Estudiante> estudiantes = lP.getEstudiantesInscritos();
        for (Estudiante estudiante : estudiantes) {
            estudiante.eliminarLearningPath(ID);
        }
        lP.eliminarLearningPath();
        learningPathsCreados.remove(ID);
    }

    /**
     * Clona una ruta de aprendizaje existente.
     * 
     * @param ID el ID de la ruta de aprendizaje a clonar
     */
    public void clonarLearningPath(String ID) {
        LearningPath lP = LearningPath.getLearningPath(ID);
        LearningPath lPClon = new LearningPath(lP, this);
        LearningPath.addLearningPath(lPClon);
        learningPathsCreados.put(lPClon.getID(), lPClon);
    }
}
