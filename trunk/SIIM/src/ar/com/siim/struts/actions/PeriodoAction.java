package ar.com.siim.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.utils.MyLogger;
import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.fachada.IPeriodoFachada;
//import ar.com.siim.fachada.IRolFachada;
import ar.com.siim.struts.actions.forms.PeriodoForm;
import ar.com.siim.struts.utils.Validator;
import ar.com.siim.utils.Constantes;

public class PeriodoAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarPeriodosAModificar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarPeriodos";
		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			//IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			List<PeriodoDTO> periodos = periodoFachada.getPeriodosDTO();
			request.setAttribute("periodos", periodos);

		} catch (Throwable e) {
			MyLogger.logError(e);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaPeriodo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoAltaPeriodo";
		try {
			PeriodoForm periodoForm = (PeriodoForm) form;
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			//IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.ALTA_LOCALIDAD_MENU,usuario.getRol());

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			periodoFachada.altaPeriodo(periodoForm.getPeriodoDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_PERIODO);

		} catch (NegocioException n){
			MyLogger.log(n.getMessage());
			request.setAttribute("error", n.getMessage());
			
		} catch (Throwable e) {
			MyLogger.logError(e);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarPeriodoAModificar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarPeriodoAModificar";
		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			//IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.MODIFICACION_LOCALIDAD_MENU,usuario.getRol());

			String id = request.getParameter("id");
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			request.setAttribute("periodo",
					periodoFachada.getPeriodoDTOPorId(Long.valueOf(id)));
			request.setAttribute("metodo", "modificacionPeriodo");

		} catch (Throwable e) {
			MyLogger.logError(e);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionPeriodo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionPeriodo";
		try {
			PeriodoForm periodoForm = (PeriodoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			periodoFachada.modificacionPeriodo(periodoForm.getPeriodoDTO());
			request.setAttribute("exitoGrabado",
					Constantes.EXITO_MODIFICACION_PERIODO);

		} catch (NegocioException n){
			MyLogger.log(n.getMessage());
			request.setAttribute("error", n.getMessage());
			strForward = "bloqueError";
		} catch (Throwable e) {
			MyLogger.logError(e);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}
		return mapping.findForward(strForward);
	}

	public boolean validarPeriodoForm(StringBuffer error, ActionForm form) {
		try{
			PeriodoForm periodoForm = (PeriodoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");
			
			boolean existe = periodoFachada.existePeriodo(periodoForm.getPeriodoDTO());
			
			if (existe) {
				Validator.addErrorXML(error, Constantes.EXISTE_PERIODO);
			}
			
			boolean ok = Validator.requerido(periodoForm.getPeriodoDTO().getPeriodo(),
					 						"Periodo", error);			
			
			return !existe && ok;
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}			
	}
}
