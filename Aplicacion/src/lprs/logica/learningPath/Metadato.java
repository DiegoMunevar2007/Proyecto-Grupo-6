package lprs.logica.learningPath;

import java.util.Date;

public class Metadato {
    private Date fechaCreacion;
    private Date fechaModificacion;
    private String version;
	public Metadato(String version) {
		this.fechaCreacion = new Date();
		this.fechaModificacion = new Date();
		this.version = version;
	}
	public Date getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	
    
}
