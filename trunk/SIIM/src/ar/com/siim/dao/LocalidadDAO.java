package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.exception.DataBaseException;
import ar.com.siim.utils.Constantes;

public class LocalidadDAO extends HibernateDaoSupport {

	public List<Localidad> getLocalidades() throws DataBaseException {
		try{
			return getHibernateTemplate().loadAll(Localidad.class);
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDADES);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDADES);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDADES);
		}			
	}

	public Localidad getLocalidadPorId(Long id) throws DataBaseException {
		try{
			return (Localidad) getHibernateTemplate().get(Localidad.class, id);
						
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDAD);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDAD);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_RECUPERACION_LOCALIDAD);
		}			
	}

	public boolean existeLocalidad(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(Localidad.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<Localidad> localidades = criteria.list();
		return (localidades.size() > 0);
	}

	public void alta_modficacion_Localidad(Localidad localidad) throws DataBaseException {

		try{
			if (existeLocalidad(localidad.getNombre(), localidad.getId())) {
				throw new DataBaseException(Constantes.EXISTE_LOCALIDAD);
			}
			this.getHibernateTemplate().saveOrUpdate(localidad);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_LOCALIDAD);
		} catch (HibernateSystemException he) {
			throw new DataBaseException(Constantes.ERROR_ALTA_LOCALIDAD);
		} catch (Exception e) {
			throw new DataBaseException(Constantes.ERROR_ALTA_LOCALIDAD);
		}			
	}

}
