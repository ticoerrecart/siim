package ar.com.siim.dto;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.EntidadDTO;
import ar.com.siim.dto.LocalidadDTO;

public class DeclaracionExtraccionDTO {

	private Long id;

	private long numero;

	private EntidadDTO productor;

	private String periodo;

	private String fechaVencimiento;

	private double importeTotal;

	private LocalidadDTO localidad;

	private String fecha;
	
	private List<BoletaDepositoDTO> boletasDeposito;
	
	private boolean anulado;
	
	public DeclaracionExtraccionDTO(){
		
		productor = new EntidadDTO();
		localidad = new LocalidadDTO();
		boletasDeposito = new ArrayList<BoletaDepositoDTO>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getNumero() {
		return numero;
	}

	public void setNumero(long numero) {
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

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	public List<BoletaDepositoDTO> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDepositoDTO> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}
}
