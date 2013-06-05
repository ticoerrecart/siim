package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateSystemException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.negocio.LocalidadDestino;
import ar.com.siim.negocio.ProvinciaDestino;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.utils.Constantes;

public class LocalidadDAO extends HibernateDaoSupport {

	public List<Localidad> getLocalidades(){

		return getHibernateTemplate().loadAll(Localidad.class);
	}

	public Localidad getLocalidadPorId(Long id){

		return (Localidad) getHibernateTemplate().get(Localidad.class, id);		
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

	public void alta_modficacion_Localidad(Localidad localidad) throws NegocioException {

		try{
			if (existeLocalidad(localidad.getNombre(), localidad.getId())) {
				throw new NegocioException(Constantes.EXISTE_LOCALIDAD);
			}
			this.getHibernateTemplate().saveOrUpdate(localidad);
			this.getHibernateTemplate().flush();
			this.getHibernateTemplate().clear();
			
		} catch (HibernateException he) {
			throw new NegocioException(Constantes.ERROR_ALTA_LOCALIDAD);
		} catch (HibernateSystemException he) {
			throw new NegocioException(Constantes.ERROR_ALTA_LOCALIDAD);
		} catch (Exception e) {
			throw new NegocioException(Constantes.ERROR_ALTA_LOCALIDAD);
		}			
	}

	public void alta_modficacion_Provincia(ProvinciaDestino provincia) throws NegocioException {

		if (existeProvincia(provincia.getNombre(), provincia.getId())) {
			throw new NegocioException(Constantes.EXISTE_PROVINCIA);
		}
		this.getHibernateTemplate().saveOrUpdate(provincia);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();		
	}	
	
	public boolean existeProvincia(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(ProvinciaDestino.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<ProvinciaDestino> provincias = criteria.list();
		return (provincias.size() > 0);
	}
	
	public List<ProvinciaDestino> getProvincias(){

		return getHibernateTemplate().loadAll(ProvinciaDestino.class);	
	}
	
	public ProvinciaDestino getProvinciaDestinoPorId(Long id){

		return (ProvinciaDestino) getHibernateTemplate().get(ProvinciaDestino.class, id);			
	}
	
	public boolean existeLocalidadDestino(String nombre, Long id, Long idProvincia) {
		Criteria criteria = getSession().createCriteria(LocalidadDestino.class);
		criteria.createAlias("provinciaDestino", "pd");
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		conj.add(Restrictions.eq("pd.id", idProvincia));
		
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<LocalidadDestino> localidades = criteria.list();
		return (localidades.size() > 0);
	}	
	
	public void alta_modficacion_LocalidadDestino(LocalidadDestino localidad) throws NegocioException {

		if (existeLocalidadDestino(localidad.getNombre(), localidad.getId(),localidad.getProvinciaDestino().getId())) {
			throw new NegocioException(Constantes.EXISTE_LOCALIDAD_DESTINO);
		}
		this.getHibernateTemplate().saveOrUpdate(localidad);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();			
	}	
	
	public List<LocalidadDestino> getLocalidadesDeProvincia(Long idProvincia){

		Criteria criteria = getSession().createCriteria(LocalidadDestino.class);
		criteria.createAlias("provinciaDestino", "pd");
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("pd.id", idProvincia));
		criteria.add(conj);
		
		List<LocalidadDestino> localidades = criteria.list();
		return localidades;		
	}
	
	public LocalidadDestino getLocalidadDestinoPorId(Long id){

		return (LocalidadDestino) getHibernateTemplate().get(LocalidadDestino.class, id);			
	}	
}
