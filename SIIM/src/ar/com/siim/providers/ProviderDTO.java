package ar.com.siim.providers;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.negocio.Periodo;
import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.dto.ItemMenuDTO;
import ar.com.siim.dto.RolDTO;
import ar.com.siim.negocio.ItemMenu;
import ar.com.siim.negocio.Rol;
import ar.com.siim.providers.ProviderDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.negocio.Usuario;



public abstract class ProviderDTO {
				
		public static UsuarioDTO getUsuarioDTO(Usuario usuario){
						
			UsuarioDTO usuarioDTO = new UsuarioDTO();
			
			usuarioDTO.setId(usuario.getId());
			usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
			usuarioDTO.setPassword(usuario.getPassword());
			usuarioDTO.setRol(ProviderDTO.getRolDTO(usuario.getRol()));
			//usuarioDTO.setEntidad(ProviderDTO.getEntidadDTO(usuario.getEntidad()));
			usuarioDTO.setHabilitado(usuario.isHabilitado());
			
			return usuarioDTO;
		}
		
		public static RolDTO getRolDTO(Rol rol){
			
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
		
		public static ItemMenuDTO getItemMenuDTO(ItemMenu menu){
			return ProviderDTO.getItemMenuDTO(menu, null);
		}
		
		private static ItemMenuDTO getItemMenuDTO(ItemMenu menu, ItemMenuDTO padre){
			
			if(menu != null){
				List<ItemMenuDTO> listaMenuDTO = new ArrayList<ItemMenuDTO>();
				ItemMenuDTO menuDTO = new ItemMenuDTO();
				
				menuDTO.setId(menu.getId());
				menuDTO.setItem(menu.getItem());
				menuDTO.setOrden(menu.getOrden());
				menuDTO.setPadre(padre);
				menuDTO.setUrl(menu.getUrl());

				for (ItemMenu menuHijo : menu.getHijos()) {
					listaMenuDTO.add(ProviderDTO.getItemMenuDTO(menuHijo,menuDTO));
				}
				menuDTO.setHijos(listaMenuDTO);
				
				return menuDTO;
			}	
			else{
				return null;
			}
		}
		
		public static LocalidadDTO getLocalidadDTO(Localidad localidad){
			
			LocalidadDTO localidadDTO = new LocalidadDTO();
			
			localidadDTO.setId(localidad.getId());
			localidadDTO.setNombre(localidad.getNombre());
			
			return localidadDTO;
		}
		
		public static PeriodoDTO getPeriodoDTO(Periodo periodo){
			
			PeriodoDTO periodoDTO = new PeriodoDTO();
			
			periodoDTO.setId(periodo.getId());
			periodoDTO.setPeriodo(periodo.getPeriodo());
			
			return periodoDTO;
		}		
}
