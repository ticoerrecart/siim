package ar.com.siim.dto;

public class TrimestreDeclaracionDeExtraccionDTO {

	private Long id;

	private Integer nroTrimestre;

	private Double volumenPrimerMes;

	private Double volumenSegundoMes;

	private Double volumenTercerMes;

	private String fechaVencimiento;

	private TipoProductoDTO tipoProducto;

	private Double regaliaMinera;

	// private VolumenDeclaracionDeExtraccionDTO volumenDeclaracionDeExtraccion;

	public TrimestreDeclaracionDeExtraccionDTO() {
		tipoProducto = new TipoProductoDTO();
		// volumenDeclaracionDeExtraccion = new
		// VolumenDeclaracionDeExtraccionDTO();
	}

	public boolean esNulo() {
		return volumenPrimerMes == 0.0 && volumenSegundoMes == 0.0
				&& volumenTercerMes == 0.0;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNroTrimestre() {
		return nroTrimestre;
	}

	public void setNroTrimestre(Integer nroTrimestre) {
		this.nroTrimestre = nroTrimestre;
	}

	public Double getVolumenPrimerMes() {
		return volumenPrimerMes;
	}

	public void setVolumenPrimerMes(Double volumenPrimerMes) {
		this.volumenPrimerMes = volumenPrimerMes;
	}

	public Double getVolumenSegundoMes() {
		return volumenSegundoMes;
	}

	public void setVolumenSegundoMes(Double volumenSegundoMes) {
		this.volumenSegundoMes = volumenSegundoMes;
	}

	public Double getVolumenTercerMes() {
		return volumenTercerMes;
	}

	public void setVolumenTercerMes(Double volumenTercerMes) {
		this.volumenTercerMes = volumenTercerMes;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public TipoProductoDTO getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoDTO tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Double getRegaliaMinera() {
		return regaliaMinera;
	}

	public void setRegaliaMinera(Double regaliaMinera) {
		this.regaliaMinera = regaliaMinera;
	}

	/*
	 * public VolumenDeclaracionDeExtraccionDTO
	 * getVolumenDeclaracionDeExtraccion() { return
	 * volumenDeclaracionDeExtraccion; }
	 * 
	 * public void setVolumenDeclaracionDeExtraccion(
	 * VolumenDeclaracionDeExtraccionDTO volumenDeclaracionDeExtraccion) {
	 * this.volumenDeclaracionDeExtraccion = volumenDeclaracionDeExtraccion; }
	 */

}
