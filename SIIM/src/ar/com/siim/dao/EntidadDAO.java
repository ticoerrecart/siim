package ar.com.siim.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import ar.com.siim.enums.TipoDeEntidad;
import ar.com.siim.negocio.Entidad;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.Productor;

import ar.com.siim.negocio.RecursosNaturales;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.utils.Constantes;

public class EntidadDAO extends HibernateDaoSupport {

	public void altaEntidad(Entidad laEntidad) throws NegocioException{

		if (existeEntidad(laEntidad.getNombre(), laEntidad.getId())) {
			throw new NegocioException(Constantes.EXISTE_ENTIDAD);
		}
		this.getHibernateTemplate().saveOrUpdate(laEntidad);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();			
	}

	public boolean existeEntidad(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(Entidad.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombre", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<Entidad> entidades = criteria.list();
		return (entidades.size() > 0);
	}

	public Entidad getEntidadPorNombre(String nombre){

		Criteria criteria = getSession().createCriteria(Entidad.class);
		criteria.add(Restrictions.eq("nombre", nombre));

		List<Entidad> entidades = criteria.list();
		if (entidades.size() > 0) {
			return entidades.get(0);
		} else {
			return null;
		}
	}

	public List<Entidad> getEntidades(){

		return getHibernateTemplate().loadAll(Entidad.class);	
	}

	public Entidad getEntidad(Long id){

		return (Entidad) getHibernateTemplate().get(Entidad.class, id);			
	}

	public List<Entidad> getEntidades(Long idLocalidad){

		Localidad localidad = (Localidad) getHibernateTemplate().get(Localidad.class, idLocalidad);

		Criteria criteria = getSession().createCriteria(Productor.class);
		criteria.add(Restrictions.eq("localidad", localidad));
		List<Entidad> productores = criteria.list();

		return productores;
	}

	public List<Entidad> getEntidades(TipoDeEntidad tipoDeEntidad){

		List<Entidad> productores = null;
		/*Criteria criteria = null;
		if (tipoDeEntidad == TipoDeEntidad.PRD) {
			criteria = getSession().createCriteria(Productor.class);
			productores = criteria.list();
		}*/
		
		Criteria criteria = getSession().createCriteria(tipoDeEntidad.getClase());
		productores = criteria.list();
		
		return productores;		
	}
	
	public List<Entidad> getOficinas(){

		List<Entidad> oficinasForestales = null;		
		Criteria criteria = getSession().createCriteria(RecursosNaturales.class);
		oficinasForestales = criteria.list();
		
		return oficinasForestales;
	}

	public void modificacionEntidad(Entidad entidad){

		this.getHibernateTemplate().saveOrUpdate(entidad);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}	
	
	public List<Entidad> getProductores(){
	
		Criteria criteria = getSession().createCriteria(Productor.class);
		List<Entidad> productores = criteria.list();

		return productores;		
	}
}
