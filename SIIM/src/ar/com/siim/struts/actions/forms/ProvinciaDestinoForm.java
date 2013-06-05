package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siim.dto.ProvinciaDestinoDTO;
import ar.com.siim.struts.utils.Validator;

public class ProvinciaDestinoForm extends ActionForm {

	private ProvinciaDestinoDTO provinciaDTO;
	
	public ProvinciaDestinoForm() {

		provinciaDTO = new ProvinciaDestinoDTO();
	}

	public boolean validar(StringBuffer error) {
		return Validator.requerido(this.getProvinciaDTO().getNombre(), "Nombre", error);
	}

	public ProvinciaDestinoDTO getProvinciaDTO() {
		return provinciaDTO;
	}

	public void setProvinciaDTO(ProvinciaDestinoDTO provinciaDTO) {
		this.provinciaDTO = provinciaDTO;
	}


}
