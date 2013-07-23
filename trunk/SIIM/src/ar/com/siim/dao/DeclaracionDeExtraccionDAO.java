package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.dto.DeclaracionExtraccionDTO;
import ar.com.siim.negocio.DeclaracionDeExtraccion;

public class DeclaracionDeExtraccionDAO extends HibernateDaoSupport {

	public String existeDeclaracionExtraccion(
			DeclaracionExtraccionDTO declaracion) {
		String error = "";
		Criteria criteria = getSession().createCriteria(
				DeclaracionDeExtraccion.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("numero", declaracion.getNumero()));
		if (declaracion.getId() != null) {
			conj.add(Restrictions.ne("id", declaracion.getId()));
		}
		criteria.add(conj);

		List<DeclaracionDeExtraccion> declaracionesDeExtraccion = criteria
				.list();
		if (declaracionesDeExtraccion.size() > 0) {
			error = "Ya existe una Declaración de Extracción con éste número.";
		}

		return error;

	}

	public void altaDeclaracionExtraccion(DeclaracionDeExtraccion declaracion) {
		this.getHibernateTemplate().saveOrUpdate(declaracion);
		this.getHibernateTemplate().flush();
	}
}
