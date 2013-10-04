package ar.com.siim.struts.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.dto.OperacionDeclaracionExtraccionDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.enums.TipoOperacion;
import ar.com.siim.fachada.DeclaracionDeExtraccionFachada;
import ar.com.siim.fachada.IDeclaracionDeExtraccionFachada;
import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.ILocalidadFachada;
import ar.com.siim.fachada.IPeriodoFachada;
import ar.com.siim.fachada.ITipoProductoFachada;
import ar.com.siim.negocio.DeclaracionDeExtraccion;
import ar.com.siim.negocio.TrimestreDeclaracionDeExtraccion;
import ar.com.siim.struts.actions.forms.DeclaracionExtraccionForm;
import ar.com.siim.struts.utils.Validator;
import ar.com.siim.utils.Constantes;
import ar.com.siim.utils.Fecha;
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
			request.setAttribute("meses", getMapMeses());
			request.setAttribute("metodo", "altaDeclaracionExtraccion");
			
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
			UsuarioDTO usuario = (UsuarioDTO) request.getSession()
					.getAttribute(Constantes.USER_LABEL_SESSION);

			// valido nuevamente por seguridad.  
			if (!validarAltaDeclaracionExtraccionForm(new StringBuffer(), declaracionDeExtraccionForm)) {
				throw new Exception("Error de Seguridad");
			}			
			
			OperacionDeclaracionExtraccionDTO operacionDTO = new OperacionDeclaracionExtraccionDTO();
			operacionDTO.setUsuario(usuario);
			operacionDTO.setFecha(Fecha.getFechaHoyDDMMAAAAhhmmssSlash());
			operacionDTO.setTipoOperacion(TipoOperacion.ALTA.getDescripcion());
			declaracionDeExtraccionForm.getDeclaracion().addOperacion(
					operacionDTO);

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
			String consulta = request.getParameter("consulta");
			request.setAttribute("idProductor", idProductor);
			request.setAttribute("idLocalizacion", idLocalizacion);
			request.setAttribute("idPeriodo", idPeriodo);
			request.setAttribute("consulta", consulta);

			WebApplicationContext ctx = getWebApplicationContext();
			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
					.getBean("periodoFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx
					.getBean("entidadFachada");

			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("productores",
					entidadFachada.getProductoresDTO());

			request.setAttribute("urlDetalle",
					"../../declaracionExtraccion.do?metodo=recuperarDeclaracionesParaModificar");

			String exitoGrabado = (String) request.getAttribute("exitoGrabado");
			if (exitoGrabado != null) {
				request.setAttribute("exitoGrabado", exitoGrabado);
			}

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
		String consulta = request.getParameter("consulta");

		DeclaracionDeExtraccion declaracionDeExtraccion = declaracionDeExtraccionFachada
				.getDeclaracionDeExtraccion(Long.parseLong(idEntidad),
						Long.parseLong(idLocalizacion), idPeriodo, true);

		request.setAttribute("declaracion", declaracionDeExtraccion);
		if (StringUtils.hasText(consulta)) {
			request.setAttribute("tituloLinkDetalle",
					"Consulta Declaración de Extracción");
			request.setAttribute("fwdDetalle",
					"/declaracionExtraccion.do?metodo=cargarConsultaDeclaracionExtraccion");
		} else {
			request.setAttribute("tituloLinkDetalle",
					"Modificar Declaración de Extracción");
			request.setAttribute("fwdDetalle",
					"/declaracionExtraccion.do?metodo=cargarModificacionDeclaracionExtraccion");
		}

		return mapping.findForward(strForward);
	}

	private Map<Integer, List<String>> getMapMeses() {
		Map<Integer, List<String>> mapMeses = new HashMap<Integer, List<String>>();
		for (int i = 1; i < 5; i++) {
			List<String> meses = new ArrayList<String>();
			if (i == 1) {
				meses.add("Enero");
				meses.add("Febrero");
				meses.add("Marzo");
			}
			if (i == 2) {
				meses.add("Abril");
				meses.add("Mayo");
				meses.add("Junio");
			}
			if (i == 3) {
				meses.add("Julio");
				meses.add("Agosto");
				meses.add("Septiembre");
			}
			if (i == 4) {
				meses.add("Octubre");
				meses.add("Noviembre");
				meses.add("Diciembre");
			}

			mapMeses.put(i, meses);
		}

		return mapMeses;

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

			Map<Integer, TrimestreDeclaracionDeExtraccion> mapTrimestres = new HashMap<Integer, TrimestreDeclaracionDeExtraccion>();
			for (TrimestreDeclaracionDeExtraccion trimestre : declaracion
					.getTrimestres()) {
				mapTrimestres.put(trimestre.getNroTrimestre(), trimestre);
			}

			request.setAttribute("trimestres", mapTrimestres);
			request.setAttribute("meses", getMapMeses());

			request.setAttribute("boletas", declaracion.getBoletas());

			request.setAttribute("modificacion", "S");
			request.setAttribute("metodo", "modificacionDeclaracionExtraccion");

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

	// secuencia de llamados
	// 1-cargarProductoresParaPagoBoletas
	// 2-recuperarDeclaracionesParaPagoBoletas
	// 3-cargarDeclaracionParaPagoBoletas

	@SuppressWarnings("unchecked")
	public ActionForward cargarProductoresParaPagoBoletas(
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
			request.setAttribute("urlDetalle",
					"../../declaracionExtraccion.do?metodo=recuperarDeclaracionesParaPagoBoletas");
			request.setAttribute("titulo", "Pago Boletas de Deposito");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward recuperarDeclaracionesParaPagoBoletas(
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
		request.setAttribute("tituloLinkDetalle", "Pagar Boletas");
		request.setAttribute("fwdDetalle",
				"/declaracionExtraccion.do?metodo=cargarDeclaracionParaPagoBoletas");

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarDeclaracionParaPagoBoletas(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarDeclaracionParaPagoBoletas";

		try {
			WebApplicationContext ctx = getWebApplicationContext();

			/*
			 * IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx
			 * .getBean("periodoFachada");
			 * 
			 * IEntidadFachada entidadFachada = (IEntidadFachada) ctx
			 * .getBean("entidadFachada");
			 * 
			 * ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
			 * .getBean("localidadFachada");
			 * 
			 * request.setAttribute("periodos",
			 * periodoFachada.getPeriodosDTO());
			 * request.setAttribute("productores",
			 * entidadFachada.getProductoresDTO());
			 * request.setAttribute("localidades",
			 * localidadFachada.getLocalidadesDTO());
			 */

			ITipoProductoFachada tipoProductoFachada = (ITipoProductoFachada) ctx
					.getBean("tipoProductoFachada");

			request.setAttribute("productoTurba",
					tipoProductoFachada.recuperarTipoProductoDTO(1L));

			DeclaracionDeExtraccionFachada declaracionFachada = (DeclaracionDeExtraccionFachada) ctx
					.getBean("declaracionDeExtraccionFachada");
			String id = request.getParameter("id");
			DeclaracionDeExtraccion declaracion = declaracionFachada
					.getDeclaracionDeExtraccionById(Long.valueOf(id));
			request.setAttribute("declaracionDeExtraccion", declaracion);

			Map<Integer, TrimestreDeclaracionDeExtraccion> mapTrimestres = new HashMap<Integer, TrimestreDeclaracionDeExtraccion>();
			for (TrimestreDeclaracionDeExtraccion trimestre : declaracion
					.getTrimestres()) {
				mapTrimestres.put(trimestre.getNroTrimestre(), trimestre);
			}

			request.setAttribute("trimestres", mapTrimestres);			
			request.setAttribute("meses", getMapMeses());			
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionDeclaracionExtraccion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String strForward = "exitoModificacionDeclaracionExtraccion";
		try{			
			WebApplicationContext ctx = getWebApplicationContext();
			DeclaracionDeExtraccionFachada declaracionFachada = (DeclaracionDeExtraccionFachada) ctx
					.getBean("declaracionDeExtraccionFachada");
			DeclaracionExtraccionForm declaracionExtraccionForm = (DeclaracionExtraccionForm) form;
	
			// valido nuevamente por seguridad.  
			if (!validarAltaDeclaracionExtraccionForm(new StringBuffer(), declaracionExtraccionForm)) {
				throw new Exception("Error de Seguridad");
			}			
			
			declaracionFachada.modificacionDeclaracionDeExtraccion(
					declaracionExtraccionForm.getDeclaracion(),
					declaracionExtraccionForm.getBoletasDeposito(),
					declaracionExtraccionForm.getTrimestres());
	
			request.setAttribute("exitoGrabado",
					"La Declaración de Extracción se ha modificado con éxito");
		
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
			
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarConsultaDeclaracionExtraccion(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoConsultaDeclaracion";

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

			request.setAttribute("declaracion", declaracion);
			
			Map<Integer, TrimestreDeclaracionDeExtraccion> mapTrimestres = new HashMap<Integer, TrimestreDeclaracionDeExtraccion>();
			for (TrimestreDeclaracionDeExtraccion trimestre : declaracion
					.getTrimestres()) {
				mapTrimestres.put(trimestre.getNroTrimestre(), trimestre);
			}

			request.setAttribute("trimestres", mapTrimestres);			
			request.setAttribute("meses", getMapMeses());
			
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
