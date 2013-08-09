package ar.com.siim.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.enums.TipoOperacion;

public class DeclaracionExtraccionDTO {

	private Long id;//

	private Long numero;//

	private EntidadDTO productor;//

	private String periodo;//

	private double importeTotal;//

	private LocalidadDTO localidad;//

	private LocalizacionDTO localizacion;//

	private String fecha;

	private List<VolumenDeclaracionDeExtraccionDTO> volumenes;//

	private boolean anulado;//

	private  List<OperacionDeclaracionExtraccionDTO> operaciones;	
	
	public DeclaracionExtraccionDTO() {

		productor = new EntidadDTO();
		localidad = new LocalidadDTO();
		localizacion = new LocalizacionDTO();
		volumenes = new ArrayList<VolumenDeclaracionDeExtraccionDTO>();
		operaciones = new ArrayList<OperacionDeclaracionExtraccionDTO>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public EntidadDTO getProductor() {
		return productor;
	}

	public void setProductor(EntidadDTO productor) {
		this.productor = productor;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
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

	public boolean getAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public List<VolumenDeclaracionDeExtraccionDTO> getVolumenes() {
		return volumenes;
	}

	public void setVolumenes(List<VolumenDeclaracionDeExtraccionDTO> volumenes) {
		this.volumenes = volumenes;
	}

	public LocalizacionDTO getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(LocalizacionDTO localizacion) {
		this.localizacion = localizacion;
	}

	public OperacionDeclaracionExtraccionDTO getOperacionAlta() {
		for (OperacionDeclaracionExtraccionDTO operacion : this.getOperaciones()) {
			if (operacion.getTipoOperacion().equals(TipoOperacion.ALTA.getDescripcion())){
				return operacion;
			}
		}
		return null;
	}

	public void addOperacion(OperacionDeclaracionExtraccionDTO operacion) {
		this.getOperaciones().add(operacion);
	}

	public List<OperacionDeclaracionExtraccionDTO> getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(List<OperacionDeclaracionExtraccionDTO> operaciones) {
		this.operaciones = operaciones;
	}
}
