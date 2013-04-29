package ar.com.siim.fachada;

import ar.com.siim.dao.LoginDAO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.negocio.exception.DataBaseException;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.providers.ProviderDTO;

public class LoginFachada implements ILoginFachada {

	private LoginDAO loginDAO;

	public LoginFachada() {
	}

	public LoginFachada(LoginDAO loginDAO) {
		this.loginDAO = loginDAO;
	}

	public UsuarioDTO login(String usuario, String password) throws NegocioException {

		Usuario usr = loginDAO.login(usuario, password);
		UsuarioDTO usrDTO = ProviderDTO.getUsuarioDTO(usr);
			
		return usrDTO;	
	}

	public Usuario getUsuario(Long id){

		return loginDAO.getUsuario(id);
	}
}
