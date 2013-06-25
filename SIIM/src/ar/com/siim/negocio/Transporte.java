package ar.com.siim.negocio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transporte {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String marca;

	@Column(nullable = false)
	private String dominio;

	private String semirremolqueMarca;

	private String semirremolqueDominio;

	private int ticketBalanza;

	private String empresaDePesaje;

	private int pesoBrutoEnKilos;

	private int taraEnKilos;

	private int pesoNetoEnKilos;

	private String empresaDeTransporte;

	private String nombreChofer;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}

	public String getSemirremolqueMarca() {
		return semirremolqueMarca;
	}

	public void setSemirremolqueMarca(String semirremolqueMarca) {
		this.semirremolqueMarca = semirremolqueMarca;
	}

	public String getSemirremolqueDominio() {
		return semirremolqueDominio;
	}

	public void setSemirremolqueDominio(String semirremolqueDominio) {
		this.semirremolqueDominio = semirremolqueDominio;
	}

	public int getTicketBalanza() {
		return ticketBalanza;
	}

	public void setTicketBalanza(int ticketBalanza) {
		this.ticketBalanza = ticketBalanza;
	}

	public String getEmpresaDePesaje() {
		return empresaDePesaje;
	}

	public void setEmpresaDePesaje(String empresaDePesaje) {
		this.empresaDePesaje = empresaDePesaje;
	}

	public int getPesoBrutoEnKilos() {
		return pesoBrutoEnKilos;
	}

	public void setPesoBrutoEnKilos(int pesoBrutoEnKilos) {
		this.pesoBrutoEnKilos = pesoBrutoEnKilos;
	}

	public int getTaraEnKilos() {
		return taraEnKilos;
	}

	public void setTaraEnKilos(int taraEnKilos) {
		this.taraEnKilos = taraEnKilos;
	}

	public int getPesoNetoEnKilos() {
		return pesoNetoEnKilos;
	}

	public void setPesoNetoEnKilos(int pesoNetoEnKilos) {
		this.pesoNetoEnKilos = pesoNetoEnKilos;
	}

	public String getEmpresaDeTransporte() {
		return empresaDeTransporte;
	}

	public void setEmpresaDeTransporte(String empresaDeTransporte) {
		this.empresaDeTransporte = empresaDeTransporte;
	}

	public String getNombreChofer() {
		return nombreChofer;
	}

	public void setNombreChofer(String nombreChofer) {
		this.nombreChofer = nombreChofer;
	}

}
