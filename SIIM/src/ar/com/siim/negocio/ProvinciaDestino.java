package ar.com.siim.negocio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class ProvinciaDestino {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nombre;
	
	@OneToMany(mappedBy = "provinciaDestino")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN })
	private List<LocalidadDestino> localidadesDestino;

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

	public List<LocalidadDestino> getLocalidadesDestino() {
		return localidadesDestino;
	}

	public void setLocalidadesDestino(List<LocalidadDestino> localidadesDestino) {
		this.localidadesDestino = localidadesDestino;
	}	
	
	
}
