package ar.com.siim.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.dto.PeriodoDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.fachada.IPeriodoFachada;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.struts.actions.forms.PeriodoForm;
import ar.com.siim.struts.utils.Validator;
import ar.com.siim.utils.Constantes;
import ar.com.siim.utils.MyLogger;

//import ar.com.siim.fachada.IRolFachada;

public class PeriodoAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarPeriodosAModificar(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarPeriodos";
		try {
			/*UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);*/
			WebApplicationContext ctx = getWebApplicationContext();

			// IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");

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

			// IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			// rolFachada.verificarMenu(Constantes.ALTA_LOCALIDAD_MENU,usuario.getRol());

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			periodoFachada.altaPeriodo(periodoForm.getPeriodoDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_PERIODO);

		} catch (NegocioException n) {
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
			/*UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);*/
			WebApplicationContext ctx = getWebApplicationContext();

			// IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
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

		} catch (NegocioException n) {
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
		try {
			PeriodoForm periodoForm = (PeriodoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");

			boolean existe = periodoFachada.existePeriodo(periodoForm
					.getPeriodoDTO());

			if (existe) {
				Validator.addErrorXML(error, Constantes.EXISTE_PERIODO);
			}

			boolean ok0 = Validator.requerido(periodoForm.getPeriodoDTO()
					.getPeriodo(), "Periodo", error);

			String primerTrim = "Fecha de Vencimiento del 1er Trimestre";
			String segundoTrim = "Fecha de Vencimiento del 2do Trimestre";
			String tercerTrim = "Fecha de Vencimiento del 3er Trimestre";
			String cuartoTrim = "Fecha de Vencimiento del 4to Trimestre";

			boolean ok1 = Validator.requerido(periodoForm.getPeriodoDTO()
					.getFechaVencimientoPrimerTrimestre(), primerTrim, error);
			boolean ok2 = Validator.requerido(periodoForm.getPeriodoDTO()
					.getFechaVencimientoSegundoTrimestre(), segundoTrim, error);
			boolean ok3 = Validator.requerido(periodoForm.getPeriodoDTO()
					.getFechaVencimientoTercerTrimestre(), tercerTrim, error);
			boolean ok4 = Validator.requerido(periodoForm.getPeriodoDTO()
					.getFechaVencimientoCuartoTrimestre(), cuartoTrim, error);

			boolean ok5 = true;
			boolean ok6 = true;
			boolean ok7 = true;
			boolean ok8 = true;

			if (ok0 && ok1 && ok2 && ok3 && ok4) {
				ok5 = Validator.validarFechaValida(periodoForm.getPeriodoDTO()
						.getFechaVencimientoPrimerTrimestre(), primerTrim,
						error);
				ok6 = Validator.validarFechaValida(periodoForm.getPeriodoDTO()
						.getFechaVencimientoPrimerTrimestre(), segundoTrim,
						error);
				ok7 = Validator.validarFechaValida(periodoForm.getPeriodoDTO()
						.getFechaVencimientoPrimerTrimestre(), tercerTrim,
						error);
				ok8 = Validator.validarFechaValida(periodoForm.getPeriodoDTO()
						.getFechaVencimientoPrimerTrimestre(), cuartoTrim,
						error);
				if (ok5 && ok6 && ok7 && ok8) {
					if (!periodoForm
							.getPeriodoDTO()
							.getPeriodo()
							.equals(periodoForm.getPeriodoDTO()
									.getFechaVencimientoPrimerTrimestre()
									.substring(6))) {
						Validator.addErrorXML(error,
								"No coincide el año de la " + primerTrim);
					}
					if (!periodoForm
							.getPeriodoDTO()
							.getPeriodo()
							.equals(periodoForm.getPeriodoDTO()
									.getFechaVencimientoSegundoTrimestre()
									.substring(6))) {
						Validator.addErrorXML(error,
								"No coincide el año de la " + segundoTrim);
					}
					if (!periodoForm
							.getPeriodoDTO()
							.getPeriodo()
							.equals(periodoForm.getPeriodoDTO()
									.getFechaVencimientoTercerTrimestre()
									.substring(6))) {
						Validator.addErrorXML(error,
								"No coincide el año de la " + tercerTrim);
					}
					if (!periodoForm
							.getPeriodoDTO()
							.getPeriodo()
							.equals(periodoForm.getPeriodoDTO()
									.getFechaVencimientoCuartoTrimestre()
									.substring(6))) {
						Validator.addErrorXML(error,
								"No coincide el año de la " + cuartoTrim);
					}
				}
			}

			return !existe && ok0 && ok1 && ok2 && ok3 && ok4 && ok5 && ok6
					&& ok7 && ok8;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}
}
