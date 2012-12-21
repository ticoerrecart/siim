package ar.com.siim.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.dao.PeriodoDAO;
import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.negocio.Periodo;
import ar.com.siim.negocio.exception.DataBaseException;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.providers.ProviderDTO;
import ar.com.siim.providers.ProviderDominio;

public class PeriodoFachada implements IPeriodoFachada {

	private PeriodoDAO PeriodoDAO;

	public PeriodoFachada() {
	}

	public PeriodoFachada(PeriodoDAO laPeriodoAO) {
		this.PeriodoDAO = laPeriodoAO;
	}

	public List<Periodo> getPeriodos() throws NegocioException {
		try{
			return PeriodoDAO.getPeriodos();
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public Periodo getPeriodoPorId(Long id) throws NegocioException {
		try{
			return PeriodoDAO.getPeriodoPorId(id);

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

	public boolean existePeriodo(PeriodoDTO Periodo) {
		return PeriodoDAO.existePeriodo(Periodo.getPeriodo(),Periodo.getId());
	}

	public void altaPeriodo(PeriodoDTO PeriodoDTO) throws NegocioException {
		try{
			PeriodoDAO.alta_modficacion_Periodo(ProviderDominio.getPeriodo(PeriodoDTO));
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}
	
	public List<PeriodoDTO> getPeriodosDTO() throws NegocioException{
		
		try{
			List<PeriodoDTO> periodosDTO = new ArrayList<PeriodoDTO>();
			List<Periodo> periodos = PeriodoDAO.getPeriodos();
			
			for (Periodo Periodo : periodos) {
				periodosDTO.add(ProviderDTO.getPeriodoDTO(Periodo));
			}
			
			return periodosDTO;
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}	
	
	public PeriodoDTO getPeriodoDTOPorId(Long id) throws NegocioException{
		
		try{
			Periodo Periodo = PeriodoDAO.getPeriodoPorId(id);
			
			return ProviderDTO.getPeriodoDTO(Periodo);
			
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}
	
	public void modificacionPeriodo(PeriodoDTO PeriodoDTO) throws NegocioException{
		
		try{
			Periodo Periodo = PeriodoDAO.getPeriodoPorId(PeriodoDTO.getId());	
			PeriodoDAO.alta_modficacion_Periodo(ProviderDominio.getPeriodo(Periodo,PeriodoDTO));

		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}
}
