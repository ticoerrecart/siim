package ar.com.siim.negocio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class LocalidadDestino {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nombre;
	
	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "provinciaDestino_fk")
	private ProvinciaDestino provinciaDestino;

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

	public ProvinciaDestino getProvinciaDestino() {
		return provinciaDestino;
	}

	public void setProvinciaDestino(ProvinciaDestino provinciaDestino) {
		this.provinciaDestino = provinciaDestino;
	}	
	
	
}
