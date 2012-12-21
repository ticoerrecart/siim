package ar.com.siim.fachada;

import java.util.List;

import ar.com.siim.dao.MenuDAO;
import ar.com.siim.negocio.ItemMenu;
import ar.com.siim.negocio.exception.DataBaseException;
import ar.com.siim.negocio.exception.NegocioException;

public class MenuFachada implements IMenuFachada {

	private MenuDAO menuDAO;

	/**
	 * Constructor por defecto.
	 * 
	 */
	public MenuFachada() {
	}

	public MenuFachada(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public List<ItemMenu> getItemsMenu(String pRol) throws NegocioException {

		try{
			return menuDAO.getItemsMenu(pRol);
		
		} catch (DataBaseException e) {
			throw new NegocioException(e.getMessage());
		}			
	}

}
