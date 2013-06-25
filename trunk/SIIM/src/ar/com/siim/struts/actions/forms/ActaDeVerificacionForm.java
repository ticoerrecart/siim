package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siim.dto.ActaDeVerificacionDTO;

public class ActaDeVerificacionForm extends ActionForm {

	private static final long serialVersionUID = -801385692305119139L;

	private ActaDeVerificacionDTO acta;
	
	public ActaDeVerificacionForm(){
		acta = new ActaDeVerificacionDTO();
	}

	public ActaDeVerificacionDTO getActa() {
		return acta;
	}

	public void setActa(ActaDeVerificacionDTO acta) {
		this.acta = acta;
	}
	

	
	
}
