package ar.com.siim.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.negocio.TipoProducto;

public class TipoProductoDAO extends HibernateDaoSupport{

	public TipoProducto recuperarTipoProducto(long id) {
		
		return (TipoProducto) getSession().get(TipoProducto.class, id);
	}	
}
