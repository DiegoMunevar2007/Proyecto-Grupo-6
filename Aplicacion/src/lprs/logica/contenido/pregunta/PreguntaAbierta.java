package lprs.logica.contenido.pregunta;

public class PreguntaAbierta extends Pregunta {
    private String respuesta;
    private Boolean calificado;

    public PreguntaAbierta(String enunciado, String respuesta) {
        super(enunciado);
        this.respuesta = respuesta;
        this.calificado = false;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Boolean getCalificado() {
        return calificado;
    }

    public void setCalificado(Boolean calificado) {
        this.calificado = calificado;
    }
}
