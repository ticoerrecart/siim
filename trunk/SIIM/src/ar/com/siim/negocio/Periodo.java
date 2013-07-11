package ar.com.siim.negocio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Periodo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String periodo;

	@Column(nullable = false)
	private Date fechaVencimientoPrimerTrimestre;

	@Column(nullable = false)
	private Date fechaVencimientoSegundoTrimestre;

	@Column(nullable = false)
	private Date fechaVencimientoTercerTrimestre;

	@Column(nullable = false)
	private Date fechaVencimientoCuartoTrimestre;

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// 1er Trimestre
	public Date getFechaVencimientoPrimerTrimestre() {
		return fechaVencimientoPrimerTrimestre;
	}

	/*
	 * public String getFechaVencimientoPrimerTrimestreStr() { String fechaStr =
	 * ""; if (fechaVencimientoPrimerTrimestre != null) { fechaStr =
	 * DateUtils.stringFromDate( fechaVencimientoPrimerTrimestre, "dd/MM/yyyy");
	 * } return fechaStr; }
	 */
	public void setFechaVencimientoPrimerTrimestre(
			Date fechaVencimientoPrimerTrimestre) {
		this.fechaVencimientoPrimerTrimestre = fechaVencimientoPrimerTrimestre;
	}

	/*
	 * public void setFechaVencimientoPrimerTrimestreStr( String
	 * fechaVencimientoPrimerTrimestre) { if (fechaVencimientoPrimerTrimestre !=
	 * null && fechaVencimientoPrimerTrimestre != "") {
	 * this.setFechaVencimientoPrimerTrimestre((Fecha
	 * .stringDDMMAAAAToDate(fechaVencimientoPrimerTrimestre))); } }
	 */

	// 2do Trimestre
	public Date getFechaVencimientoSegundoTrimestre() {
		return fechaVencimientoSegundoTrimestre;
	}

	/*
	 * public String getFechaVencimientoSegundoTrimestreStr() { String fechaStr
	 * = ""; if (fechaVencimientoSegundoTrimestre != null) { fechaStr =
	 * DateUtils.stringFromDate( fechaVencimientoSegundoTrimestre,
	 * "dd/MM/yyyy"); } return fechaStr; }
	 */

	public void setFechaVencimientoSegundoTrimestre(
			Date fechaVencimientoSegundoTrimestre) {
		this.fechaVencimientoSegundoTrimestre = fechaVencimientoSegundoTrimestre;
	}

	/*
	 * public void setFechaVencimientoSegundoTrimestreStr( String
	 * fechaVencimientoSegundoTrimestre) { if (fechaVencimientoSegundoTrimestre
	 * != null && fechaVencimientoSegundoTrimestre != "") {
	 * this.setFechaVencimientoSegundoTrimestre((Fecha
	 * .stringDDMMAAAAToDate(fechaVencimientoSegundoTrimestre))); } }
	 */

	// 3er Trimestre
	public Date getFechaVencimientoTercerTrimestre() {
		return fechaVencimientoTercerTrimestre;
	}

	/*
	 * public String getFechaVencimientoTercerTrimestreStr() { String fechaStr =
	 * ""; if (fechaVencimientoTercerTrimestre != null) { fechaStr =
	 * DateUtils.stringFromDate( fechaVencimientoTercerTrimestre, "dd/MM/yyyy");
	 * } return fechaStr; }
	 */

	public void setFechaVencimientoTercerTrimestre(
			Date fechaVencimientoTercerTrimestre) {
		this.fechaVencimientoTercerTrimestre = fechaVencimientoTercerTrimestre;
	}

	/*
	 * public void setFechaVencimientoTercerTrimestreStr( String
	 * fechaVencimientoTercerTrimestre) { if (fechaVencimientoTercerTrimestre !=
	 * null && fechaVencimientoTercerTrimestre != "") {
	 * this.setFechaVencimientoTercerTrimestre((Fecha
	 * .stringDDMMAAAAToDate(fechaVencimientoTercerTrimestre))); } }
	 */

	// 4to Trimestre
	public Date getFechaVencimientoCuartoTrimestre() {
		return fechaVencimientoCuartoTrimestre;
	}

	/*
	 * public String getFechaVencimientoCuartoTrimestreStr() { String fechaStr =
	 * ""; if (fechaVencimientoCuartoTrimestre != null) { fechaStr =
	 * DateUtils.stringFromDate( fechaVencimientoCuartoTrimestre, "dd/MM/yyyy");
	 * } return fechaStr; }
	 */

	public void setFechaVencimientoCuartoTrimestre(
			Date fechaVencimientoCuartoTrimestre) {
		this.fechaVencimientoCuartoTrimestre = fechaVencimientoCuartoTrimestre;
	}

	/*
	 * public void setFechaVencimientoCuartoTrimestreStr( String
	 * fechaVencimientoCuartoTrimestre) { if (fechaVencimientoCuartoTrimestre !=
	 * null && fechaVencimientoCuartoTrimestre != "") {
	 * this.setFechaVencimientoCuartoTrimestre((Fecha
	 * .stringDDMMAAAAToDate(fechaVencimientoCuartoTrimestre))); } }
	 */
}