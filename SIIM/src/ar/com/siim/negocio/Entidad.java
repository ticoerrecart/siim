package ar.com.siim.negocio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipoEntidad", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("ENTIDAD")
public class Entidad implements Comparable<Entidad> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "localidad_fk")
	private Localidad localidad;

	private String direccion;

	private String telefono;

	private String email;

	private Long nroMatricula;

	private String cuit;

	private Integer codigoPostal;

	@OneToMany(mappedBy = "productorForestal")
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE_ORPHAN })
	private List<Localizacion> localizaciones;

	/**
	 * Cada subclase debe implementar éste método.
	 * 
	 * @return
	 */
	public String getTipoEntidad() {
		return null;
	}

	/**
	 * Cada subclase debe implementar éste método.
	 * 
	 * @return
	 */
	public String getIdTipoEntidad() {
		return null;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public List<Localizacion> getLocalizaciones() {
		return localizaciones;
	}

	public void setLocalizaciones(List<Localizacion> localizaciones) {
		this.localizaciones = localizaciones;
	}

	public int compareTo(Entidad o) {

		return this.getNombre().compareTo(o.getNombre());
	}

	public Long getNroMatricula() {
		return nroMatricula;
	}

	public void setNroMatricula(Long nroMatricula) {
		this.nroMatricula = nroMatricula;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public Integer getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(Integer codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

}
