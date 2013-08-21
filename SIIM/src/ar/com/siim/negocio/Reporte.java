package ar.com.siim.negocio;

import java.sql.Blob;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Reporte {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	public Long idReporte;
	
	@Column(nullable = false)	
	public String nombreReporte;
	
	@Lob @Basic(fetch = FetchType.LAZY)
	@Column(nullable = false, length=100000)	
	public Blob archivoReporte;
	
	public String nombreReportePadre;

	public Long getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(Long idReporte) {
		this.idReporte = idReporte;
	}

	public String getNombreReporte() {
		return nombreReporte;
	}

	public void setNombreReporte(String nombreReporte) {
		this.nombreReporte = nombreReporte;
	}

	public Blob getArchivoReporte() {
		return archivoReporte;
	}

	public void setArchivoReporte(Blob archivoReporte) {
		this.archivoReporte = archivoReporte;
	}

	public String getNombreReportePadre() {
		return nombreReportePadre;
	}

	public void setNombreReportePadre(String nombreReportePadre) {
		this.nombreReportePadre = nombreReportePadre;
	}
	
	
}
