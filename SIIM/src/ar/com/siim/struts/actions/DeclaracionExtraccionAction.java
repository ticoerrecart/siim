package ar.com.siim.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.fachada.DeclaracionDeExtraccionFachada;
import ar.com.siim.fachada.IDeclaracionDeExtraccionFachada;
import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.ILocalidadFachada;
import ar.com.siim.fachada.IPeriodoFachada;
import ar.com.siim.fachada.ITipoProductoFachada;
import ar.com.siim.negocio.DeclaracionDeExtraccion;
import ar.com.siim.struts.actions.forms.DeclaracionExtraccionForm;
import ar.com.siim.struts.utils.Validator;
import ar.com.siim.utils.MyLogger;

public class DeclaracionExtraccionAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaDeclaracionExtraccion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargaAltaDeclaracionExtraccion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
					.getBean("localidadFachada");

			ITipoProductoFachada tipoProductoFachada = (ITipoProductoFachada) ctx
					.getBean("tipoProductoFachada");

			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("productores",
					entidadFachada.getProductoresDTO());
			request.setAttribute("localidades",
					localidadFachada.getLocalidadesDTO());
			request.setAttribute("productoTurba",
					tipoProductoFachada.recuperarTipoProductoDTO(1L));

			String msjeExito = request.getParameter("msjeExito");
			if (msjeExito != null) {
				request.setAttribute("exitoGrabado",
						"Se ha dado de alta con éxito la Declaración de Extracción");
			}

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public boolean validarAltaDeclaracionExtraccionForm(StringBuffer error,
			ActionForm form) {

		try {
			boolean ok0 = true;
			boolean ok1 = true;
			boolean ok2 = true;
			boolean ok3 = true;
			boolean ok4 = true;
			boolean ok5 = true;
			boolean ok6 = true;
			boolean ok7 = true;

			DeclaracionExtraccionForm declaracionExtraccionForm = (DeclaracionExtraccionForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IDeclaracionDeExtraccionFachada declaracionDeExtraccionFachada = (IDeclaracionDeExtraccionFachada) ctx
					.getBean("declaracionDeExtraccionFachada");

			ok0 = Validator.validarLongMayorQue(0, declaracionExtraccionForm
					.getDeclaracion().getNumero().toString(), "Número", error);
			ok2 = Validator
					.requerido(declaracionExtraccionForm.getDeclaracion()
							.getFecha(), "Fecha de Declaración", error);
			ok3 = Validator.validarProductorRequerido(declaracionExtraccionForm
					.getDeclaracion().getProductor().getId().toString(), error);

			ok4 = Validator.validarLocalizacionRequerido(
					declaracionExtraccionForm.getDeclaracion()
							.getLocalizacion().getId() == null ? null
							: declaracionExtraccionForm.getDeclaracion()
									.getLocalizacion().getId().toString(),
					error);

			if (ok0 && ok2 && ok3 && ok4) {
				String existe = declaracionDeExtraccionFachada
						.existeDeclaracionExtraccion(declaracionExtraccionForm
								.getDeclaracion());

				if (StringUtils.hasText(existe)) {
					Validator.addErrorXML(error, existe);
					ok1 = false;
				}
			}

			if (ok0 & ok1 && ok2 && ok3 && ok4) {
				ok5 = Validator.validarTrimestres(
						declaracionExtraccionForm.getTrimestres(), error);
				ok6 = Validator.validarComboRequerido("-1",
						declaracionExtraccionForm.getDeclaracion()
								.getLocalidad().getId().toString(),
						"Localidad", error);

				if (ok5) {
					ok7 = Validator.validarBoletasDeposito(
							declaracionExtraccionForm.getBoletasDeposito(),
							declaracionExtraccionForm.getDeclaracion()
									.getImporteTotal(), error);
				}
			}

			return ok1 && ok2 && ok3 && ok4 && ok5 && ok6 && ok7;

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaDeclaracionExtraccion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoAltaDeclaracionExtraccion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();
			DeclaracionExtraccionForm declaracionDeExtraccionForm = (DeclaracionExtraccionForm) form;

			IDeclaracionDeExtraccionFachada declaracionFachada = (IDeclaracionDeExtraccionFachada) ctx
					.getBean("declaracionDeExtraccionFachada");
			declaracionFachada.altaDeclaracionExtraccion(
					declaracionDeExtraccionForm.getDeclaracion(),
					declaracionDeExtraccionForm.getTrimestres(),
					declaracionDeExtraccionForm.getBoletasDeposito());
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	// secuencia de llamados
	// 1-cargarProductoresParaModificacionDeDeclaracion
	// 2-recuperarDeclaracionesParaModificar
	// 3-cargarModificacionDeDeclaracion

	@SuppressWarnings("unchecked")
	public ActionForward cargarProductoresParaModificacionDeDeclaracion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarProductoresParaModificacionDeDeclaracion";

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
					"../../declaracionExtraccion.do?metodo=recuperarDeclaracionesParaModificar");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarDeclaracionesParaModificar(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarDeclaracionesParaModificar";
		WebApplicationContext ctx = getWebApplicationContext();

		IDeclaracionDeExtraccionFachada declaracionDeExtraccionFachada = (IDeclaracionDeExtraccionFachada) ctx
				.getBean("declaracionDeExtraccionFachada");
		String idLocalizacion = request.getParameter("idLocalizacion");
		String idPeriodo = request.getParameter("idPeriodo");
		String idEntidad = request.getParameter("idEntidad");

		DeclaracionDeExtraccion declaracionDeExtraccion = declaracionDeExtraccionFachada
				.getDeclaracionDeExtraccion(Long.parseLong(idEntidad),
						Long.parseLong(idLocalizacion), idPeriodo, true);

		request.setAttribute("declaracion", declaracionDeExtraccion);
		request.setAttribute("tituloLinkDetalle",
				"Modificar Declaración de Extracción");
		request.setAttribute("fwdDetalle",
				"/declaracionExtraccion.do?metodo=cargarModificacionDeclaracionExtraccion");

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionDeclaracionExtraccion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargaAltaDeclaracionExtraccion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
					.getBean("localidadFachada");

			ITipoProductoFachada tipoProductoFachada = (ITipoProductoFachada) ctx
					.getBean("tipoProductoFachada");

			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("productores",
					entidadFachada.getProductoresDTO());
			request.setAttribute("localidades",
					localidadFachada.getLocalidadesDTO());
			request.setAttribute("productoTurba",
					tipoProductoFachada.recuperarTipoProductoDTO(1L));

			DeclaracionDeExtraccionFachada declaracionFachada = (DeclaracionDeExtraccionFachada) ctx
					.getBean("declaracionDeExtraccionFachada");
			String id = request.getParameter("id");
			DeclaracionDeExtraccion declaracion = declaracionFachada
					.getDeclaracionDeExtraccionById(Long.valueOf(id));

			request.setAttribute("declaracionDeExtraccion", declaracion);
			/*
			 * String msjeExito = request.getParameter("msjeExito"); if
			 * (msjeExito != null) { request.setAttribute("exitoGrabado",
			 * "Se ha dado de alta con éxito la Declaración de Extracción"); }
			 */

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

}
