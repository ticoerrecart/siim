package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.dto.LocalizacionDTO;

public interface ILocalizacionFachada {

	public void altaLocalizacion(LocalizacionDTO localizacionDTO) throws NegocioException;
	
	public void modificacionLocalizacion(LocalizacionDTO localizacionDTO) throws NegocioException;
	
	public List<LocalizacionDTO> getLocalizacionesPorProductorDTO(Long idProductor) throws NegocioException;
	
	public LocalizacionDTO getLocalizacionDTOPorId(long idLocalizacion);
}
