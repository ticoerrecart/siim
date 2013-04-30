package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siim.dto.EntidadDTO;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.struts.utils.Validator;

public class EntidadForm extends ActionForm {

	private Entidad entidad;

	private Long idLocalidad;

	private String tipoEntidad;

	private String confirmacionEmail;

	private EntidadDTO entidadDTO;

	public EntidadForm() {
		this.entidad = new Entidad();
		this.entidadDTO = new EntidadDTO();
	}

	/*
	 * @Override public void reset(ActionMapping mapping, HttpServletRequest
	 * request) { Entidad laEntidad = (Entidad)
	 * request.getSession().getAttribute( "entidad"); if (laEntidad != null) {
	 * this.entidad = laEntidad; } }
	 */
	public boolean validar(StringBuffer error) {
		boolean mailsOk = true;
		boolean ok1 = Validator.validarComboRequerido("-1", this.getEntidadDTO().getTipoEntidad(),
				"Tipo de Entidad", error)
				&& Validator.requerido(this.getEntidadDTO().getNombre(), "Nombre", error)
				&& Validator.validarEmail(this.getEntidadDTO().getEmail(), "E-Mail", error)
				&& Validator.validarEmail(this.getConfirmacionEmail(), "Confirmación de E-Mail",
						error);
		if (ok1 && !this.getEntidadDTO().getEmail().equalsIgnoreCase(this.getConfirmacionEmail())) {
			Validator.addErrorXML(error, "Los e-mails no coinciden.  Verifique.");
			mailsOk = false;
		}

		if (ok1 && mailsOk) {
			if (!"RN".equalsIgnoreCase(this.getEntidadDTO().getTipoEntidad())) {
				ok1 = ok1
						&& Validator.validarLongMayorQue(0,this.getEntidadDTO().getNroMatricula().toString(),
								"Nro Matrícula", error);
				if (ok1) {
					ok1 = ok1 && Validator.requerido(this.getEntidadDTO().getCuit(), "Cuit", error);
					/*if (ok1) {
						ok1 = ok1 && Validator.validarCuit(this.getEntidadDTO().getCuit(), error);
					}*/
				}

			}
		}

		return ok1 && mailsOk;
	}

	public Entidad getEntidad() {
		return entidad;
	}

	public void setEntidad(Entidad entidad) {
		this.entidad = entidad;
	}

	public Long getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(Long idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(String tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	public String getConfirmacionEmail() {
		return confirmacionEmail;
	}

	public void setConfirmacionEmail(String confirmacionEmail) {
		this.confirmacionEmail = confirmacionEmail;
	}

	public EntidadDTO getEntidadDTO() {
		return entidadDTO;
	}

	public void setEntidadDTO(EntidadDTO entidadDTO) {
		this.entidadDTO = entidadDTO;
	}

}
