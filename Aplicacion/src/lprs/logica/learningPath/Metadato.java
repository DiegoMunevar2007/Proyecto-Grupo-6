package lprs.logica.learningPath;

import java.io.Serializable;
import java.util.Date;

public class Metadato implements Serializable {
	private String fechaCreacion;
	private String fechaModificacion;
	private String version;

	public Metadato(String fechaCreacion,String version) {
		this.fechaCreacion = fechaCreacion;
		this.fechaModificacion = fechaCreacion;
		this.version = version;
	}

	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaString) {
		this.fechaModificacion = fechaString;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fecha) {
		this.fechaCreacion = fecha;
	}

}
