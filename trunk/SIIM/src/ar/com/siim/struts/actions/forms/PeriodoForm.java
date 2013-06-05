package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.negocio.Periodo;
import ar.com.siim.struts.utils.Validator;

public class PeriodoForm extends ActionForm {

	private Periodo periodo;
	private PeriodoDTO periodoDTO;

	public PeriodoForm() {
		periodo = new Periodo();
		periodoDTO = new PeriodoDTO();
	}

	/*public boolean validar(StringBuffer error) {
		boolean b1 = Validator.requerido(this.getPeriodoDTO().getPeriodo(),
				"Periodo", error);
		boolean b2 = Validator.validarFormatoPeriodo(this.getPeriodoDTO()
				.getPeriodo(), error);
		return b1 && b2;

	}*/

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public PeriodoDTO getPeriodoDTO() {
		return periodoDTO;
	}

	public void setPeriodoDTO(PeriodoDTO periodoDTO) {
		this.periodoDTO = periodoDTO;
	}

}
