package ar.com.siim.struts.actions.forms;

import org.apache.struts.action.ActionForm;

import ar.com.siim.dto.TipoProductoDTO;

public class TipoProductoForm extends ActionForm {

	private TipoProductoDTO tipoProducto;
	
	public TipoProductoForm(){
		
		tipoProducto = new TipoProductoDTO();
	}

	public TipoProductoDTO getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(TipoProductoDTO tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	
}
