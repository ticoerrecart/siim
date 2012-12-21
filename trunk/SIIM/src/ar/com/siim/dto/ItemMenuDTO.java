package ar.com.siim.dto;

import java.util.List;

public class ItemMenuDTO implements Comparable<ItemMenuDTO>{

	private Long id;
	private String item;
	private String url;	
	private ItemMenuDTO padre;
	private List<ItemMenuDTO> hijos;
	private Integer orden;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ItemMenuDTO getPadre() {
		return padre;
	}
	public void setPadre(ItemMenuDTO padre) {
		this.padre = padre;
	}
	public List<ItemMenuDTO> getHijos() {
		return hijos;
	}
	public void setHijos(List<ItemMenuDTO> hijos) {
		this.hijos = hijos;
	}
	public Integer getOrden() {
		return orden;
	}
	public void setOrden(Integer orden) {
		this.orden = orden;
	}	
	public boolean esRaiz() {
		return this.getPadre() == null;
	}	
	public int compareTo(ItemMenuDTO o) {

		if (this.getOrden() == null) {
			return 1;
		}
		if (o.getOrden() == null) {
			return -1;
		}
		return this.getOrden().compareTo(o.getOrden());
	}	
	
}
