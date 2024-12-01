package lprs.logica.cuentas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import lprs.exceptions.ClonarLPException;
import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.learningPath.LearningPath;
import lprs.principal.LPRS;

public class Profesor extends Usuario {
    private final String PROFESOR = "Profesor";
    private HashMap<String, LearningPath> learningPathsCreados;
    private ArrayList<LearningPath> learningPathsCreadosLista;
    private ArrayList<ActividadRealizable> actividadesPendientes;

    /**
     * Constructor para crear un objeto Profesor.
     *
     * @param usuario     el nombre de usuario del profesor
     * @param contrasenia la contraseña del profesor
     */
    public Profesor(String usuario, String contrasenia, LPRS lprsActual) {
        super(usuario, contrasenia, lprsActual);
        this.tipo = PROFESOR;
        learningPathsCreados = new HashMap<String, LearningPath>();
        actividadesPendientes = new ArrayList<ActividadRealizable>();
        learningPathsCreadosLista = new ArrayList<LearningPath>();
    }

    /**
     * Crea una nueva ruta de aprendizaje y la añade a la lista y al hash de rutas
     * de aprendizaje.
     *
     * @param titulo          el título de la ruta de aprendizaje
     * @param descripcion     la descripción de la ruta de aprendizaje
     * @param nivelDificultad el nivel de dificultad de la ruta de aprendizaje
     * @param objetivos       una lista de los objetivos de la ruta de aprendizaje
     */
    public String crearLearningPath(String titulo, String descripcion, String nivelDificultad,
            ArrayList<String> objetivos, ArrayList<String> keyWords) {
        String ID = lprsActual.getManejadorLP().generarID();
        LearningPath lP = new LearningPath(ID, titulo, descripcion, nivelDificultad, objetivos, this, lprsActual, keyWords);
        lprsActual.getManejadorLP().addLearningPath(lP);
        learningPathsCreados.put(lP.getID(), lP);
        learningPathsCreadosLista.add(lP);
        return lP.getID();
    }

    /**
     * Modifica una ruta de aprendizaje existente.
     *
     * @param ID              el ID de la ruta de aprendizaje a modificar
     * @param titulo          el nuevo título de la ruta de aprendizaje
     * @param descripcion     la nueva descripción de la ruta de aprendizaje
     * @param nivelDificultad el nuevo nivel de dificultad de la ruta de aprendizaje
     * @param objetivos       la nueva lista de objetivos de la ruta de aprendizaje

     */
    public void modificarLearningPath(String ID, String titulo, String descripcion, String nivelDificultad,
            ArrayList<String> objetivos) {

        LearningPath lP = learningPathsCreados.get(ID);
        if (lP.getProfesorCreador() != this) {
            System.out.println("No tienes permiso para modificar esta ruta de aprendizaje.");
            return;
        }
        try {
            lP.editarLearningPath(titulo, descripcion, nivelDificultad, objetivos, this);
        } catch (Exception e) {
            System.out.println("Clona el learning path y modifica la copia.");
        }
    }

    /**
     * Elimina una ruta de aprendizaje existente.
     *
     * @param ID el ID de la ruta de aprendizaje a eliminar
     */
    public void eliminarLearningPath(String ID) {

        LearningPath lP = lprsActual.getManejadorLP().getLearningPath(ID);
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
    public String clonarLearningPath(String ID) throws ClonarLPException {
        if (lprsActual.getManejadorLP().getLearningPath(ID) == null) {
            System.out.println("No existe un learning path con ese ID.");
            return "";
        } else if (learningPathsCreados.containsKey(ID)) {
            throw new ClonarLPException(lprsActual.getManejadorLP().getLearningPath(ID));
        }
        LearningPath lP = lprsActual.getManejadorLP().getLearningPath(ID);
        String IDClon = lprsActual.getManejadorLP().generarID();
        LearningPath lPClon = new LearningPath(IDClon, lP, this, lP.obtenerFecha());
        lprsActual.getManejadorLP().addLearningPath(lPClon);
        learningPathsCreadosLista.add(lPClon);
        learningPathsCreados.put(lPClon.getID(), lPClon);
        return lPClon.getID();
    }

    public Collection<LearningPath> getLearningPathsCreados() {
        return learningPathsCreados.values();
    }

    public ArrayList<ActividadRealizable> getActividadesPendientes() {
        return actividadesPendientes;
    }

    public LearningPath getLearningPathCreado(String ID) {
        return learningPathsCreados.get(ID);
    }

    public void setActividadesPendientes(ArrayList<ActividadRealizable> actividadesPendientes) {
        this.actividadesPendientes = actividadesPendientes;
    }

    public String getPROFESOR() {
        return PROFESOR;
    }

    public void setLearningPathsCreados(HashMap<String, LearningPath> learningPathsCreados) {
        this.learningPathsCreados = learningPathsCreados;
    }

    public void addActividadPendiente(ActividadRealizable actividad) {
        actividadesPendientes.add(actividad);
    }

    public ArrayList<LearningPath> getLearningPathsCreadosLista() {
        return learningPathsCreadosLista;
    }

    public void setLearningPathsCreadosLista(ArrayList<LearningPath> learningPathsCreadosLista) {
        this.learningPathsCreadosLista = learningPathsCreadosLista;
    }

    public void actividadCalificada(ActividadRealizable actividadCalificada) {
        actividadesPendientes.remove(actividadCalificada);
    }

}
