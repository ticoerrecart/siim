package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.struts.utils.Validator;

public class UsuarioForm extends ActionForm {

	private Usuario usuario;

	private String contrasenia;

	private String idRol;

	private String idEntidad;

	private UsuarioDTO usuarioDTO;
	
	public UsuarioForm() {
		usuario = new Usuario();
		usuarioDTO = new UsuarioDTO();
	}

	public boolean validar(StringBuffer error) {
		boolean requeridos = Validator.requerido(this.getUsuarioDTO().getNombreUsuario(), "Nombre",
				error)
				&& Validator.requerido(this.getUsuarioDTO().getPassword(), "Contraseña", error)
				&& Validator.requerido(this.getContrasenia(), "Confirmar Contraseña", error);
		boolean passValido = true;
		if (requeridos && !this.getUsuarioDTO().getPassword().equalsIgnoreCase(this.getContrasenia())) {
			Validator.addErrorXML(error, "La contraseña no coincide.  Verifique.");
			passValido = false;
		}

		return requeridos && passValido;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(String idEntidad) {
		this.idEntidad = idEntidad;
	}

	public UsuarioDTO getUsuarioDTO() {
		return usuarioDTO;
	}

	public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
		this.usuarioDTO = usuarioDTO;
	}
	
}
