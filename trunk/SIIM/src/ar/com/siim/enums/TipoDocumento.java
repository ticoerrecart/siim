package ar.com.siim.enums;

public enum TipoDocumento {

	CUIT("CUIT"), CUIL("CUIL"), DNI("DNI");
	
	private String descripcion;

	TipoDocumento(String pDescripcion) {
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
