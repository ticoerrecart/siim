package ar.com.siim.enums;

public enum TipoDeEntidad {
	PRD("Productor"), RN("Recursos Naturales");

	private String descripcion;

	TipoDeEntidad(String pDescripcion) {
		this.descripcion = pDescripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getName() {
		return name();
	}
}