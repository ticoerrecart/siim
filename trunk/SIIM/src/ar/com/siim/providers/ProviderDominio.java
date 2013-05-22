package ar.com.siim.providers;

import java.util.ArrayList;

import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.dto.RolDTO;
import ar.com.siim.negocio.ItemMenu;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.negocio.Rol;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.dto.EntidadDTO;
import ar.com.siim.enums.TipoDeEntidad;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.Localizacion;
import ar.com.siim.negocio.Productor;
import ar.com.siim.negocio.RecursosNaturales;
import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.negocio.Periodo;
import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.negocio.Localidad;

public abstract class ProviderDominio {

	public static Localidad getLocalidad(LocalidadDTO localidadDTO){
		
		Localidad localidad = new Localidad();		
		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}

	public static Localidad getLocalidad(Localidad localidad, LocalidadDTO localidadDTO){
		
		localidad.setNombre(localidadDTO.getNombre());
		return localidad;
	}	
	
	public static Periodo getPeriodo(PeriodoDTO periodoDTO){
		
		Periodo periodo = new Periodo();		
		periodo.setPeriodo(periodoDTO.getPeriodo());
		return periodo;
	}

	public static Periodo getPeriodo(Periodo periodo, PeriodoDTO periodoDTO){
		periodo.setPeriodo(periodoDTO.getPeriodo());
		return periodo;
	}	
	
	public static Entidad getEntidad(EntidadDTO entidadDTO, Localidad localidad) {

		Entidad entidad = null;
		if (TipoDeEntidad.PRD.getName().equalsIgnoreCase(
				entidadDTO.getTipoEntidad())) {
			entidad = new Productor();
		} else {
			entidad = new RecursosNaturales();
		}

		entidad.setDireccion(entidadDTO.getDireccion());
		entidad.setEmail(entidadDTO.getEmail());
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setTelefono(entidadDTO.getTelefono());
		entidad.setLocalidad(localidad);
		entidad.setNroMatricula(entidadDTO.getNroMatricula());
		entidad.setCuit(entidadDTO.getCuit());
		entidad.setCodigoPostal(entidadDTO.getCodigoPostal());
		
		return entidad;
	}

	public static Entidad getEntidad(Entidad entidad, EntidadDTO entidadDTO,
			Localidad localidad) {

		entidad.setDireccion(entidadDTO.getDireccion());
		entidad.setEmail(entidadDTO.getEmail());
		entidad.setLocalidad(localidad);
		entidad.setNombre(entidadDTO.getNombre());
		entidad.setTelefono(entidadDTO.getTelefono());
		entidad.setNroMatricula(entidadDTO.getNroMatricula());
		entidad.setCuit(entidadDTO.getCuit());
		entidad.setCodigoPostal(entidadDTO.getCodigoPostal());
		return entidad;
	}
	
	public static Usuario getUsuario(UsuarioDTO usuarioDTO, Entidad entidad,
			Rol rol) {

		Usuario usuario = new Usuario();
		usuario.setEntidad(entidad);
		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);

		return usuario;
	}

	public static Usuario getUsuario(Usuario usuario, UsuarioDTO usuarioDTO,
			Entidad entidad, Rol rol) {

		usuario.setEntidad(entidad);
		usuario.setHabilitado(usuarioDTO.isHabilitado());
		usuario.setNombreUsuario(usuarioDTO.getNombreUsuario());
		usuario.setPassword(usuarioDTO.getPassword());
		usuario.setRol(rol);

		return usuario;
	}	
	
	public static Rol getRol(RolDTO rolDTO) {

		Rol rol = new Rol();
		rol.setRol(rolDTO.getRol());
		rol.setMenues(new ArrayList<ItemMenu>());

		return rol;
	}	
	
	public static Localizacion getLocalizacion(LocalizacionDTO localizacionDTO, Entidad productor){
		
		Localizacion localizacion = new Localizacion();
		localizacion.setExpediente(localizacionDTO.getExpediente());
		localizacion.setProductor(productor);
		localizacion.setRazonSocial(localizacionDTO.getRazonSocial());
		localizacion.setResolucion(localizacionDTO.getResolucion());
		localizacion.setSuperficie(localizacionDTO.getSuperficie());
		
		return localizacion;
	}
}
