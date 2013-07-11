package ar.com.siim.enums;

public enum EstadoEIA {

	Aprobado("Aprobado"), Revision("Revisión(Faltan Datos)"), NoPresente("No Presente");
	
	private String descripcion;

	EstadoEIA(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getName() {
		return name();
	}	
}
