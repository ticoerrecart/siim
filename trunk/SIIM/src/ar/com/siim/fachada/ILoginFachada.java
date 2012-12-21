package ar.com.siim.fachada;

import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.negocio.exception.NegocioException;

public interface ILoginFachada {

	public UsuarioDTO login(String usuario, String password) throws NegocioException;

	public Usuario getUsuario(Long id)throws NegocioException;

}
