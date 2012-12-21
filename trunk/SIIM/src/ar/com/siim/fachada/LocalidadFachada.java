package ar.com.siim.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.dao.LocalidadDAO;
import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.exception.DataBaseException;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.providers.ProviderDTO;
import ar.com.siim.providers.ProviderDominio;

public class LocalidadFachada implements ILocalidadFachada {

	private LocalidadDAO localidadDAO;

	public LocalidadFachada() {
	}

	public LocalidadFachada(LocalidadDAO laLocalidaDAO) {
		this.localidadDAO = laLocalidaDAO;
	}

	public List<Localidad> getLocalidades() throws NegocioException {
		try{
			return localidadDAO.getLocalidades();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public Localidad getLocalidadPorId(Long id) throws NegocioException {
		try{
			return localidadDAO.getLocalidadPorId(id);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public boolean existeLocalidad(LocalidadDTO localidad) {
		return localidadDAO.existeLocalidad(localidad.getNombre(),localidad.getId());
	}

	public void altaLocalidad(LocalidadDTO localidadDTO) throws NegocioException {
		try{
			localidadDAO.alta_modficacion_Localidad(ProviderDominio.getLocalidad(localidadDTO));
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}
	
	public List<LocalidadDTO> getLocalidadesDTO() throws NegocioException{
		
		try{
			List<LocalidadDTO> localidadesDTO = new ArrayList<LocalidadDTO>();
			List<Localidad> localidades = localidadDAO.getLocalidades();
			
			for (Localidad localidad : localidades) {
				localidadesDTO.add(ProviderDTO.getLocalidadDTO(localidad));
			}
			
			return localidadesDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public LocalidadDTO getLocalidadDTOPorId(Long id) throws NegocioException{
		
		try{
			Localidad localidad = localidadDAO.getLocalidadPorId(id);
			
			return ProviderDTO.getLocalidadDTO(localidad);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}
	
	public void modificacionLocalidad(LocalidadDTO localidadDTO) throws NegocioException{
		
		try{
			Localidad localidad = localidadDAO.getLocalidadPorId(localidadDTO.getId());	
			localidadDAO.alta_modficacion_Localidad(ProviderDominio.getLocalidad(localidad,localidadDTO));

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}
}
