package ar.com.siim.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@DiscriminatorValue("DECLARACIONEXTRACCION")
public class OperacionDeclaracionExtraccion extends Operacion {

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "declaracionExtraccion_fk")	
	private DeclaracionDeExtraccion declaracion;

	public DeclaracionDeExtraccion getDeclaracion() {
		return declaracion;
	}

	public void setDeclaracion(DeclaracionDeExtraccion declaracion) {
		this.declaracion = declaracion;
	}
}
