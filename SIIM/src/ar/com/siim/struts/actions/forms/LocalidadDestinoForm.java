package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siim.dto.LocalidadDestinoDTO;
import ar.com.siim.struts.utils.Validator;

public class LocalidadDestinoForm extends ActionForm {

	private LocalidadDestinoDTO localidadDestinoDTO;
	
	public LocalidadDestinoForm(){
		
		localidadDestinoDTO = new LocalidadDestinoDTO();
	}

	public LocalidadDestinoDTO getLocalidadDestinoDTO() {
		return localidadDestinoDTO;
	}

	public void setLocalidadDestinoDTO(LocalidadDestinoDTO localidadDestinoDTO) {
		this.localidadDestinoDTO = localidadDestinoDTO;
	}
	
	public boolean validar(StringBuffer error) {
		return Validator.requerido(this.getLocalidadDestinoDTO().getNombre(), "Nombre", error);
	}	
}
