package ar.com.siim.dto;

public class TipoProductoDTO {

	private Long id;

	private String nombre;

	private double regaliaMinera;
	
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

	public double getRegaliaMinera() {
		return regaliaMinera;
	}

	public void setRegaliaMinera(double regaliaMinera) {
		this.regaliaMinera = regaliaMinera;
	}
	
	
}
