package ar.com.siim.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siim.dao.ReportesDAO;
import ar.com.siim.utils.Constantes;

public class ReportesDeclaracionExtraccionFachada implements
		IReportesDeclaracionExtraccionFachada {


	private ReportesDAO reportesDAO;
	
	public ReportesDeclaracionExtraccionFachada(){}
	
	public ReportesDeclaracionExtraccionFachada(ReportesDAO pReportesDAO){
		
		this.reportesDAO = pReportesDAO;
	}
	
	@Override	
	public byte[] generarReporteEstadoDeudaDeclaracionExtraccionPorProductorLocalizacionYPeriodo(
			String path,String periodo,Long idProductor,Long idLocalizacion) throws Exception{
		
		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);		
		parameters.put("idProductor", idProductor);
		parameters.put("idLocalizacion", idLocalizacion);
		parameters.put("periodo", periodo);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_ESTADO_DEUDA_DECLARACION_EXTRACCION_POR_PRODUCTOR,
				parameters);				
	}	
}
