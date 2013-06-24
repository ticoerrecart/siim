package ar.com.siim.dto;

public class CanonMineroDTO {

	private long id;

	private EntidadDTO entidad;//

	private LocalizacionDTO zonaExtraccion;//

	private String periodo;//

	private double hectareas;

	private double cantHaXPertenenciaMinera;

	private double cantPertenenciasMineras;

	private double canonXPertenencia;//

	private double montoTotal;//

	private LocalidadDTO localidad;//

	private String fecha;//

	public CanonMineroDTO() {

		entidad = new EntidadDTO();
		zonaExtraccion = new LocalizacionDTO();
		localidad = new LocalidadDTO();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalizacionDTO getZonaExtraccion() {
		return zonaExtraccion;
	}

	public void setZonaExtraccion(LocalizacionDTO zonaExtraccion) {
		this.zonaExtraccion = zonaExtraccion;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public double getCanonXPertenencia() {
		return canonXPertenencia;
	}

	public void setCanonXPertenencia(double canonXPertenencia) {
		this.canonXPertenencia = canonXPertenencia;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public EntidadDTO getEntidad() {
		return entidad;
	}

	public void setEntidad(EntidadDTO entidad) {
		this.entidad = entidad;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public double getHectareas() {
		return hectareas;
	}

	public void setHectareas(double hectareas) {
		this.hectareas = hectareas;
	}

	public double getCantHaXPertenenciaMinera() {
		return cantHaXPertenenciaMinera;
	}

	public void setCantHaXPertenenciaMinera(double cantHaXPertenenciaMinera) {
		this.cantHaXPertenenciaMinera = cantHaXPertenenciaMinera;
	}

	public double getCantPertenenciasMineras() {
		return cantPertenenciasMineras;
	}

	public void setCantPertenenciasMineras(double cantPertenenciasMineras) {
		this.cantPertenenciasMineras = cantPertenenciasMineras;
	}

}
