package ar.com.siim.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.enums.TipoOperacion;
import ar.com.siim.utils.Fecha;
import ar.com.siim.dto.OperacionActaVerificacionDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.utils.Constantes;
import ar.com.siim.fachada.IActaDeVerificacionFachada;
import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.ILocalidadFachada;
import ar.com.siim.fachada.IPeriodoFachada;
import ar.com.siim.fachada.IUsuarioFachada;
import ar.com.siim.negocio.ActaDeVerificacion;
import ar.com.siim.struts.actions.forms.ActaDeVerificacionForm;
import ar.com.siim.struts.utils.Validator;
import ar.com.siim.utils.MyLogger;

public class ActaDeVerificacionAction extends ValidadorAction {

	public ActionForward cargarActaDeVerificacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoCargaActaDeVerificacion";

		try {
			WebApplicationContext ctx = getWebApplicationContext();

			IPeriodoFachada periodoFachada = (IPeriodoFachada) ctx.getBean("periodoFachada");

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			
			IUsuarioFachada usuarioFachada = (IUsuarioFachada) ctx.getBean("usuarioFachada");

			// ITipoProductoFachada tipoProductoFachada = (ITipoProductoFachada)
			// ctx.getBean("tipoProductoFachada");

			request.setAttribute("provincias", localidadFachada.getProvinciasDTO());
			request.setAttribute("localidades", localidadFachada.getLocalidadesDTO());
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("productores", entidadFachada.getProductoresDTO());
			request.setAttribute("usuarios", usuarioFachada.getUsuariosDTO());
			
			
			// request.setAttribute("productoTurba",
			// tipoProductoFachada.recuperarTipoProductoDTO(1L));

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public ActionForward altaActaDeVerificacion(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String strForward = "exitoAltaDeVerificacion";

		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			ActaDeVerificacionForm actaForm = (ActaDeVerificacionForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IActaDeVerificacionFachada actaDeVerificacionFachada = (IActaDeVerificacionFachada) ctx.getBean("actaDeVerificacionFachada");
			
			// valido nuevamente por seguridad.  
			if (!validarActaDeVerificacionForm(new StringBuffer(), actaForm)) {
				throw new Exception("Error de Seguridad");
			}			
			
			OperacionActaVerificacionDTO operacionDTO = new OperacionActaVerificacionDTO();
			operacionDTO.setUsuario(usuario);
			operacionDTO.setFecha(Fecha.getFechaHoyDDMMAAAAhhmmssSlash());
			operacionDTO.setTipoOperacion(TipoOperacion.ALTA.getDescripcion());
			actaForm.getActa().addOperacion(operacionDTO);			
			
			actaDeVerificacionFachada.altaActaDeVerificacion(actaForm.getActa());
			request.setAttribute("exitoGrabado", "Acta de Verificación grabada con Exito");
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public boolean validarActaDeVerificacionForm(StringBuffer error, ActionForm form) {

		try {
			ActaDeVerificacionForm actaForm = (ActaDeVerificacionForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IActaDeVerificacionFachada actaDeVerificacionFachada = (IActaDeVerificacionFachada) ctx.getBean("actaDeVerificacionFachada");
			boolean ok = true;
			boolean ok1 = true;
			boolean ok2 = true;
			boolean ok3 = true;
			boolean ok4 = true;
			boolean ok5 = true;
			boolean ok6 = true;
			boolean ok7 = true;
			boolean ok8 = true;
			boolean ok9 = true;
			boolean ok10 = true;
			
			ok = Validator.validarLongMayorQue(0, Long.toString(actaForm.getActa().getNumero()), "Número de Acta", error);

			if (ok) {
				ok1 = !actaDeVerificacionFachada.existeActaDeVerificacion(actaForm.getActa().getNumero());

				if (!ok1) {
					Validator.addErrorXML(error, "El número de Acta de Verificación ya existe, especifique otro");
				}
			}

			ok8 = Validator.validarComboRequeridoSinNull("-1",String.valueOf(actaForm.getActa().getProductor().getId()), "Productor", error);
			ok9 = Validator.requerido(actaForm.getActa().getFechaVerificacion(), "Fecha Verificación", error);
			ok2 = Validator.validarComboRequeridoSinNull("-1",actaForm.getActa().getAgenteVerificacion(), "Agente Verificación", error);
			ok4 = Validator.validarComboRequeridoSinNull("-1",String.valueOf(actaForm.getActa().getYacimiento().getId()),"Razón Social",error);
			ok3 = Validator.requerido(actaForm.getActa().getTransporte().getDominio(), "Dominio del Transporte", error);			
			ok5 = Validator.validarEnteroMayorQue(0, String.valueOf(actaForm.getActa().getNumeroDeRemito()), "Numero de Remito", error);
			ok6 = Validator.validarComboRequeridoSinNull("-1",String.valueOf(actaForm.getActa().getDestino().getId()), "Localidad Destino", error);
			
			if (actaForm.getActa().getGranelVolumenM3Declarado() == 0 && actaForm.getActa().getBolsaVolumenD3()==0){
				Validator.addErrorXML(error, "Debe declarar el Volumen en Granel y/o en Bolsa");
				ok7 = false;
			}
			
			ok10 = Validator.validarComboRequerido("-1",String.valueOf(actaForm.getActa().getOficinaMinera().getId()),
					"Oficina Minera", error);			
			
			return ok && ok2 && ok3 && ok4 && ok5 && ok6 && ok7 && ok8 && ok9 && ok10;

		} catch (Throwable t) {
			MyLogger.logError(t);
			// Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}

	
	//CONSULTAS
	
	// secuencia de llamados
	// 1-cargarProductoresLocalizacionPeriodo
	// 2-recuperarActaDeVerificacion
	// 3-cargarActaDeVerificacion

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
					"../../consultaActas.do?metodo=recuperarActaDeVerificacion");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}

	public ActionForward recuperarActaDeVerificacion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarActas";
		WebApplicationContext ctx = getWebApplicationContext();
		IActaDeVerificacionFachada actaDeVerificacionFachada = (IActaDeVerificacionFachada) ctx
				.getBean("actaDeVerificacionFachada");
		String idLocalizacion = request.getParameter("idLocalizacion");
		String periodo = request.getParameter("idPeriodo");

		List<ActaDeVerificacion> actas = actaDeVerificacionFachada.getActas(Long.parseLong(idLocalizacion), periodo);

		request.setAttribute("actas", actas);
		request.setAttribute("tituloLinkDetalle", "Ver Acta de Verificación");
		request.setAttribute("fwdDetalle",
				"/consultaActas.do?metodo=cargarConsultaActa");
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarConsultaActa(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarConsultaActas";
		try {
			String idActa = request.getParameter("id");
			WebApplicationContext ctx = getWebApplicationContext();
			
			IActaDeVerificacionFachada actaDeVerificacionFachada = (IActaDeVerificacionFachada) ctx
					.getBean("actaDeVerificacionFachada");

			ActaDeVerificacion acta = actaDeVerificacionFachada.getActa(Long.parseLong(idActa));

			request.setAttribute("acta", acta);
			request.setAttribute("titulo", "Consulta de Actas de Verificación");
			request.setAttribute("volver",
					"/consultaActas.do?metodo=cargarProductoresLocalizacionPeriodo");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}

		return mapping.findForward(strForward);
	}
	
}
