package lprs.logica.contenido.pregunta;

public class PreguntaAbierta extends Pregunta {
    private Boolean calificado;

    public PreguntaAbierta(String enunciado) {
        super(enunciado);
        this.calificado = false;
    }

    public Boolean getCalificado() {
        return calificado;
    }

    public void setCalificado(Boolean calificado) {
        this.calificado = calificado;
    }
}
