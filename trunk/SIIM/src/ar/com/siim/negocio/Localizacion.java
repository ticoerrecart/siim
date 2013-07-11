package ar.com.siim.negocio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "tipoLocalizacion", discriminatorType = DiscriminatorType.STRING)
public class Localizacion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne()
	@Cascade(value = CascadeType.SAVE_UPDATE)
	@JoinColumn(name = "entidad_fk")
	protected Entidad productor;
	
	private String expediente;
	
	private String razonSocial;
	
	private String resolucion;
	
	private String domicilio;
	
	private double superficie;
	
	@OneToMany(mappedBy = "localizacion")
	@Cascade(value = {CascadeType.SAVE_UPDATE,CascadeType.DELETE_ORPHAN })	
	private List<EstudioImpactoAmbiental> listaEIA;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entidad getProductor() {
		return productor;
	}

	public void setProductor(Entidad productor) {
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

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public List<EstudioImpactoAmbiental> getListaEIA() {
		return listaEIA;
	}

	public void setListaEIA(List<EstudioImpactoAmbiental> listaEIA) {
		this.listaEIA = listaEIA;
	}
	
	public EstudioImpactoAmbiental getEstudioVigente(){
		
		for (EstudioImpactoAmbiental estudio : listaEIA) {
			
			if(estudio.isVigente()){
				return estudio;
			}
		}
		return new EstudioImpactoAmbiental();
	}	
}
