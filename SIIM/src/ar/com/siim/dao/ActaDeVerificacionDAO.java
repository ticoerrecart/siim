package ar.com.siim.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.negocio.ActaDeVerificacion;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.utils.DateUtils;

public class ActaDeVerificacionDAO extends HibernateDaoSupport {

	public void altaActaDeVerificacion(ActaDeVerificacion acta) throws NegocioException {
		this.getHibernateTemplate().saveOrUpdate(acta);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	@SuppressWarnings("unchecked")
	public boolean existeActaDeVerificacion(long numero) {
		Criteria criteria = getSession().createCriteria(ActaDeVerificacion.class);
		criteria.add(Restrictions.eq("numero", numero));
		List<ActaDeVerificacion> actas = criteria.list();
		return (actas.size() > 0);
	}

	public List<ActaDeVerificacion> getActas(long idLocalizacion, String periodo) {
		Criteria criteria = getSession().createCriteria(ActaDeVerificacion.class);
		criteria.add(Restrictions.eq("yacimiento.id", idLocalizacion));
		Date desde = DateUtils.dateFromString(periodo + "-01-01 00:00" ); 
		Date hasta = DateUtils.dateFromString(periodo + "-12-31 23:59" );
		criteria.add(Restrictions.between("fechaVerificacion", desde, hasta));
		
		return criteria.list();
	}

}
