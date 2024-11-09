package lprs.logica.contenido.pregunta;

public class PreguntaCerrada extends Pregunta {

    private Opcion correcta;

    private Opcion opciones[];

    public PreguntaCerrada(String enunciado, Opcion correcta, Opcion opciones[]) {
        super(enunciado);
        this.correcta = correcta;
        this.opciones = opciones;
    }

    public Opcion getCorrecta() {
        return correcta;
    }

    public void setCorrecta(Opcion correcta) {
        this.correcta = correcta;
    }

	public Opcion[] getOpciones() {
		return opciones;
	}

	public void setOpciones(Opcion[] opciones) {
		this.opciones = opciones;
	}

}
