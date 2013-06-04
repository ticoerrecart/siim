package ar.com.siim.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class CanonMineroDAO extends HibernateDaoSupport {

	public int recuperarCanonMineroXPertenencia(){
		
		SQLQuery q = getSession().createSQLQuery("select id, valor from CanonMineroXPertenencia");
		List<Object[]> l = q.list();
		
		Object[] a = l.get(0);
		return (Integer)a[1];
	}	
}
