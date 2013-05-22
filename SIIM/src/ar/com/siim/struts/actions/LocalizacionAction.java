package ar.com.siim.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.utils.Constantes;
import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.utils.MyLogger;

public class LocalizacionAction extends ValidadorAction {

	public ActionForward cargarAltaLocalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarAltaLocalizacion";

		try {
			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
		
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());			
			request.setAttribute("titulo",Constantes.TITULO_ALTA_LOCALIZACION);			
			request.setAttribute("urlDetalle","../bloqueAltaModificacionLocalizacion.jsp?metodo=altaLocalizacion");			
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);	
	}	
}
