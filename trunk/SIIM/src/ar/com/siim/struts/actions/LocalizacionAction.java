package ar.com.siim.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.struts.actions.forms.LocalizacionForm;
import ar.com.siim.struts.utils.Validator;
import ar.com.siim.utils.Constantes;
import ar.com.siim.dto.LocalizacionDTO;
import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.ILocalizacionFachada;
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

	public ActionForward altaLocalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoAltaLocalizacion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			LocalizacionForm localizacionForm = (LocalizacionForm)form;
			ILocalizacionFachada localizacionFachada = 
									(ILocalizacionFachada) ctx.getBean("localizacionFachada");
			
			localizacionFachada.altaLocalizacion(localizacionForm.getLocalizacionDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_LOCALIZACION);			
			
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);	
	}	
	
	public ActionForward cargarModificacionLocalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarAltaLocalizacion";

		try {
			String paramForward = request.getParameter("forward");
			WebApplicationContext ctx = getWebApplicationContext();
		
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidadProductores());			
			request.setAttribute("titulo",Constantes.TITULO_MODIFICACION_LOCALIZACION);		
			request.setAttribute("urlDetalle","../../localizacion.do?metodo=cargarLocalizacionesParaModificar");			
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);	
	}	

	public ActionForward cargarLocalizacionesParaModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarLocalizacionesParaModificar";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
		
			String idProductor = request.getParameter("idProductor");
			
			ILocalizacionFachada localizacionFachada = (ILocalizacionFachada) ctx.getBean("localizacionFachada");
			request.setAttribute("localizaciones", localizacionFachada.getLocalizacionesPorProductorDTO(Long.valueOf(idProductor)));			
						
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);	
	}	
	
	public ActionForward cargarLocalizacionParaModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarLocalizacionParaModificar";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
		
			String idLocalizacion = request.getParameter("idLocalizacion");
			
			ILocalizacionFachada localizacionFachada = (ILocalizacionFachada) ctx.getBean("localizacionFachada");
			
			LocalizacionDTO localizacionDTO = localizacionFachada.getLocalizacionDTOPorId(Long.valueOf(idLocalizacion));
			
			request.setAttribute("localizacion", localizacionDTO);
			request.setAttribute("idProductor", localizacionDTO.getProductor().getId());			
			request.setAttribute("metodo","modificacionLocalizacion");			
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);	
	}	
	
	public ActionForward modificacionLocalizacion(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String strForward = "exitoModificacionLocalizacion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			LocalizacionForm localizacionForm = (LocalizacionForm)form;
			ILocalizacionFachada localizacionFachada = 
									(ILocalizacionFachada) ctx.getBean("localizacionFachada");
			
			localizacionFachada.modificacionLocalizacion(localizacionForm.getLocalizacionDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_LOCALIZACION);			
			
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);	
	}	
	
	public boolean validarLocalizacionForm(StringBuffer error, ActionForm form) {

		try{
			
			LocalizacionForm localizacionForm = (LocalizacionForm)form;
			LocalizacionDTO localizacionDTO = localizacionForm.getLocalizacionDTO();
			
			boolean ok=true,ok1=true,ok2=true,ok3=true;
			
			ok = Validator.requerido(localizacionDTO.getRazonSocial(), "Razón Social", error); 
			ok1 = Validator.requerido(localizacionDTO.getExpediente(), "Expediente", error);
			ok2 = Validator.requerido(localizacionDTO.getResolucion(), "Resolución", error);
			ok3 = Validator.validarDoubleMayorQue(0,
					Double.toString(localizacionDTO.getSuperficie()),
					"Superficie", error);			
			
			return ok && ok1 && ok2 && ok3; 
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}
}
