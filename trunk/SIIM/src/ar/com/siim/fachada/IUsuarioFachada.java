package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dto.UsuarioDTO;
//import ar.com.siim.negocio.Operacion;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.negocio.exception.NegocioException;

public interface IUsuarioFachada {

	public void altaUsuario(Usuario elUsuario) throws NegocioException;

	public boolean existeUsuario(String nombre, Long id);

	public List<Usuario> getUsuarios();

	public List<UsuarioDTO> getUsuariosDTO();

	public Usuario getUsuario(Long id);

	public UsuarioDTO getUsuarioDTO(Long id);

	public void altaUsuario(UsuarioDTO usuario) throws NegocioException;

	public void modificacionUsuario(UsuarioDTO usuario) throws NegocioException;
	
}
