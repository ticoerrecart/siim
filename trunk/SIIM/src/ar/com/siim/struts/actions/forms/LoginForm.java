package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

public class LoginForm extends ActionForm {

	private String usuario;

	private String password;

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
