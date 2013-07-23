package ar.com.siim.negocio;

import java.util.Date;

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

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "tipoProducto_fk")
	private TipoProducto tipoProducto;

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "volumenDeclaracionDeExtraccion_fk")
	private VolumenDeclaracionDeExtraccion volumenDeclaracionDeExtraccion;

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

	public VolumenDeclaracionDeExtraccion getVolumenDeclaracionDeExtraccion() {
		return volumenDeclaracionDeExtraccion;
	}

	public void setVolumenDeclaracionDeExtraccion(
			VolumenDeclaracionDeExtraccion volumenDeclaracionDeExtraccion) {
		this.volumenDeclaracionDeExtraccion = volumenDeclaracionDeExtraccion;
	}

}
