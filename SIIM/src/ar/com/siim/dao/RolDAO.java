package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.dto.RolDTO;
import ar.com.siim.negocio.ItemMenu;
import ar.com.siim.negocio.Rol;
import ar.com.siim.negocio.exception.AccesoDenegadoException;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.utils.Constantes;

public class RolDAO extends HibernateDaoSupport {

	public List<Rol> getRoles() {
		Criteria criteria = getSession().createCriteria(Rol.class);

		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.ne("id", 1L));

		criteria.add(conj);

		List<Rol> roles = criteria.list();
		return roles;
	}

	public List<ItemMenu> recuperarMenues() {
		Criteria criteria = getSession().createCriteria(ItemMenu.class);
		criteria.add(Restrictions.conjunction()
				.add(Restrictions.gt("orden", 0)));
		return criteria.list();
	}

	public void altaRol(Rol rol) throws NegocioException {
		Criteria criteria = getSession().createCriteria(Rol.class);
		criteria.add(Restrictions.disjunction().add(
				Restrictions.eq("rol", rol.getRol())));
		List<Rol> listaRol = criteria.list();
		if (listaRol.size() > 0) {
			throw new NegocioException(Constantes.EXISTE_ROL);
		}
		this.getHibernateTemplate().save(rol);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();

	}

	public Rol recuperarRol(long idRol) {
		return (Rol) getSession().get(Rol.class, idRol);
	}

	public void modificacionRol(Rol rol) throws NegocioException {
		Criteria criteria = getSession().createCriteria(Rol.class);
		criteria.add(
				Restrictions.conjunction().add(
						Restrictions.eq("rol", rol.getRol()))).add(
				Restrictions.ne("id", rol.getId()));

		List<Rol> roles = criteria.list();

		if (roles.size() > 0) {
			throw new NegocioException(Constantes.EXISTE_ROL);
		}

		this.getHibernateTemplate().saveOrUpdate(rol);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();

	}

	public ItemMenu getItemMenu(Long idMenu) {
		return (ItemMenu) getHibernateTemplate().get(ItemMenu.class, idMenu);

	}

	public Rol getRol(Long id) {
		return (Rol) this.getHibernateTemplate().get(Rol.class, id);

	}

	public boolean existeRol(RolDTO rol) {
		Criteria criteria = getSession().createCriteria(Rol.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("rol", rol.getRol()));
		if (rol.getId() != null) {
			conj.add(Restrictions.ne("id", rol.getId()));
		}
		criteria.add(conj);

		List<Rol> roles = criteria.list();
		return (roles.size() > 0);
	}

	public void verificarMenu(String pNombreMenu, Rol pRol)
			throws AccesoDenegadoException {
		Rol rol = (Rol) getHibernateTemplate().load(Rol.class, pRol.getId());
		for (ItemMenu item : rol.getMenues()) {
			if (item.getItem().equals(pNombreMenu)) {
				return;
			}
		}
		throw new AccesoDenegadoException("El rol " + pRol.getRol()
				+ " no puede ejecutar la acción: " + pNombreMenu);
	}

	public Rol getRolAdministrador() {
		Criteria criteria = getSession().createCriteria(Rol.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("rol", Constantes.ADMINISTRADOR));
		criteria.add(conj);
		List<Rol> roles = criteria.list();
		return roles.get(0);

	}

}
