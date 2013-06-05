package ar.com.siim.dto;

public class LocalidadDestinoDTO {

	private Long id;
	private String nombre;
	private ProvinciaDestinoDTO provinciaDestinoDTO;
	
	public LocalidadDestinoDTO(){
		
		provinciaDestinoDTO = new ProvinciaDestinoDTO();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ProvinciaDestinoDTO getProvinciaDestinoDTO() {
		return provinciaDestinoDTO;
	}

	public void setProvinciaDestinoDTO(ProvinciaDestinoDTO provinciaDestinoDTO) {
		this.provinciaDestinoDTO = provinciaDestinoDTO;
	}
	
	
}
