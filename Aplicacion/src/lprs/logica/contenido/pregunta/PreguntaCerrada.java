package lprs.logica.contenido.pregunta;

public class PreguntaCerrada extends Pregunta{

    private boolean correcta;

    public PreguntaCerrada(String enunciado, boolean correcta) {
        super(enunciado);
        this.correcta = correcta;
    }

    public boolean isCorrecta() {
        return correcta;
    }

    public void setCorrecta(boolean correcta) {
        this.correcta = correcta;
    }

}
