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
import ar.com.siim.fachada.IReportesFachada;
import ar.com.siim.utils.MyLogger;

public class ReportesAction extends ValidadorAction {

	public ActionForward cargarReporteVolDeclaradoFiscalizado(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarReporteVolDeclaradoFiscalizado";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			
			request.setAttribute("periodos",periodoFachada.getPeriodosDTO());			
			request.setAttribute("productores",entidadFachada.getProductoresDTO());
			request.setAttribute("titulo","Reporte Volumen Declarado - Fiscalizado");
			request.setAttribute("action","reportes");
			request.setAttribute("metodo","generarReporteVolDeclaradoFiscalizado");
			request.setAttribute("permitirTodosLosPeriodos","N");
			request.setAttribute("permitirTodasLasLocalizaciones","N");
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	public ActionForward generarReporteVolDeclaradoFiscalizado(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");
			
			String productor = request.getParameter("productor");
			String localizacion = request.getParameter("localizacion");
			String periodo = request.getParameter("periodo");
			
			byte[] bytes = reportesFachada.generarReporteVolDeclaradoFiscalizado(path,periodo,
											Long.valueOf(productor),Long.valueOf(localizacion));

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
	
	public ActionForward cargarReporteEstadoDeDeudaGeneralPorProductor(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarReporteEstadoDeDeudaGeneralPorProductor";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			
			request.setAttribute("periodos",periodoFachada.getPeriodosDTO());			
			request.setAttribute("productores",entidadFachada.getProductoresDTO());
			request.setAttribute("titulo","Reporte Estado de Deuda por Productor y Periodo");
			request.setAttribute("action","reportes");
			request.setAttribute("metodo","generarReporteEstadoDeDeudaGeneralPorProductor");
			request.setAttribute("permitirTodosLosProductores","S");
			request.setAttribute("permitirTodosLosPeriodos","S");
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	public ActionForward generarReporteEstadoDeDeudaGeneralPorProductor(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");
			
			String productor = request.getParameter("productor");
			String periodo = request.getParameter("periodo");
			
			byte[] bytes = reportesFachada.generarReporteEstadoDeDeudaGeneralPorProductor(path,periodo,
											Long.valueOf(productor));

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
	
	public ActionForward generarReporteDeclaracionExtraccion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			String idDeclaracion = request.getParameter("idDeclaracion");			
			
			byte[] bytes = reportesFachada.generarReporteDeclaracionExtraccion(path,Long.valueOf(idDeclaracion));

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

	public ActionForward generarReporteCanonMinero(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			String idCanon = request.getParameter("idCanon");			
			
			byte[] bytes = reportesFachada.generarReporteCanonMinero(path,Long.valueOf(idCanon));

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
	
	public ActionForward generarReporteActaVerificacion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = (IReportesFachada) ctx
					.getBean("reportesFachada");

			String idActa = request.getParameter("idActa");			
			
			byte[] bytes = reportesFachada.generarReporteActaVerificacion(path,Long.valueOf(idActa));

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
	
	public ActionForward cargarReporteVolumenFiscalizado(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarReportePorProductorLocalizacionYPeriodo";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			
			request.setAttribute("periodos",periodoFachada.getPeriodosDTO());			
			request.setAttribute("productores",entidadFachada.getProductoresDTO());
			request.setAttribute("titulo","Reporte Volumen Fiscalizado");
			request.setAttribute("action","reportes");
			request.setAttribute("metodo","generarReporteVolumenFiscalizado");
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
	
	public ActionForward generarReporteVolumenFiscalizado(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesFachada reportesFachada = 
					(IReportesFachada) ctx.getBean("reportesFachada");
			
			String productor = request.getParameter("productor");
			String localizacion = request.getParameter("localizacion");
			String periodo = request.getParameter("periodo");

			byte[] bytes = reportesFachada
					.generarReporteVolumenFiscalizado(path,periodo,Long.valueOf(productor),Long.valueOf(localizacion));

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
