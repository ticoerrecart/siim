package ar.com.siim.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String rol;

	@ManyToMany
	@JoinTable(name = "rol_item", joinColumns = @JoinColumn(name = "rol_fk"), inverseJoinColumns = { @JoinColumn(name = "item_fk") })
	@Cascade({ CascadeType.ALL })
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ItemMenu> menues;

	public Rol() {
		menues = new ArrayList<ItemMenu>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idRol) {
		this.id = idRol;
	}

	//@OrderBy("id")
	public List<ItemMenu> getMenues() {
		return menues;
	}

	public void setMenues(List<ItemMenu> menues) {
		this.menues = menues;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}
