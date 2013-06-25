package ar.com.siim.dto;

public class TransporteDTO {

	private String marca;

	private String dominio;

	private String semirremolqueMarca;

	private String semirremolqueDominio;

	private Integer ticketBalanza;

	private String empresaDePesaje;

	private Integer pesoBrutoEnKilos;

	private Integer taraEnKilos;

	private Integer pesoNetoEnKilos;

	private String empresaDeTransporte;

	private String nombreChofer;

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

	public Integer getTicketBalanza() {
		return ticketBalanza;
	}

	public void setTicketBalanza(Integer ticketBalanza) {
		this.ticketBalanza = ticketBalanza;
	}

	public String getEmpresaDePesaje() {
		return empresaDePesaje;
	}

	public void setEmpresaDePesaje(String empresaDePesaje) {
		this.empresaDePesaje = empresaDePesaje;
	}

	public Integer getPesoBrutoEnKilos() {
		return pesoBrutoEnKilos;
	}

	public void setPesoBrutoEnKilos(Integer pesoBrutoEnKilos) {
		this.pesoBrutoEnKilos = pesoBrutoEnKilos;
	}

	public Integer getTaraEnKilos() {
		return taraEnKilos;
	}

	public void setTaraEnKilos(Integer taraEnKilos) {
		this.taraEnKilos = taraEnKilos;
	}

	public Integer getPesoNetoEnKilos() {
		return pesoNetoEnKilos;
	}

	public void setPesoNetoEnKilos(Integer pesoNetoEnKilos) {
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
