package ar.com.siim.providers;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.dto.EntidadDTO;
import ar.com.siim.dto.ItemMenuDTO;
import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.dto.LocalidadDestinoDTO;
import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.dto.ProvinciaDestinoDTO;
import ar.com.siim.dto.RolDTO;
import ar.com.siim.dto.TipoProductoDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.ItemMenu;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.LocalidadDestino;
import ar.com.siim.negocio.Localizacion;
import ar.com.siim.negocio.Periodo;
import ar.com.siim.negocio.ProvinciaDestino;
import ar.com.siim.negocio.Rol;
import ar.com.siim.negocio.TipoProducto;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.utils.DateUtils;

public abstract class ProviderDTO {

	public static UsuarioDTO getUsuarioDTO(Usuario usuario) {

		UsuarioDTO usuarioDTO = new UsuarioDTO();

		usuarioDTO.setId(usuario.getId());
		usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
		usuarioDTO.setPassword(usuario.getPassword());
		usuarioDTO.setRol(ProviderDTO.getRolDTO(usuario.getRol()));
		usuarioDTO.setEntidad(ProviderDTO.getEntidadDTO(usuario.getEntidad()));
		usuarioDTO.setHabilitado(usuario.isHabilitado());

		return usuarioDTO;
	}

	public static RolDTO getRolDTO(Rol rol) {

		List<ItemMenuDTO> listaMenuesDTO = new ArrayList<ItemMenuDTO>();
		RolDTO rolDTO = new RolDTO();

		rolDTO.setId(rol.getId());
		rolDTO.setRol(rol.getRol());

		for (ItemMenu menu : rol.getMenues()) {
			listaMenuesDTO.add(ProviderDTO.getItemMenuDTO(menu));
		}
		rolDTO.setMenues(listaMenuesDTO);

		return rolDTO;
	}

	public static ItemMenuDTO getItemMenuDTO(ItemMenu menu) {
		return ProviderDTO.getItemMenuDTO(menu, null);
	}

	private static ItemMenuDTO getItemMenuDTO(ItemMenu menu, ItemMenuDTO padre) {

		if (menu != null) {
			List<ItemMenuDTO> listaMenuDTO = new ArrayList<ItemMenuDTO>();
			ItemMenuDTO menuDTO = new ItemMenuDTO();

			menuDTO.setId(menu.getId());
			menuDTO.setItem(menu.getItem());
			menuDTO.setOrden(menu.getOrden());
			menuDTO.setPadre(padre);
			menuDTO.setUrl(menu.getUrl());

			for (ItemMenu menuHijo : menu.getHijos()) {
				listaMenuDTO.add(ProviderDTO.getItemMenuDTO(menuHijo, menuDTO));
			}
			menuDTO.setHijos(listaMenuDTO);

			return menuDTO;
		} else {
			return null;
		}
	}

	public static LocalidadDTO getLocalidadDTO(Localidad localidad) {

		LocalidadDTO localidadDTO = new LocalidadDTO();

		localidadDTO.setId(localidad.getId());
		localidadDTO.setNombre(localidad.getNombre());

		return localidadDTO;
	}

	public static PeriodoDTO getPeriodoDTO(Periodo periodo) {

		PeriodoDTO periodoDTO = new PeriodoDTO();

		periodoDTO.setId(periodo.getId());
		periodoDTO.setPeriodo(periodo.getPeriodo());
		periodoDTO.setFechaVencimientoPrimerTrimestre(DateUtils.stringFromDate(
				periodo.getFechaVencimientoPrimerTrimestre(), "dd/MM/yyyy"));
		periodoDTO.setFechaVencimientoSegundoTrimestre(DateUtils
				.stringFromDate(periodo.getFechaVencimientoSegundoTrimestre(),
						"dd/MM/yyyy"));
		periodoDTO.setFechaVencimientoTercerTrimestre(DateUtils.stringFromDate(
				periodo.getFechaVencimientoTercerTrimestre(), "dd/MM/yyyy"));
		periodoDTO.setFechaVencimientoCuartoTrimestre(DateUtils.stringFromDate(
				periodo.getFechaVencimientoCuartoTrimestre(), "dd/MM/yyyy"));

		return periodoDTO;
	}

	public static EntidadDTO getEntidadDTO(Entidad entidad) {

		EntidadDTO entidadDTO = new EntidadDTO();

		entidadDTO.setId(entidad.getId());
		entidadDTO.setNombre(entidad.getNombre());
		entidadDTO.setDireccion(entidad.getDireccion());
		entidadDTO.setTelefono(entidad.getTelefono());
		entidadDTO.setEmail(entidad.getEmail());
		entidadDTO.setLocalidad(ProviderDTO.getLocalidadDTO(entidad
				.getLocalidad()));
		entidadDTO.setTipoEntidadDesc(entidad.getTipoEntidad());
		entidadDTO.setTipoEntidad(entidad.getIdTipoEntidad());
		entidadDTO.setNroMatricula(entidad.getNroMatricula());
		entidadDTO.setCuit(entidad.getCuit());
		entidadDTO.setCuil(entidad.getCuil());
		entidadDTO.setDni(entidad.getDni());
		entidadDTO.setTipoDocumento(entidad.getTipoDocumento());
		entidadDTO.setCodigoPostal(entidad.getCodigoPostal());

		return entidadDTO;
	}

	public static TipoProductoDTO getTipoProductoDTO(TipoProducto tipoProducto) {

		TipoProductoDTO tipoProdDTO = new TipoProductoDTO();
		tipoProdDTO.setId(tipoProducto.getId());
		tipoProdDTO.setNombre(tipoProducto.getNombre());
		tipoProdDTO.setRegaliaMinera(tipoProducto.getRegaliaMinera());

		return tipoProdDTO;
	}

	public static LocalizacionDTO getLocalizacionDTO(Localizacion localizacion) {

		LocalizacionDTO localizacionDTO = new LocalizacionDTO();
		localizacionDTO.setExpediente(localizacion.getExpediente());
		localizacionDTO.setId(localizacion.getId());
		localizacionDTO.setProductor(ProviderDTO.getEntidadDTO(localizacion
				.getProductor()));
		localizacionDTO.setRazonSocial(localizacion.getRazonSocial());
		localizacionDTO.setResolucion(localizacion.getResolucion());
		localizacionDTO.setDomicilio(localizacion.getDomicilio());
		localizacionDTO.setSuperficie(localizacion.getSuperficie());

		return localizacionDTO;
	}

	public static ProvinciaDestinoDTO getProvinciaDestinoDTO(
			ProvinciaDestino provincia) {

		ProvinciaDestinoDTO provinciaDTO = new ProvinciaDestinoDTO();

		provinciaDTO.setId(provincia.getId());
		provinciaDTO.setNombre(provincia.getNombre());

		return provinciaDTO;
	}

	public static LocalidadDestinoDTO getLocalidadDestinoDTO(
			LocalidadDestino localidad) {

		LocalidadDestinoDTO localidadDTO = new LocalidadDestinoDTO();

		localidadDTO.setId(localidad.getId());
		localidadDTO.setNombre(localidad.getNombre());
		localidadDTO.setProvinciaDestinoDTO(ProviderDTO
				.getProvinciaDestinoDTO(localidad.getProvinciaDestino()));

		return localidadDTO;
	}
}
