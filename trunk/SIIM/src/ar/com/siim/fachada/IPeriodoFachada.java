package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.negocio.Periodo;
import ar.com.siim.negocio.exception.NegocioException;

public interface IPeriodoFachada {

	public List<Periodo> getPeriodos();

	public Periodo getPeriodoPorId(Long id);

	public boolean existePeriodo(PeriodoDTO periodo);

	public void altaPeriodo(PeriodoDTO periodoDTO) throws NegocioException;

	public List<PeriodoDTO> getPeriodosDTO();

	public PeriodoDTO getPeriodoDTOPorId(Long id);

	public PeriodoDTO getPeriodoDTOPorPeriodo(String periodo);

	public void modificacionPeriodo(PeriodoDTO periodoDTO)
			throws NegocioException;
}
