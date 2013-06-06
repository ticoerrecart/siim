package ar.com.siim.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.dao.EntidadDAO;
import ar.com.siim.dto.EntidadDTO;
import ar.com.siim.enums.TipoDeEntidad;
import ar.com.siim.enums.TipoDocumento;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.exception.DataBaseException;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.providers.ProviderDTO;
import ar.com.siim.providers.ProviderDominio;
import ar.com.siim.utils.MyLogger;

public class EntidadFachada implements IEntidadFachada {

	private EntidadDAO entidadDAO;
	private ILocalidadFachada localidadFachada;
	
	public EntidadFachada() {

	}

	public EntidadFachada(EntidadDAO laEntidadDAO, ILocalidadFachada pLocalidadFachada) {
		this.entidadDAO = laEntidadDAO;
		this.localidadFachada = pLocalidadFachada;
	}

	public void altaEntidad(EntidadDTO entidadDTO)throws NegocioException {

		Localidad localidad = localidadFachada.getLocalidadPorId(entidadDTO.getIdLocalidad());
		entidadDAO.altaEntidad(ProviderDominio.getEntidad(entidadDTO,localidad));	
	}

	public List<Entidad> getEntidades(){

		return entidadDAO.getEntidades();
	}

	public Entidad getEntidad(Long id){

		return entidadDAO.getEntidad(id);	
	}

	public boolean existeEntidad(String nombre, Long id) {
		return entidadDAO.existeEntidad(nombre, id);
	}

	public List<Entidad> getEntidadesPorLocalidad(Long idLocalidad) throws NegocioException{
		try{
			return entidadDAO.getEntidades(idLocalidad);
		
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}			
	}

	public List<TipoDeEntidad> getTiposDeEntidad() {
		List<TipoDeEntidad> tiposDeEntidad = new ArrayList<TipoDeEntidad>();
		tiposDeEntidad.add(TipoDeEntidad.RN);
		tiposDeEntidad.add(TipoDeEntidad.PRD);

		return tiposDeEntidad;
	}	
	
	public List<TipoDeEntidad> getTiposDeEntidadProductores() {
		List<TipoDeEntidad> tiposDeEntidad = new ArrayList<TipoDeEntidad>();
		tiposDeEntidad.add(TipoDeEntidad.PRD);
		
		return tiposDeEntidad;
	}
	
	public List<Entidad> getEntidadesPorTipoDeEntidad(String tipoDeEntidad){

		return entidadDAO.getEntidades(TipoDeEntidad.valueOf(tipoDeEntidad));			
	}

	public List<Entidad> getOficinas(){

		return entidadDAO.getOficinas();
			
	}	
	
	public List<EntidadDTO> getOficinasDTO(){

		List<EntidadDTO> oficianasDTO = new ArrayList<EntidadDTO>();
		List<Entidad> oficinas = entidadDAO.getOficinas();
		
		for (Entidad entidad : oficinas) {
			oficianasDTO.add(ProviderDTO.getEntidadDTO(entidad));
		}
		return oficianasDTO;
			
	}	
	
	public List<EntidadDTO> getEntidadesPorTipoDeEntidadDTO(String tipoDeEntidad) throws NegocioException{

		List<EntidadDTO> entidadesDTO = null;
		try{
			
			entidadesDTO = new ArrayList<EntidadDTO>();
			List<Entidad> entidades = entidadDAO.getEntidades(TipoDeEntidad.valueOf(tipoDeEntidad));
				
			for (Entidad entidad : entidades) {
				entidadesDTO.add(ProviderDTO.getEntidadDTO(entidad));
			}			
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}	
		return entidadesDTO;	
	}	
	
	public List<EntidadDTO> getEntidadesDTO(){

		List<EntidadDTO> listaEntidadesDTO = new ArrayList<EntidadDTO>();
		List<Entidad> listaEntidades = entidadDAO.getEntidades();
		
		for (Entidad entidad : listaEntidades) {
			listaEntidadesDTO.add(ProviderDTO.getEntidadDTO(entidad));
		}
		
		return listaEntidadesDTO;		
	}	
	
	public EntidadDTO getEntidadDTO(Long id) throws NegocioException{
		try{
			return ProviderDTO.getEntidadDTO(entidadDAO.getEntidad(id));
	
		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}			
	}	
	
	public void modificacionEntidad(EntidadDTO entidadDTO){

		Entidad entidad = entidadDAO.getEntidad(entidadDTO.getId());
		Localidad localidad = localidadFachada.getLocalidadPorId(entidadDTO.getIdLocalidad());
		
		entidadDAO.modificacionEntidad(ProviderDominio.getEntidad(entidad, entidadDTO, localidad));	
	}
	
	public List<EntidadDTO> getProductoresDTO(){

		List<EntidadDTO> listaEntidadesDTO = new ArrayList<EntidadDTO>();
		List<Entidad> listaEntidades = entidadDAO.getProductores();
		
		for (Entidad entidad : listaEntidades) {
			listaEntidadesDTO.add(ProviderDTO.getEntidadDTO(entidad));
		}
		return listaEntidadesDTO;
	}
	
	public List<TipoDocumento> recuperarTiposDocumento(){
		
		List<TipoDocumento> lista = new ArrayList<TipoDocumento>();
		
		for(int i=0;i<TipoDocumento.values().length;i++){
			lista.add(TipoDocumento.values()[i]);
		}
		
		return lista;
	}
}
