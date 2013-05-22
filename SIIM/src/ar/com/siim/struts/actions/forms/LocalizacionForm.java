package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siim.dto.LocalizacionDTO;

public class LocalizacionForm extends ActionForm {

	private LocalizacionDTO localizacionDTO;
	
	public LocalizacionForm(){
		
		localizacionDTO = new LocalizacionDTO();
	}

	public LocalizacionDTO getLocalizacionDTO() {
		return localizacionDTO;
	}

	public void setLocalizacionDTO(LocalizacionDTO localizacionDTO) {
		this.localizacionDTO = localizacionDTO;
	}

}
