package lprs.logica.contenido.realizable;

import lprs.logica.contenido.pregunta.Opcion;
import lprs.logica.contenido.pregunta.PreguntaCerrada;

public class PreguntaCerradaRealizable extends PreguntaRealizable {

	Opcion opcionEscogida;
	PreguntaCerrada preguntaBase;
	boolean correcta;

	public PreguntaCerradaRealizable(PreguntaCerrada preguntaBase, Opcion opcionEscogida) {
		super(preguntaBase);
		this.preguntaBase = preguntaBase;
		this.opcionEscogida = opcionEscogida;
		verificarOpcion(opcionEscogida);
	}

	public Opcion getOpcionEscogida() {
		return opcionEscogida;
	}

	public void setOpcionEscogida(Opcion opcionEscogida) {
		this.opcionEscogida = opcionEscogida;
	}

	public boolean verificarOpcion(Opcion opcionEscogida) {
		Opcion correcta = preguntaBase.getCorrecta();
		if (correcta == opcionEscogida) {
			this.correcta = true;
		} else {
			this.correcta = false;
		}
		return this.correcta;
	}

	public boolean isCorrecta() {
		return correcta;
	}

	public PreguntaCerrada getPreguntaBase() {
		return preguntaBase;
	}

}
