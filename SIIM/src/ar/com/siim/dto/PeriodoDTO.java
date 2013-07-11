package ar.com.siim.dto;

public class PeriodoDTO {

	private Long id;
	private String periodo;
	private String fechaVencimientoPrimerTrimestre;
	private String fechaVencimientoSegundoTrimestre;
	private String fechaVencimientoTercerTrimestre;
	private String fechaVencimientoCuartoTrimestre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getFechaVencimientoPrimerTrimestre() {
		return fechaVencimientoPrimerTrimestre;
	}

	public void setFechaVencimientoPrimerTrimestre(
			String fechaVencimientoPrimerTrimestre) {
		this.fechaVencimientoPrimerTrimestre = fechaVencimientoPrimerTrimestre;
	}

	public String getFechaVencimientoSegundoTrimestre() {
		return fechaVencimientoSegundoTrimestre;
	}

	public void setFechaVencimientoSegundoTrimestre(
			String fechaVencimientoSegundoTrimestre) {
		this.fechaVencimientoSegundoTrimestre = fechaVencimientoSegundoTrimestre;
	}

	public String getFechaVencimientoTercerTrimestre() {
		return fechaVencimientoTercerTrimestre;
	}

	public void setFechaVencimientoTercerTrimestre(
			String fechaVencimientoTercerTrimestre) {
		this.fechaVencimientoTercerTrimestre = fechaVencimientoTercerTrimestre;
	}

	public String getFechaVencimientoCuartoTrimestre() {
		return fechaVencimientoCuartoTrimestre;
	}

	public void setFechaVencimientoCuartoTrimestre(
			String fechaVencimientoCuartoTrimestre) {
		this.fechaVencimientoCuartoTrimestre = fechaVencimientoCuartoTrimestre;
	}

}
