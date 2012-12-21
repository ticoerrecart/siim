package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.negocio.ItemMenu;
import ar.com.siim.negocio.exception.NegocioException;

public interface IMenuFachada {

	public List<ItemMenu> getItemsMenu(String rol)throws NegocioException;
}
