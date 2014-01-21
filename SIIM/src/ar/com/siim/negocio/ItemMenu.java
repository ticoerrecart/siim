package ar.com.siim.negocio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class ItemMenu implements Comparable<ItemMenu> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String item;

	private String url;

	@ManyToOne()
	@JoinColumn(name = "item_fk")
	private ItemMenu padre;

	@OneToMany(mappedBy = "padre")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ItemMenu> hijos;

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

	public ItemMenu getPadre() {
		return padre;
	}

	public void setPadre(ItemMenu padre) {
		this.padre = padre;
	}

	public List<ItemMenu> getHijos() {
		return hijos;
	}

	public void setHijos(List<ItemMenu> hijos) {
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

	public int compareTo(ItemMenu o) {
		
		if (this.getOrden() == null) {
			if (o.getOrden() == null) {
				return 0;
			} else {
				return 1;
			}
		}
		if (o.getOrden() == null) {
			return -1;
		}

		return this.getOrden().compareTo(o.getOrden());
	}
}
