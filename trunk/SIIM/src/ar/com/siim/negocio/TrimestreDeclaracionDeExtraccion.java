package ar.com.siim.negocio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class TrimestreDeclaracionDeExtraccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Integer nroTrimestre;

	private Double volumenPrimerMes;

	private Double volumenSegundoMes;

	private Double volumenTercerMes;

	private Date fechaVencimiento;

	private Double regaliaMinera;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "tipoProducto_fk")
	private TipoProducto tipoProducto;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "declaracionDeExtraccion_fk")
	private DeclaracionDeExtraccion declaracionDeExtraccion;

	@Column(nullable = false)
	private Date fecha;

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

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public TipoProducto getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProducto tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Double getVolumenTotal() {
		return this.getVolumenPrimerMes() + this.getVolumenSegundoMes()
				+ this.getVolumenTercerMes();
	}

	public Double getImporteTotal() {
		return this.getVolumenTotal() * this.getRegaliaMinera();
	}

	public DeclaracionDeExtraccion getDeclaracionDeExtraccion() {
		return declaracionDeExtraccion;
	}

	public void setDeclaracionDeExtraccion(
			DeclaracionDeExtraccion declaracionDeExtraccion) {
		this.declaracionDeExtraccion = declaracionDeExtraccion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getRegaliaMinera() {
		return regaliaMinera;
	}

	public void setRegaliaMinera(Double regaliaMinera) {
		this.regaliaMinera = regaliaMinera;
	}
}
