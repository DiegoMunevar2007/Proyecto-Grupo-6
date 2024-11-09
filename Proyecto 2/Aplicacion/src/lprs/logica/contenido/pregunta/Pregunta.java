package lprs.logica.contenido.pregunta;

import java.io.Serializable;

public class Pregunta implements Serializable {
    protected String enunciado;

    public Pregunta(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
