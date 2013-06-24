package ar.com.siim.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class CanonMinero {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "productor_fk")
	private Entidad entidad;

	private String periodo;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "localizacion_fk")
	private Localizacion localizacion;

	private Double hectareas;

	private Double cantHaXPertenenciaMinera;

	private Double cantPertenenciasMineras;

	private Double canonMineroXPertenencia;

	private Double montoTotal;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "localidad_fk")
	private Localidad localidad;

	private Date fecha;

	@OneToMany(mappedBy = "canonMinero")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private List<BoletaDeposito> boletasDeposito = new ArrayList<BoletaDeposito>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Localizacion getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(Localizacion localizacion) {
		this.localizacion = localizacion;
	}

	public Double getHectareas() {
		return hectareas;
	}

	public void setHectareas(Double hectareas) {
		this.hectareas = hectareas;
	}

	public Double getCantHaXPertenenciaMinera() {
		return cantHaXPertenenciaMinera;
	}

	public void setCantHaXPertenenciaMinera(Double cantHaXPertenenciaMinera) {
		this.cantHaXPertenenciaMinera = cantHaXPertenenciaMinera;
	}

	public Double getCantPertenenciasMineras() {
		return cantPertenenciasMineras;
	}

	public void setCantPertenenciasMineras(Double cantPertenenciasMineras) {
		this.cantPertenenciasMineras = cantPertenenciasMineras;
	}

	public Double getCanonMineroXPertenencia() {
		return canonMineroXPertenencia;
	}

	public void setCanonMineroXPertenencia(Double canonMineroXPertenencia) {
		this.canonMineroXPertenencia = canonMineroXPertenencia;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<BoletaDeposito> getBoletasDeposito() {
		return boletasDeposito;
	}

	public void setBoletasDeposito(List<BoletaDeposito> boletasDeposito) {
		this.boletasDeposito = boletasDeposito;
	}

}
