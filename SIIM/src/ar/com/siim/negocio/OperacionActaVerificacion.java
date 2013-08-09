package ar.com.siim.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@DiscriminatorValue("ACTAVERIFICACION")
public class OperacionActaVerificacion extends Operacion {

	@ManyToOne()
	@Cascade(value = { CascadeType.ALL, CascadeType.DELETE_ORPHAN })
	@JoinColumn(name = "actaVerificacion_fk")	
	private ActaDeVerificacion actaVerificacion;

	public ActaDeVerificacion getActaVerificacion() {
		return actaVerificacion;
	}

	public void setActaVerificacion(ActaDeVerificacion actaVerificacion) {
		this.actaVerificacion = actaVerificacion;
	}
}
