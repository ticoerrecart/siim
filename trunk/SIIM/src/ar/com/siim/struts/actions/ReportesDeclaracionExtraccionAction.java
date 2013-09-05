package ar.com.siim.struts.actions;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.IPeriodoFachada;
import ar.com.siim.fachada.IReportesDeclaracionExtraccionFachada;
import ar.com.siim.utils.MyLogger;

public class ReportesDeclaracionExtraccionAction extends ValidadorAction {

	public ActionForward cargarReporteEstadoDeudaDeclaracionExtraccionPorProductorLocalizacionYPeriodo(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarReporteEstadoDeudaDeclaracionExtraccionPorProductorLocalizacionYPeriodo";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			
			request.setAttribute("periodos",periodoFachada.getPeriodosDTO());			
			request.setAttribute("productores",entidadFachada.getProductoresDTO());
			request.setAttribute("titulo","Reporte Declaración de Extracción - Estado de Deuda");
			request.setAttribute("action","reportesDeclaracionExtraccion");
			request.setAttribute("metodo","generarReporteEstadoDeudaDeclaracionExtraccionPorProductorLocalizacionYPeriodo");
			request.setAttribute("permitirTodosLosProductores","N");
			request.setAttribute("permitirTodosLosPeriodos","S");
			request.setAttribute("permitirTodasLasLocalizaciones","S");
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}
	
	public ActionForward generarReporteEstadoDeudaDeclaracionExtraccionPorProductorLocalizacionYPeriodo(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesDeclaracionExtraccionFachada reportesDeclaracionExtraccionFachada = 
					(IReportesDeclaracionExtraccionFachada) ctx.getBean("reportesDeclaracionExtraccionFachada");
			
			String productor = request.getParameter("productor");
			String localizacion = request.getParameter("localizacion");
			String periodo = request.getParameter("periodo");

			byte[] bytes = reportesDeclaracionExtraccionFachada
					.generarReporteEstadoDeudaDeclaracionExtraccionPorProductorLocalizacionYPeriodo(
															path,periodo,Long.valueOf(productor),Long.valueOf(localizacion));

			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			// response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			return mapping.findForward("errorSinMenu");
		}

		return null;
	}	
	
	public ActionForward cargarReporteVolumenDeclarado(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarReporteEstadoDeudaDeclaracionExtraccionPorProductorLocalizacionYPeriodo";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			
			request.setAttribute("periodos",periodoFachada.getPeriodosDTO());			
			request.setAttribute("productores",entidadFachada.getProductoresDTO());
			request.setAttribute("titulo","Reporte Volumen de Declaración de Extracción");
			request.setAttribute("action","reportesDeclaracionExtraccion");
			request.setAttribute("metodo","generarReporteVolumenDeclarado");
			request.setAttribute("permitirTodosLosProductores","S");
			request.setAttribute("permitirTodosLosPeriodos","S");
			request.setAttribute("permitirTodasLasLocalizaciones","S");
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	public ActionForward generarReporteVolumenDeclarado(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesDeclaracionExtraccionFachada reportesDeclaracionExtraccionFachada = 
					(IReportesDeclaracionExtraccionFachada) ctx.getBean("reportesDeclaracionExtraccionFachada");
			
			String productor = request.getParameter("productor");
			String localizacion = request.getParameter("localizacion");
			String periodo = request.getParameter("periodo");

			byte[] bytes = reportesDeclaracionExtraccionFachada
					.generarReporteVolumenDeclarado(path,periodo,Long.valueOf(productor),Long.valueOf(localizacion));

			// Lo muestro en la salida del response
			response.setContentType("application/pdf");
			// response.setContentLength(baos.size());
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			return mapping.findForward("errorSinMenu");
		}

		return null;
	}	
}
