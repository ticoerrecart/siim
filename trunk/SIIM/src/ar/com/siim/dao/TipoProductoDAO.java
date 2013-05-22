package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import ar.com.siim.negocio.TipoProducto;

public class TipoProductoDAO extends HibernateDaoSupport{

	public TipoProducto recuperarTipoProducto(long id) {
		
		return (TipoProducto) getSession().get(TipoProducto.class, id);
	}	
	
	public List<TipoProducto> recuperarTiposProducto() {
		Criteria criteria = getSession().createCriteria(TipoProducto.class);
		List<TipoProducto> tiposProducto = criteria.list();
		
		return tiposProducto;
	}
	
	public void modificacionTipoProducto(TipoProducto tipoProducto){

		this.getHibernateTemplate().saveOrUpdate(tipoProducto);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();		
	}
}
