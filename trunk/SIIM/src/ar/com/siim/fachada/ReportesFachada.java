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
}
