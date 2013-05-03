package ar.com.siim.fachada;

import ar.com.siim.dao.TipoProductoDAO;
import ar.com.siim.dto.TipoProductoDTO;
import ar.com.siim.negocio.TipoProducto;
import ar.com.siim.providers.ProviderDTO;

public class TipoProductoFachada implements ITipoProductoFachada {

	private TipoProductoDAO tipoProductoDAO;
	
	public TipoProductoFachada(){}
	
	public TipoProductoFachada(TipoProductoDAO pTipoProductoDAO){
		this.tipoProductoDAO = pTipoProductoDAO;
	}
	
	@Override
	public TipoProductoDTO recuperarTipoProductoDTO(long id) {

		TipoProducto tipoProducto = tipoProductoDAO.recuperarTipoProducto(id);
		
		return ProviderDTO.getTipoProductoDTO(tipoProducto);
	}

}
