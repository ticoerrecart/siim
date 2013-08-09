package ar.com.siim.dao;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.util.JRLoader;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import ar.com.siim.negocio.Reporte;
import ar.com.siim.utils.Fecha;

public class ReportesDAO extends HibernateDaoSupport {

	public byte[] generarReporte(String nombreReporte,Map<String,Object> parameters) throws Exception 
	{
		parameters.put("fechaReporte", Fecha.getFechaHoy());		
		
		InputStream input = obtenerReporte(nombreReporte);
		this.cargarSubReportes(nombreReporte, parameters);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(input);

		return JasperRunManager.runReportToPdf(jasperReport, parameters,
				getSession().connection());
	}	
	
	private InputStream obtenerReporte(String nombreReporte)throws SQLException 
	{
		Criteria criteria = getSession().createCriteria(Reporte.class);
		criteria.add(Restrictions.eq("nombreReporte", nombreReporte));
		
		List<Reporte> lista = criteria.list();
		Reporte r = lista.get(0);
		
		return r.getArchivoReporte().getBinaryStream();
	}

	private void cargarSubReportes(String nombrePadre,
			Map<String, Object> parameters) throws SQLException {

		Criteria criteria = getSession().createCriteria(Reporte.class);
		criteria.add(Restrictions.eq("nombreReportePadre", nombrePadre));

		List<Reporte> lista = criteria.list();
		for (Reporte reporte : lista) {

			parameters.put(reporte.getNombreReporte(), reporte
					.getArchivoReporte().getBinaryStream());
		}
	}	
}
