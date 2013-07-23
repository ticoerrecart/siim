package ar.com.siim.dto;

import java.util.ArrayList;
import java.util.List;

public class VolumenDeclaracionDeExtraccionDTO {

	private Long id;

	private DeclaracionExtraccionDTO declaracionDeExtraccion;

	private List<TrimestreDeclaracionDeExtraccionDTO> trimestres = new ArrayList<TrimestreDeclaracionDeExtraccionDTO>();

	private List<BoletaDepositoDTO> boletas = new ArrayList<BoletaDepositoDTO>();

}
