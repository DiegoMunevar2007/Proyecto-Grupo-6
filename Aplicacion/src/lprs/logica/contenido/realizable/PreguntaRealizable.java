package lprs.logica.contenido.realizable;

import lprs.logica.contenido.pregunta.Pregunta;

public class PreguntaRealizable {
	Pregunta preguntaBase;

	public PreguntaRealizable(Pregunta preguntaBase) {
		this.preguntaBase = preguntaBase;

	}

	public Pregunta getPreguntaBase() {
		return preguntaBase;
	}

	public void setPreguntaBase(Pregunta preguntaBase) {
		this.preguntaBase = preguntaBase;
	}

}
