package ar.com.siim.fachada;

import java.util.HashMap;
import java.util.Map;

import ar.com.siim.dao.ReportesDAO;
import ar.com.siim.utils.Constantes;

public class ReportesCanonMineroFachada implements IReportesCanonMineroFachada {

	private ReportesDAO reportesDAO;
	
	public ReportesCanonMineroFachada(){}
	
	public ReportesCanonMineroFachada(ReportesDAO pReportesDAO){
		
		this.reportesDAO = pReportesDAO;
	}
	
	@Override
	public byte[] generarReporteCanonMineroEstadoDeudaPorProductorLocalizacionYPeriodo(
			String path, String periodo, Long idProductor, Long idLocalizacion) throws Exception {

		Map parameters = new HashMap();
		parameters.put("PATH_SUB_REPORTES", path);
		parameters.put("periodo", periodo);
		parameters.put("idProductor", idProductor);
		parameters.put("idLocalizacion", idLocalizacion);
		
		return reportesDAO.generarReporte(
				Constantes.REPORTE_CANON_MINERO_ESTADO_DEUDA_POR_PRODUCTOR_Y_PERIODO,
				parameters);
	}

}
