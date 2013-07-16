package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dto.ActaDeVerificacionDTO;
import ar.com.siim.negocio.ActaDeVerificacion;
import ar.com.siim.negocio.exception.NegocioException;

public interface IActaDeVerificacionFachada {
	public void altaActaDeVerificacion(ActaDeVerificacionDTO actaDTO) throws NegocioException;

	public boolean existeActaDeVerificacion(long numero);

	public List<ActaDeVerificacion> getActas(long idLocalizacion, String periodo);

	public ActaDeVerificacion getActa(long idActa);
}
