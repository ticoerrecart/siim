package ar.com.siim.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.struts.utils.Validator;
import ar.com.siim.dto.CanonMineroDTO;
import ar.com.siim.fachada.ICanonMineroFachada;
import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.ILocalidadFachada;
import ar.com.siim.fachada.IPeriodoFachada;
import ar.com.siim.fachada.ITipoProductoFachada;
import ar.com.siim.struts.actions.forms.CanonMineroForm;
import ar.com.siim.utils.Constantes;
import ar.com.siim.utils.MyLogger;

public class CanonMineroAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaCanonMinero(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarAltaCanonMinero";

		try {
			WebApplicationContext ctx = getWebApplicationContext();			
			
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
												.getBean("periodoFachada");
			
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
												.getBean("entidadFachada");

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
												.getBean("localidadFachada");			

			ICanonMineroFachada canonMineroFachada = (ICanonMineroFachada) ctx
												.getBean("canonMineroFachada");			
			
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("productores", entidadFachada.getProductoresDTO());
			request.setAttribute("localidades",localidadFachada.getLocalidadesDTO());			
			request.setAttribute("canonXPertenencia",canonMineroFachada.recuperarCanonMineroXPertenencia());
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward altaCanonMinero(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoAltaCanonMinero";

		try {
			WebApplicationContext ctx = getWebApplicationContext();			
			
			CanonMineroForm canonMineroForm = (CanonMineroForm)form;
			
			System.out.println("alta canon minero");
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	

	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionValorCanonMinero(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarModificacionValorCanonMinero";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			ICanonMineroFachada canonMineroFachada = (ICanonMineroFachada) ctx
														.getBean("canonMineroFachada");			
			
			int valor = canonMineroFachada.recuperarCanonMineroXPertenencia();
			CanonMineroDTO canon = new CanonMineroDTO();
			canon.setCanonXPertenencia(valor);
			
			request.setAttribute("canonMinero", canon);
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}		
	
	@SuppressWarnings("unchecked")
	public ActionForward modificacionValorCanonMinero(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionValorCanonMinero";

		try {
			WebApplicationContext ctx = getWebApplicationContext();			
			ICanonMineroFachada canonMineroFachada = (ICanonMineroFachada) ctx
													.getBean("canonMineroFachada");				
			
			CanonMineroForm canonMineroForm = (CanonMineroForm)form;
			CanonMineroDTO canonDTO = canonMineroForm.getCanonMinero();  
			
			canonMineroFachada.modificacionValorCanonMinero(canonDTO.getCanonXPertenencia());
			
			request.setAttribute("canonMinero", canonDTO);
			request.setAttribute("exito", Constantes.EXITO_MODIFICACION_VALOR_CANON_MINERO);
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
	
	public boolean validarAltaCanonMineroForm(StringBuffer error, ActionForm form) {

		try {
			CanonMineroForm canonMineroForm = (CanonMineroForm) form;
			CanonMineroDTO canon = canonMineroForm.getCanonMinero();

			boolean ok = true;
			boolean ok1 = true;
			boolean ok2 = true;
			boolean ok3 = true;
			boolean ok4 = true;
			boolean ok5 = true;

			ok = Validator.validarComboRequerido("-1", String.valueOf(canon.getProductor().getId()),
					"Productor", error);

			ok1 = Validator.validarLocalizacionRequerido(String.valueOf(canon.getZonaExtraccion().getId()), error);			
			
			ok2 = Validator.validarDoubleMayorQue(0, String.valueOf(canon.getMontoTotal()), "Monto Total", error);			
			
			ok3 = Validator.validarBoletasDeposito(canonMineroForm.getBoletasDeposito(), canon.getMontoTotal(), error);			
			
			ok4 = Validator.validarComboRequerido("-1", String.valueOf(canon.getLocalidad().getId()),
					"Localidad", error);			
			
			ok5 = Validator.requerido(canon.getFecha(), "Fecha", error);

			return ok && ok1 && ok2 && ok3 && ok4 && ok5;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}
	
	public boolean validarValorCanonMineroForm(StringBuffer error, ActionForm form) {

		try {	
			CanonMineroForm canonMineroForm = (CanonMineroForm) form;
			CanonMineroDTO canon = canonMineroForm.getCanonMinero();			
			
			return Validator.validarDoubleMayorQue(0,String.valueOf(canon.getCanonXPertenencia()),
									   "Valor Canon Minero Por Pertenencia", error);
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}		
}
