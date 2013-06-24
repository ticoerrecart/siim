package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.negocio.CanonMinero;

public class CanonMineroDAO extends HibernateDaoSupport {

	public int recuperarCanonMineroXPertenencia() {

		SQLQuery q = getSession().createSQLQuery(
				"select id, valor from CanonMineroXPertenencia");
		List<Object[]> l = q.list();

		Object[] a = l.get(0);
		return (Integer) a[1];
	}

	public void modificacionValorCanonMinero(double valor) {

		SQLQuery q = getSession().createSQLQuery(
				"UPDATE CanonMineroXPertenencia SET valor=" + valor
						+ " WHERE id=1;");
		q.executeUpdate();
	}

	public void altaCanonMinero(CanonMinero canonMinero) {
		this.getHibernateTemplate().saveOrUpdate(canonMinero);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public boolean existeCanonMinero(LocalizacionDTO yacimiento, String periodo) {
		Criteria criteria = getSession().createCriteria(CanonMinero.class);
		criteria.add(Restrictions.eq("localizacion.id", yacimiento.getId()));
		criteria.add(Restrictions.eq("periodo", periodo));

		List<CanonMinero> canonMinero = criteria.list();

		return (canonMinero.size() > 0);
	}
}
