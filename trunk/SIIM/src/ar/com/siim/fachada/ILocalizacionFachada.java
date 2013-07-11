package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.negocio.Localizacion;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.dto.EstudioImpactoAmbientalDTO;
import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.enums.EstadoEIA;

public interface ILocalizacionFachada {

	public void altaLocalizacion(LocalizacionDTO localizacionDTO) throws NegocioException;
	
	public void modificacionLocalizacion(LocalizacionDTO localizacionDTO, EstudioImpactoAmbientalDTO eiaDTO) throws NegocioException;
	
	public List<LocalizacionDTO> getLocalizacionesPorProductorDTO(Long idProductor) throws NegocioException;
	
	public LocalizacionDTO getLocalizacionDTOPorId(long idLocalizacion);
	
	public Localizacion getLocalizacion (Long idLocalizacion);
	
	public List<EstadoEIA> recuperarEstadosEIA();
}
