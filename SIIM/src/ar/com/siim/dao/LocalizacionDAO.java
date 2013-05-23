package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.negocio.Localizacion;

public class LocalizacionDAO extends HibernateDaoSupport {

	public void altaModificacionLocalizacion(Localizacion localizacion){
		
		this.getHibernateTemplate().saveOrUpdate(localizacion);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();		
	}
	
	public boolean existeLocalizacion(String razonSocial, long idProductor, long idLocalizacion){
		
		Criteria criteria = getSession().createCriteria(Localizacion.class);
		criteria.add(Restrictions.eq("razonSocial", razonSocial));
		criteria.add(Restrictions.eq("productor.id", idProductor));
		
		if(idLocalizacion != 0){//En el caso que sea una modificación de localización
			criteria.add(Restrictions.ne("id", idLocalizacion));	
		}
		
		return (criteria.list().size() > 0);		
	}
	
	public List<Localizacion> getLocalizacionesPorProductor(long idProductor){
		
		Criteria criteria = getSession().createCriteria(Localizacion.class);
		criteria.add(Restrictions.eq("productor.id", idProductor));
		return criteria.list();		
	}
	
	public Localizacion getLocalizacionPorId(long idLocalizacion){
				
		return (Localizacion) getHibernateTemplate().get(Localizacion.class, idLocalizacion);		
	}	
}
