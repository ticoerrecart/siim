package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dto.BoletaDepositoDTO;
import ar.com.siim.dto.CanonMineroDTO;
import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.negocio.CanonMinero;

public interface ICanonMineroFachada {

	public int recuperarCanonMineroXPertenencia();

	public void modificacionValorCanonMinero(double valor);

	public void altaCanonMinero(CanonMineroDTO canonMineroDTO,
			List<BoletaDepositoDTO> boletasDeposito);

	public boolean existeCanonMinero(LocalizacionDTO yacimiento, String periodo);
	
	public CanonMinero getCanonMinero(Long idYacimiento, String periodo);
	
	public CanonMinero getCanonMinero(Long idCanonMinero);
}
