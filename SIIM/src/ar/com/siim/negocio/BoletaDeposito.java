package ar.com.siim.negocio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.com.siim.utils.Fecha;

@Entity
public class BoletaDeposito {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private Long numero;

	private String concepto;

	private String area;

	private String efectivoCheque;

	@Column(nullable = false)
	private double monto;

	private Date fechaPago;

	@Column(nullable = false)
	private Date fechaVencimiento;

	@Column(nullable = false)
	private boolean anulado;

	@Transient
	private String fechaPagoTransient;

	@Transient
	private String fechaVencimientoTransient;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "canonMinero_fk")
	private CanonMinero canonMinero;

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getEfectivoCheque() {
		return efectivoCheque;
	}

	public void setEfectivoCheque(String efectivoCheque) {
		this.efectivoCheque = efectivoCheque;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
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

	public String getFechaPagoTransient() {
		return fechaPagoTransient;
	}

	public void setFechaPagoTransient(String fechaPagoTransient) {
		this.fechaPagoTransient = fechaPagoTransient;
	}

	public String getFechaVencimientoTransient() {
		return fechaVencimientoTransient;
	}

	public void setFechaVencimientoTransient(String fechaVencimientoTransient) {
		this.fechaVencimientoTransient = fechaVencimientoTransient;

		if (fechaVencimientoTransient != null
				&& fechaVencimientoTransient != "") {
			this.setFechaVencimiento(Fecha
					.stringDDMMAAAAToDate(fechaVencimientoTransient));
		}
	}

	public boolean isAnulado() {
		return anulado;
	}

	public void setAnulado(boolean anulado) {
		this.anulado = anulado;
	}

	public CanonMinero getCanonMinero() {
		return canonMinero;
	}

	public void setCanonMinero(CanonMinero canonMinero) {
		this.canonMinero = canonMinero;
	}

}
