package ar.com.siim.dto;

public class LocalizacionDTO {

	private Long id;

	private EntidadDTO productor;

	private String expediente;

	private String razonSocial;

	private String resolucion;

	private String domicilio;

	private Double superficie;

	public LocalizacionDTO() {

		productor = new EntidadDTO();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntidadDTO getProductor() {
		return productor;
	}

	public void setProductor(EntidadDTO productor) {
		this.productor = productor;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}

}
