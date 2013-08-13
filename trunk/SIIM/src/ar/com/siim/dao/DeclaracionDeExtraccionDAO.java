package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.dto.DeclaracionExtraccionDTO;
import ar.com.siim.negocio.BoletaDeposito;
import ar.com.siim.negocio.DeclaracionDeExtraccion;
import ar.com.siim.utils.DateUtils;

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
		} else {
			Criteria criteria2 = getSession().createCriteria(
					DeclaracionDeExtraccion.class);
			criteria2.createAlias("entidad", "e");
			criteria2.createAlias("localizacion", "l");
			Conjunction conj2 = Restrictions.conjunction();
			conj2.add(Restrictions.eq("periodo", declaracion.getPeriodo()));
			conj2.add(Restrictions.eq("e.id", declaracion.getProductor()
					.getId()));
			conj2.add(Restrictions.eq("l.id", declaracion.getLocalizacion()
					.getId()));

			criteria2.add(conj2);
			declaracionesDeExtraccion = criteria2.list();
			if (declaracionesDeExtraccion.size() > 0) {
				error = "Ya existe una Declaración de Extracción con éste Productor-Año de Declaración-Localización.";
			}
		}

		return error;

	}

	public void altaDeclaracionExtraccion(DeclaracionDeExtraccion declaracion) {
		this.getHibernateTemplate().saveOrUpdate(declaracion);
		this.getHibernateTemplate().flush();
	}

	public DeclaracionDeExtraccion getDeclaracionDeExtraccionById(Long id) {
		return (DeclaracionDeExtraccion) this.getHibernateTemplate().get(
				DeclaracionDeExtraccion.class, id);
	}

	public DeclaracionDeExtraccion getDeclaracionDeExtraccion(Long idEntidad,
			Long idLocalizacion, String idPeriodo, boolean sinAnuladas) {
		Criteria criteria2 = getSession().createCriteria(
				DeclaracionDeExtraccion.class);
		criteria2.createAlias("entidad", "e");
		criteria2.createAlias("localizacion", "l");
		Conjunction conj2 = Restrictions.conjunction();
		conj2.add(Restrictions.eq("periodo", idPeriodo));
		if (sinAnuladas) {
			conj2.add(Restrictions.eq("anulado", false));
		}
		conj2.add(Restrictions.eq("e.id", idEntidad));
		conj2.add(Restrictions.eq("l.id", idLocalizacion));

		criteria2.add(conj2);
		List<DeclaracionDeExtraccion> lista = criteria2.list();
		if (lista.size() > 0) {
			return (DeclaracionDeExtraccion) lista.get(0);
		}
		return null;
	}
	
	public String registrarPagoBoletaDeposito(Long idBoleta, String fechaPago) {
		BoletaDeposito boletaDeposito = (BoletaDeposito) this
				.getHibernateTemplate().get(BoletaDeposito.class, idBoleta);

		boletaDeposito.setFechaPago(DateUtils.dateFromString(fechaPago,
				"dd/MM/yyyy"));
		this.getHibernateTemplate().saveOrUpdate(boletaDeposito);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();

		return fechaPago;
	}	
}
