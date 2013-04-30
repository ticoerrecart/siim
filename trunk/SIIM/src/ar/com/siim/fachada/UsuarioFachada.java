package ar.com.siim.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.dao.UsuarioDAO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.negocio.Entidad;
//import ar.com.siim.negocio.Operacion;
import ar.com.siim.negocio.Rol;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.providers.ProviderDTO;
import ar.com.siim.providers.ProviderDominio;

public class UsuarioFachada implements IUsuarioFachada {

	private UsuarioDAO usuarioDAO;
	private IEntidadFachada entidadFachada;
	private IRolFachada rolFachada;

	public UsuarioFachada() {
	}

	public UsuarioFachada(UsuarioDAO usuarioDAO,
			IEntidadFachada pEntidadFachada, IRolFachada pRolFachada) {
		this.usuarioDAO = usuarioDAO;
		this.entidadFachada = pEntidadFachada;
		this.rolFachada = pRolFachada;
	}

	public void altaUsuario(Usuario elUsuario) throws NegocioException {
		usuarioDAO.altaUsuario(elUsuario);
	}

	public boolean existeUsuario(String nombre, Long id) {
		return usuarioDAO.existeUsuario(nombre, id);
	}

	public List<Usuario> getUsuarios() {
		return usuarioDAO.getUsuarios();
	}

	public List<UsuarioDTO> getUsuariosDTO() {
		List<UsuarioDTO> usuariosDTO = new ArrayList<UsuarioDTO>();
		List<Usuario> usuarios = usuarioDAO.getUsuarios();
		for (Usuario usuario : usuarios) {
			usuariosDTO.add(ProviderDTO.getUsuarioDTO(usuario));
		}
		return usuariosDTO;
	}

	public Usuario getUsuario(Long id) {
		return usuarioDAO.getUsuario(id);
	}

	public UsuarioDTO getUsuarioDTO(Long id) {
		Usuario usuario = usuarioDAO.getUsuario(id);
		return ProviderDTO.getUsuarioDTO(usuario);
	}

	public void altaUsuario(UsuarioDTO usuario) throws NegocioException {
		Entidad entidad = entidadFachada.getEntidad(usuario.getEntidad()
				.getId());
		Rol rol = rolFachada.getRol(usuario.getRol().getId());
		usuarioDAO.altaUsuario(ProviderDominio
				.getUsuario(usuario, entidad, rol));

	}

	public void modificacionUsuario(UsuarioDTO usuarioDTO)
			throws NegocioException {
		Usuario usuario = usuarioDAO.getUsuario(usuarioDTO.getId());
		Entidad entidad = entidadFachada.getEntidad(usuarioDTO.getEntidad()
				.getId());
		Rol rol = rolFachada.getRol(usuarioDTO.getRol().getId());

		usuarioDAO.modificacionUsuario(ProviderDominio.getUsuario(usuario,
				usuarioDTO, entidad, rol));
	}

}
