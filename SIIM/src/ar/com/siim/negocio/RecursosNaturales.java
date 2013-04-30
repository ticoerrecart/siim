package ar.com.siim.negocio;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import ar.com.siim.enums.TipoDeEntidad;

@Entity
@DiscriminatorValue("RN")
public class RecursosNaturales extends Entidad {

	public String getTipoEntidad() {
		return TipoDeEntidad.RN.getDescripcion();
	}

	public String getIdTipoEntidad() {
		//return "RN";
		return TipoDeEntidad.RN.getName();
	}
}
