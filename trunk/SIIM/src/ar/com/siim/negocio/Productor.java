package ar.com.siim.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.siim.enums.TipoDeEntidad;

@Entity
@DiscriminatorValue("PRD")
public class Productor extends Entidad {

	public String getTipoEntidad() {
		return TipoDeEntidad.PRD.getDescripcion();
	}

	public String getIdTipoEntidad() {
		//return "RN";
		return TipoDeEntidad.PRD.getName();
	}	
}
