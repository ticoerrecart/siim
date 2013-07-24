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
import ar.com.siim.fachada.IReportesCanonMineroFachada;
import ar.com.siim.utils.MyLogger;

public class ReportesCanonMineroAction extends ValidadorAction {

	public ActionForward cargarReporteCanonMineroEstadoDeudaPorProductorYPeriodo(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarReporteCanonMineroEstadoDeudaPorProductorYPeriodo";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			
			request.setAttribute("periodos",periodoFachada.getPeriodosDTO());			
			request.setAttribute("productores",entidadFachada.getProductoresDTO());
			request.setAttribute("titulo","Reporte Canon Minero - Estado de Deuda");
			request.setAttribute("action","reportesCanonMinero");
			request.setAttribute("metodo","generarReporteCanonMineroEstadoDeudaPorProductorYPeriodo");
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
		
	public ActionForward generarReporteCanonMineroEstadoDeudaPorProductorYPeriodo(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		try {
			String path = request.getSession().getServletContext()
					.getRealPath("jasper");

			WebApplicationContext ctx = getWebApplicationContext();

			IReportesCanonMineroFachada reportesCanonMineroFachada = (IReportesCanonMineroFachada) ctx
					.getBean("reportesCanonMineroFachada");

			String periodo = request.getParameter("periodo");
			String productor = request.getParameter("productor");

			byte[] bytes = reportesCanonMineroFachada
					.generarReporteCanonMineroEstadoDeudaPorProductorYPeriodo(path,periodo,Long.valueOf(productor));

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
