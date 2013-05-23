package ar.com.siim.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.utils.MyLogger;
import ar.com.siim.utils.Constantes;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.dao.LocalizacionDAO;
import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.Localizacion;
import ar.com.siim.providers.ProviderDTO;
import ar.com.siim.providers.ProviderDominio;

public class LocalizacionFachada implements ILocalizacionFachada {

	private LocalizacionDAO localizacionDAO;
	private IEntidadFachada entidadFachada;
	
	public LocalizacionFachada(){}
	
	public LocalizacionFachada(LocalizacionDAO pLocalizacionDAO, IEntidadFachada pEntidadFachada){
		
		this.localizacionDAO = pLocalizacionDAO;
		this.entidadFachada = pEntidadFachada;
	}
	
	public LocalizacionDAO getLocalizacionDAO() {
		return localizacionDAO;
	}

	public void setLocalizacionDAO(LocalizacionDAO localizacionDAO) {
		this.localizacionDAO = localizacionDAO;
	}

	public IEntidadFachada getEntidadFachada() {
		return entidadFachada;
	}

	public void setEntidadFachada(IEntidadFachada entidadFachada) {
		this.entidadFachada = entidadFachada;
	}

	public void altaLocalizacion(LocalizacionDTO localizacionDTO) throws NegocioException{
		
		if(localizacionDAO.existeLocalizacion(localizacionDTO.getRazonSocial(),
											  localizacionDTO.getProductor().getId(),
											  localizacionDTO.getId()))
		{
			throw new NegocioException(Constantes.ERROR_EXISTE_LOCALIZACION);
		}
		Entidad productor = entidadFachada.getEntidad(localizacionDTO.getProductor().getId());
		localizacionDAO.altaModificacionLocalizacion(ProviderDominio.getLocalizacion(localizacionDTO, productor));
	}

	public void modificacionLocalizacion(LocalizacionDTO localizacionDTO) throws NegocioException{
		
		if(localizacionDAO.existeLocalizacion(localizacionDTO.getRazonSocial(),
											  localizacionDTO.getProductor().getId(),
											  localizacionDTO.getId()))
		{
			throw new NegocioException(Constantes.ERROR_EXISTE_LOCALIZACION);
		}
		Localizacion localizacion = localizacionDAO.getLocalizacionPorId(localizacionDTO.getId());
		localizacion.setExpediente(localizacionDTO.getExpediente());
		localizacion.setRazonSocial(localizacionDTO.getRazonSocial());
		localizacion.setResolucion(localizacionDTO.getResolucion());
		localizacion.setSuperficie(localizacionDTO.getSuperficie());
		
		localizacionDAO.altaModificacionLocalizacion(localizacion);
	}	
	
	public List<LocalizacionDTO> getLocalizacionesPorProductorDTO(Long idProductor) throws NegocioException {
		try {
			List<Localizacion> localizaciones = localizacionDAO.getLocalizacionesPorProductor(idProductor);
			List<LocalizacionDTO> localizacionesDTO = new ArrayList<LocalizacionDTO>();
			for (Localizacion localizacion : localizaciones) {
				localizacionesDTO.add(ProviderDTO.getLocalizacionDTO(localizacion));
			}
			return localizacionesDTO;

		} catch (Throwable t) {
			MyLogger.logError(t);
			throw new NegocioException("Error Inesperado");
		}
	}
	
	public LocalizacionDTO getLocalizacionDTOPorId(long idLocalizacion){
		
		return ProviderDTO.getLocalizacionDTO(localizacionDAO.getLocalizacionPorId(idLocalizacion));
	}
}
