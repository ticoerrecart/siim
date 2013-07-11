package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siim.dto.EstudioImpactoAmbientalDTO;
import ar.com.siim.dto.LocalizacionDTO;

public class LocalizacionForm extends ActionForm {

	private LocalizacionDTO localizacionDTO;
	
	private EstudioImpactoAmbientalDTO estudioVigente;
	
	public LocalizacionForm(){
		
		localizacionDTO = new LocalizacionDTO();
		estudioVigente = new EstudioImpactoAmbientalDTO();
	}

	public LocalizacionDTO getLocalizacionDTO() {
		return localizacionDTO;
	}

	public void setLocalizacionDTO(LocalizacionDTO localizacionDTO) {
		this.localizacionDTO = localizacionDTO;
	}

	public EstudioImpactoAmbientalDTO getEstudioVigente() {
		return estudioVigente;
	}

	public void setEstudioVigente(EstudioImpactoAmbientalDTO estudioVigente) {
		this.estudioVigente = estudioVigente;
	}

}
