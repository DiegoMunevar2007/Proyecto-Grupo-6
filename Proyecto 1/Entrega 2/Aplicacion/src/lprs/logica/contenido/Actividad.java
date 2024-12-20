package lprs.logica.contenido;

import java.io.Serializable;
import java.util.ArrayList;

import lprs.logica.contenido.realizable.ActividadRealizable;
import lprs.logica.cuentas.Estudiante;
import lprs.logica.learningPath.LearningPath;

public abstract class Actividad implements Serializable{
    protected static int actividadesCreadas = 0;
    protected String numeroActividad;
    protected String titulo;
    protected String descripcion;
    protected String objetivo;
    protected String nivelDificultad;
    protected int duracionEsperada;
    protected boolean obligatoria;
    protected String fechaLimite;
    protected ArrayList<Resenia> resenias;
    protected ArrayList<Actividad> actividadesPrevias;
    protected ArrayList<Actividad> actividadesSeguimiento;
    protected LearningPath learningPathAsignado;
    protected ArrayList<ActividadRealizable> actividadesRealizablesCreadas;

    public Actividad(String titulo, String descripcion, String objetivo, int duracionEsperada,
            boolean obligatoria, String fechaLimite, LearningPath lP, String dificultad) {
        actividadesCreadas++;
        this.numeroActividad = Integer.toString(actividadesCreadas);
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.objetivo = objetivo;
        this.duracionEsperada = duracionEsperada;
        this.obligatoria = obligatoria;
        this.fechaLimite = fechaLimite;
        this.resenias = new ArrayList<Resenia>();
        this.actividadesPrevias = new ArrayList<Actividad>();
        this.actividadesSeguimiento = new ArrayList<Actividad>();
        this.learningPathAsignado = lP;
        this.nivelDificultad = dificultad;
        this.actividadesRealizablesCreadas = new ArrayList<ActividadRealizable>();
    }

    public Actividad(Actividad actividad) {
        actividadesCreadas++;
        this.numeroActividad = Integer.toString(actividadesCreadas);
        this.titulo = actividad.getTitulo();
        this.descripcion = actividad.getDescripcion();
        this.objetivo = actividad.getObjetivo();
        this.duracionEsperada = actividad.getDuracionEsperada();
        this.obligatoria = actividad.isObligatoria();
        this.fechaLimite = actividad.getFechaLimite();

        this.resenias = actividad.getResenias();
        this.actividadesPrevias = actividad.getActividadesPrevias();
        this.actividadesSeguimiento = actividad.getActividadesSeguimiento();
        this.learningPathAsignado = actividad.getLearningPathAsignado();
        this.nivelDificultad = actividad.getNivelDificultad();
        this.actividadesRealizablesCreadas = actividad.getActividadesRealizablesCreadas();

    }

    public abstract ActividadRealizable crearActividadRealizable(Estudiante estudiante);

    public ArrayList<ActividadRealizable> getActividadesRealizablesCreadas() {
        return actividadesRealizablesCreadas;
    }

    /**
     * Obtiene el número de la actividad.
     *
     * @return el número de la actividad
     */
    public String getNumeroActividad() {
        return numeroActividad;
    }

    /**
     * Obtiene el título de la actividad.
     *
     * @return el título de la actividad
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Obtiene el nivel de dificultad de la actividad.
     *
     * @return el nivel de dificultad de la actividad
     */
    public String getNivelDificultad() {
        return nivelDificultad;
    }

    /**
     * Obtiene la descripción de la actividad.
     *
     * @return la descripción de la actividad
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Obtiene el objetivo de la actividad.
     *
     * @return el objetivo de la actividad
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * Obtiene la duración esperada de la actividad.
     *
     * @return la duración esperada de la actividad
     */
    public int getDuracionEsperada() {
        return duracionEsperada;
    }

    /**
     * Indica si la actividad es obligatoria.
     *
     * @return true si la actividad es obligatoria, false en caso contrario
     */
    public boolean isObligatoria() {
        return obligatoria;
    }

    /**
     * Obtiene la Date límite de la actividad.
     *
     * @return la Date límite de la actividad
     */
    public String getFechaLimite() {
        return fechaLimite;
    }

    /**
     * Obtiene las reseñas de la actividad.
     *
     * @return una lista de las reseñas de la actividad
     */
    public ArrayList<Resenia> getResenias() {
        return resenias;
    }

    /**
     * Obtiene las actividades previas a esta actividad.
     *
     * @return una lista de las actividades previas
     */
    public ArrayList<Actividad> getActividadesPrevias() {
        return actividadesPrevias;
    }

    /**
     * Obtiene las actividades de seguimiento de esta actividad.
     *
     * @return una lista de las actividades de seguimiento
     */
    public ArrayList<Actividad> getActividadesSeguimiento() {
        return actividadesSeguimiento;
    }

    /**
     * Establece el título de la actividad.
     *
     * @param titulo el nuevo título de la actividad
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Establece el nivel de dificultad de la actividad.
     *
     * @param dificultad el nuevo nivel de dificultad de la actividad
     */
    public void setNivelDificultad(String dificultad) {
        this.nivelDificultad = dificultad;
    }

    /**
     * Establece la descripción de la actividad.
     *
     * @param descripcion la nueva descripción de la actividad
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Establece el objetivo de la actividad.
     *
     * @param objetivo el nuevo objetivo de la actividad
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    /**
     * Establece la duración esperada de la actividad.
     *
     * @param duracionEsperada la nueva duración esperada de la actividad
     */
    public void setDuracionEsperada(int duracionEsperada) {
        this.duracionEsperada = duracionEsperada;
    }

    /**
     * Establece si la actividad es obligatoria.
     *
     * @param obligatoria true si la actividad es obligatoria, false en caso
     *                    contrario
     */
    public void setObligatoria(boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    /**
     * Establece la Date límite de la actividad.
     *
     * @param fechaLimite la nueva Date límite de la actividad
     */
    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }

    /**
     * Establece las reseñas de la actividad.
     *
     * @param resenias la nueva lista de reseñas de la actividad
     */
    public void setResenias(ArrayList<Resenia> resenias) {
        this.resenias = resenias;
    }

    /**
     * Establece las actividades previas a esta actividad.
     *
     * @param actividadesPrevias la nueva lista de actividades previas
     */
    public void setActividadesPrevias(ArrayList<Actividad> actividadesPrevias) {
        this.actividadesPrevias = actividadesPrevias;
    }

    /**
     * Establece las actividades de seguimiento de esta actividad.
     *
     * @param actividadesSeguimiento la nueva lista de actividades de seguimiento
     */
    public void setActividadesSeguimiento(ArrayList<Actividad> actividadesSeguimiento) {
        this.actividadesSeguimiento = actividadesSeguimiento;
    }

    /**
     * Añade una reseña a la actividad.
     *
     * @param resenia la reseña a añadir
     */
    public void addResenia(Resenia resenia) {
        resenias.add(resenia);
        learningPathAsignado.cambiarRating(resenia.getRating());
    }

    /**
     * Añade una actividad previa a esta actividad.
     *
     * @param actividadPrevia la actividad previa a añadir
     */
    public void addActividadPrevia(Actividad actividadPrevia) {
        actividadesPrevias.add(actividadPrevia);
    }

    /**
     * Añade una actividad de seguimiento a esta actividad.
     *
     * @param actividadSeguimiento la actividad de seguimiento a añadir
     */
    public void addActividadSeguimiento(Actividad actividadSeguimiento) {
        actividadesSeguimiento.add(actividadSeguimiento);
    }

    /**
     * Elimina una actividad previa de esta actividad.
     *
     * @param actividadPrevia la actividad previa a eliminar
     */
    public void removeActividadPrevia(Actividad actividadPrevia) {
        actividadesPrevias.remove(actividadPrevia);
    }

    /**
     * Elimina una actividad de seguimiento de esta actividad.
     *
     * @param actividadSeguimiento la actividad de seguimiento a eliminar
     */
    public void removeActividadSeguimiento(Actividad actividadSeguimiento) {
        actividadesSeguimiento.remove(actividadSeguimiento);
    }

    public static int getActividadesCreadas() {
        return actividadesCreadas;
    }

    public static void setActividadesCreadas(int actividadesCreadas) {
        Actividad.actividadesCreadas = actividadesCreadas;
    }

    public LearningPath getLearningPathAsignado() {
        return learningPathAsignado;
    }

    public void setLearningPathAsignado(LearningPath learningPathAsignado) {
        this.learningPathAsignado = learningPathAsignado;
    }

    public void setNumeroActividad(String numeroActividad) {
        this.numeroActividad = numeroActividad;
    }

}