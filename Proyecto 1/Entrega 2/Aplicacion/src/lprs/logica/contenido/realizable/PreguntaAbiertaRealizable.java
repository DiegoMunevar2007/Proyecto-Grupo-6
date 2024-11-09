package lprs.logica.contenido.realizable;

import lprs.logica.contenido.pregunta.PreguntaAbierta;

public class PreguntaAbiertaRealizable extends PreguntaRealizable {

    String respuesta;
    PreguntaAbierta preguntaBase;

    public PreguntaAbiertaRealizable(String respuesta, PreguntaAbierta preguntaBase) {
        super(preguntaBase);
        this.preguntaBase = preguntaBase;
        this.respuesta = respuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public PreguntaAbierta getPreguntaBase() {
        return preguntaBase;
    }

    public void setPreguntaBase(PreguntaAbierta preguntaBase) {
        this.preguntaBase = preguntaBase;
    }
}
