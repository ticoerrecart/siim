package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.DeclaracionExtraccionDTO;
import ar.com.siim.dto.TrimestreDeclaracionDeExtraccionDTO;

public interface IDeclaracionDeExtraccionFachada {

	public String existeDeclaracionExtraccion(
			DeclaracionExtraccionDTO declaracion);

	public void altaDeclaracionExtraccion(
			DeclaracionExtraccionDTO declaracionExtraccionDTO,
			List<TrimestreDeclaracionDeExtraccionDTO> trimestresDTO,
			List<BoletaDepositoDTO> boletasDTO);
}
