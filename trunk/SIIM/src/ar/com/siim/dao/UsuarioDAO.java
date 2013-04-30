package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.negocio.Usuario;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.utils.Constantes;

public class UsuarioDAO extends HibernateDaoSupport {

	public void altaUsuario(Usuario elUsuario) throws NegocioException {
		if (existeUsuario(elUsuario.getNombreUsuario(), elUsuario.getId())) {
			throw new NegocioException(Constantes.EXISTE_USUARIO);
		}
		this.getHibernateTemplate().saveOrUpdate(elUsuario);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

	public boolean existeUsuario(String nombre, Long id) {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.eq("nombreUsuario", nombre));
		if (id != null) {
			conj.add(Restrictions.ne("id", id));
		}
		criteria.add(conj);

		List<Usuario> usuarios = criteria.list();
		return (usuarios.size() > 0);
	}

	public List<Usuario> getUsuarios() {
		Criteria criteria = getSession().createCriteria(Usuario.class);
		criteria.createAlias("rol", "r");
		
		Conjunction conj = Restrictions.conjunction();
		conj.add(Restrictions.ne("r.id", 1L));
		
		criteria.add(conj);

		List<Usuario> usuarios = criteria.list(); 
		return usuarios;
	}

	public Usuario getUsuario(Long id) {
		return (Usuario) this.getHibernateTemplate().get(Usuario.class, id);

	}

	public void modificacionUsuario(Usuario elUsuario) throws NegocioException {
		if (existeUsuario(elUsuario.getNombreUsuario(), elUsuario.getId())) {
			throw new NegocioException(Constantes.EXISTE_USUARIO);
		}
		this.getHibernateTemplate().saveOrUpdate(elUsuario);
		this.getHibernateTemplate().flush();
		this.getHibernateTemplate().clear();
	}

}
