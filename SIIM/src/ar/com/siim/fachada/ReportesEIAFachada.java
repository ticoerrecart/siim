package ar.com.siim.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siim.dao.ReportesDAO;
import ar.com.siim.utils.Constantes;

public class ReportesEIAFachada implements IReportesEIAFachada {

	private ReportesDAO reportesDAO;
	
	public ReportesEIAFachada(){}
	
	public ReportesEIAFachada(ReportesDAO pReportesDAO){
		
		this.reportesDAO = pReportesDAO;
	}
	
	@Override
	public byte[] generarReporteEstadoEIA(String path) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);

		return reportesDAO.generarReporte(
				Constantes.REPORTE_ESTADO_EIA,
				parameters);
	}
}
