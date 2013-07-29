package ar.com.siim.dto;


public class EstudioImpactoAmbientalDTO {

	private Long id;
	
	private String estado;
	
	private String fechaDesde;
	
	private String fechaHasta;
	
	private String nroResolucionEIA;
	
	private String observaciones;
	
	private LocalizacionDTO localizacion;
	
	private boolean vigente;
	
	private String fechaAlta;
	
	public EstudioImpactoAmbientalDTO(){
		
		localizacion = new LocalizacionDTO();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public String getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public String getNroResolucionEIA() {
		return nroResolucionEIA;
	}

	public void setNroResolucionEIA(String nroResolucionEIA) {
		this.nroResolucionEIA = nroResolucionEIA;
	}

	public LocalizacionDTO getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(LocalizacionDTO localizacion) {
		this.localizacion = localizacion;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public boolean isVigente() {
		return vigente;
	}

	public void setVigente(boolean vigente) {
		this.vigente = vigente;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
}
