package lprs.logica.contenido.pregunta;

public class Pregunta {
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
