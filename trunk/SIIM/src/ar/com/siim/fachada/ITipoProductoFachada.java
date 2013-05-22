package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dto.TipoProductoDTO;
import ar.com.siim.negocio.exception.NegocioException;

public interface ITipoProductoFachada {

	public TipoProductoDTO recuperarTipoProductoDTO(long id);
	
	public List<TipoProductoDTO> recuperarTiposProductoDTO();
	
	public void modificacionTipoProducto(TipoProductoDTO tipoProductoDTO)throws NegocioException;	
}
