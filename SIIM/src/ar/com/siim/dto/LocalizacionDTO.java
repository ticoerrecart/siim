package ar.com.siim.dto;

import java.util.ArrayList;
import java.util.List;

public class LocalizacionDTO {

	private Long id;

	private EntidadDTO productor;

	private String expediente;

	private String razonSocial;

	private String resolucion;

	private String domicilio;

	private Double superficie;

	private List<EstudioImpactoAmbientalDTO> listaEIA;
	
	public LocalizacionDTO() {

		productor = new EntidadDTO();
		listaEIA = new ArrayList<EstudioImpactoAmbientalDTO>();
	}

	public EstudioImpactoAmbientalDTO getEstudioVigente(){
		
		for (EstudioImpactoAmbientalDTO estudio : listaEIA) {
			
			if(estudio.isVigente()){
				return estudio;
			}
		}
		return new EstudioImpactoAmbientalDTO();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EntidadDTO getProductor() {
		return productor;
	}

	public void setProductor(EntidadDTO productor) {
		this.productor = productor;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getResolucion() {
		return resolucion;
	}

	public void setResolucion(String resolucion) {
		this.resolucion = resolucion;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(Double superficie) {
		this.superficie = superficie;
	}

	public List<EstudioImpactoAmbientalDTO> getListaEIA() {
		return listaEIA;
	}

	public void setListaEIA(List<EstudioImpactoAmbientalDTO> listaEIA) {
		this.listaEIA = listaEIA;
	}

}
