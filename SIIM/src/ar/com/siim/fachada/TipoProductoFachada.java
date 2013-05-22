package ar.com.siim.fachada;

import java.util.ArrayList;
import java.util.List;

import ar.com.siim.negocio.exception.NegocioException;
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

	public List<TipoProductoDTO> recuperarTiposProductoDTO(){
		
		List<TipoProductoDTO> tiposProductoDTO = new ArrayList<TipoProductoDTO>();
		List<TipoProducto> tiposProducto = tipoProductoDAO.recuperarTiposProducto();
		for (TipoProducto tipoProducto : tiposProducto) {
			tiposProductoDTO.add(ProviderDTO.getTipoProductoDTO(tipoProducto));
		}
		return tiposProductoDTO;		
	}	
	
	public void modificacionTipoProducto(TipoProductoDTO tipoProductoDTO)throws NegocioException{
		
		TipoProducto tipoProducto = tipoProductoDAO.recuperarTipoProducto(tipoProductoDTO.getId());
		tipoProducto.setRegaliaMinera(tipoProductoDTO.getRegaliaMinera());
		
		tipoProductoDAO.modificacionTipoProducto(tipoProducto);
	}
}
