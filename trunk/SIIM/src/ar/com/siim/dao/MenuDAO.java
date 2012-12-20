package ar.com.siim.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.negocio.ItemMenu;
import ar.com.siim.negocio.Rol;
import ar.com.siim.negocio.exception.DataBaseException;
import ar.com.siim.utils.ComparadorItemsMenu;
import ar.com.siim.utils.Constantes;

public class MenuDAO extends HibernateDaoSupport {

	@SuppressWarnings("unchecked")
	public List<ItemMenu> getItemsMenu(String pRol) throws DataBaseException {
		
		try{
			Criteria criteria = getSession().createCriteria(Rol.class);
			String[] rol = { pRol };
			criteria.add(Restrictions.in("rol", rol));
			List<Rol> roles = criteria.list();
	
			List<ItemMenu> items = null;
			for (Rol r : roles) {
				items = r.getMenues();
			}
			Collections.sort(items, new ComparadorItemsMenu());
			HashMap<Long, ItemMenu> hashMenu = new HashMap<Long, ItemMenu>();
			for (ItemMenu itemMenu : items) {
	
				ItemMenu iMenu = new ItemMenu();
				iMenu.setId(itemMenu.getId());
				iMenu.setItem(itemMenu.getItem());
				iMenu.setOrden(itemMenu.getOrden());
				iMenu.setPadre(itemMenu.getPadre());
				iMenu.setUrl(itemMenu.getUrl());
				iMenu.setHijos(new ArrayList<ItemMenu>());
	
				if (iMenu.getPadre() == null) {
					hashMenu.put(iMenu.getId(), iMenu);
				} else {
					ItemMenu padreMenu = hashMenu.get(iMenu.getPadre().getId());
					padreMenu.getHijos().add(iMenu);
	
					if (itemMenu.getHijos() != null && itemMenu.getHijos().size() > 0) {//Si el item tiene hijos lo tengo q poner en el Map
						hashMenu.put(iMenu.getId(), iMenu);
					}
				}
	
			}
			List<ItemMenu> listaMenues = new ArrayList<ItemMenu>();
			listaMenues.addAll(hashMenu.values());
	
			return listaMenues;
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_MENU);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_MENU);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_MENU);
		}			
	}
}
