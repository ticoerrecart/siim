package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.DeclaracionExtraccionDTO;
import ar.com.siim.dto.TrimestreDeclaracionDeExtraccionDTO;
import ar.com.siim.negocio.DeclaracionDeExtraccion;

public interface IDeclaracionDeExtraccionFachada {

	public String existeDeclaracionExtraccion(
			DeclaracionExtraccionDTO declaracion);

	public void altaDeclaracionExtraccion(
			DeclaracionExtraccionDTO declaracionExtraccionDTO,
			List<TrimestreDeclaracionDeExtraccionDTO> trimestresDTO,
			List<BoletaDepositoDTO> boletasDTO);

	public DeclaracionDeExtraccion getDeclaracionDeExtraccionById(Long id);

	public DeclaracionDeExtraccion getDeclaracionDeExtraccion(Long idEntidad,
			Long idLocalizacion, String idPeriodo, boolean sinAnuladas);
}
