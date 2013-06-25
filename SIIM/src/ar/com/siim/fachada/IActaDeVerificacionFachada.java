package ar.com.siim.fachada;

import ar.com.siim.dto.ActaDeVerificacionDTO;
import ar.com.siim.negocio.exception.NegocioException;

public interface IActaDeVerificacionFachada {
	public void altaActaDeVerificacion(ActaDeVerificacionDTO actaDTO) throws NegocioException;

	public boolean existeActaDeVerificacion(long numero);
}
