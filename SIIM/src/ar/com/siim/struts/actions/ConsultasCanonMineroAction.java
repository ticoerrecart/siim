package ar.com.siim.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.fachada.ICanonMineroFachada;
import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.IPeriodoFachada;
import ar.com.siim.negocio.CanonMinero;
import ar.com.siim.utils.MyLogger;

public class ConsultasCanonMineroAction extends ValidadorAction {

	// secuencia de llamados
	// 1-cargarProductoresLocalizacionPeriodo
	// 2-recuperarCanonMinero
	// 3-cargarConsultaCanonMinero

	@SuppressWarnings("unchecked")
	public ActionForward cargarProductoresLocalizacionPeriodo(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String strForward = "exitoCargarProductoresLocalizacionPeriodo";
		try {
			String idProductor = request.getParameter("idProductor");
			String idLocalizacion = request.getParameter("idLocalizacion");
			String idPeriodo = request.getParameter("idPeriodo");
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("idLocalizacion", idLocalizacion);
			request.setAttribute("idPeriodo", idPeriodo);

			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("productores",
					entidadFachada.getProductoresDTO());
			// request.setAttribute("paramForward",);
			request.setAttribute("urlDetalle",
					"../../consultasCanonMinero.do?metodo=recuperarCanonMinero");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarCanonMinero(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarCanonMinero";
		WebApplicationContext ctx = getWebApplicationContext();
		ICanonMineroFachada canonMineroFachada = (ICanonMineroFachada) ctx
				.getBean("canonMineroFachada");
		String idLocalizacion = request.getParameter("idLocalizacion");
		String idPeriodo = request.getParameter("idPeriodo");

		CanonMinero canonMinero = canonMineroFachada.getCanonMinero(
				Long.parseLong(idLocalizacion), idPeriodo);

		request.setAttribute("canonMinero", canonMinero);
		request.setAttribute("tituloLinkDetalle", "Ver Canon Minero");
		request.setAttribute("fwdDetalle",
				"/consultasCanonMinero.do?metodo=cargarConsultaCanonMinero");
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarConsultaCanonMinero(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarConsultaCanonMinero";
		try {
			String idCanonMinero = request.getParameter("id");
			WebApplicationContext ctx = getWebApplicationContext();

			ICanonMineroFachada canonMineroFachada = (ICanonMineroFachada) ctx
					.getBean("canonMineroFachada");

			CanonMinero canonMinero = canonMineroFachada.getCanonMinero(Long
					.parseLong(idCanonMinero));

			request.setAttribute("canonMinero", canonMinero);
			request.setAttribute("titulo", "Consulta de Canon Minero");
			request.setAttribute("volver",
					"/consultasCanonMinero.do?metodo=cargarProductoresLocalizacionPeriodo");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}
}
