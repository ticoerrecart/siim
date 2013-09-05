package ar.com.siim.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siim.dao.ReportesDAO;
import ar.com.siim.utils.Constantes;

public class ReportesFachada implements IReportesFachada {

	private ReportesDAO reportesDAO;
	
	public ReportesFachada(){}
	
	public ReportesFachada(ReportesDAO pReportesDAO){
		
		this.reportesDAO = pReportesDAO;
	}	
	
	@Override
	public byte[] generarReporteVolDeclaradoFiscalizado(
			String path, String periodo, Long idProductor, Long idLocalizacion) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);
		parameters.put("idLocalizacion", idLocalizacion);

		return reportesDAO.generarReporte(Constantes.REPORTE_VOLUMEN_DECLARADO_VERIFICADO,parameters);
	}	
	
	@Override
	public byte[] generarReporteEstadoDeDeudaGeneralPorProductor(
			String path, String periodo, Long idProductor) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);

		return reportesDAO.generarReporte(Constantes.REPORTE_ESTADO_DEUDA_GENERAL_POR_PRODUCTOR,parameters);
	}
	
	public byte[] generarReporteDeclaracionExtraccion(String path, Long idDeclaracion) throws Exception{

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idDeclaracion", idDeclaracion);
		
		return reportesDAO.generarReporte(Constantes.REPORTE_DECLARACION_EXTRACCION,parameters);		
	}
	
	public byte[] generarReporteCanonMinero(String path, Long idCanon) throws Exception{
		
		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idCanonMinero", idCanon);
		
		return reportesDAO.generarReporte(Constantes.REPORTE_CANON_MINERO,parameters);		
	}	

	public byte[] generarReporteActaVerificacion(String path, Long idActa) throws Exception{
		
		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("idActaVerificacion", idActa);
		
		return reportesDAO.generarReporte(Constantes.REPORTE_ACTA_VERIFICACION,parameters);		
	}		
	
	public byte[] generarReporteVolumenFiscalizado(
			String path,String periodo,Long idProductor,Long idLocalizacion) throws Exception{
		
		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);		
		parameters.put("idProductor", idProductor);
		parameters.put("idLocalizacion", idLocalizacion);
		parameters.put("periodo", periodo);

		return reportesDAO.generarReporte(Constantes.REPORTE_VOLUMEN_FISCALIZADO,parameters);		
	}	
}
