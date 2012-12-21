package ar.com.siim.dto;

import java.util.List;

public class RolDTO {

	private Long id;

	private String rol;

	private List<ItemMenuDTO> menues;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public List<ItemMenuDTO> getMenues() {
		return menues;
	}

	public void setMenues(List<ItemMenuDTO> menues) {
		this.menues = menues;
	}
	
	
}
