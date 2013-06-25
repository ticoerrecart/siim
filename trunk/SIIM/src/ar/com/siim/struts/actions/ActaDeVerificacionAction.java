package ar.com.siim.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.fachada.IActaDeVerificacionFachada;
import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.ILocalidadFachada;
import ar.com.siim.fachada.IPeriodoFachada;
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

			// ITipoProductoFachada tipoProductoFachada = (ITipoProductoFachada)
			// ctx.getBean("tipoProductoFachada");

			request.setAttribute("provincias", localidadFachada.getProvinciasDTO());
			request.setAttribute("localidades", localidadFachada.getLocalidadesDTO());
			request.setAttribute("periodos", periodoFachada.getPeriodosDTO());
			request.setAttribute("productores", entidadFachada.getProductoresDTO());

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
			ActaDeVerificacionForm actaForm = (ActaDeVerificacionForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			IActaDeVerificacionFachada actaDeVerificacionFachada = (IActaDeVerificacionFachada) ctx.getBean("actaDeVerificacionFachada");
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

			ok = Validator.validarLongMayorQue(0, Long.toString(actaForm.getActa().getNumero()), "Número de Acta", error);

			if (ok) {
				ok1 = !actaDeVerificacionFachada.existeActaDeVerificacion(actaForm.getActa().getNumero());

				if (!ok1) {
					Validator.addErrorXML(error, "El número de Acta de Verificación ya existe, especifique otro");
				}
			}

			ok2 = Validator.requerido(actaForm.getActa().getAgenteVerificacion(), "Agente Verificación", error);
			ok3 = Validator.requerido(actaForm.getActa().getTransporte().getDominio(), "Dominio del Transporte", error);
			ok4 = Validator.requerido(actaForm.getActa().getYacimiento().getId(), "Yacimiento", error);
			ok5 = Validator.validarEnteroMayorQue(0, String.valueOf(actaForm.getActa().getNumeroDeRemito()), "Numero de Remito", error);
			ok6 = Validator.requerido(actaForm.getActa().getDestino().getId(), "Localidad Destino", error);
			
			if (actaForm.getActa().getGranelVolumenM3Declarado() == 0 && actaForm.getActa().getBolsaVolumenD3()==0){
				Validator.addErrorXML(error, "Debe declarar el Volumen en Granel y/o en Bolsa");
				ok7 = false;
			}
			
			return ok && ok2 && ok3 && ok4 && ok5 && ok6 && ok7;

		} catch (Throwable t) {
			MyLogger.logError(t);
			// Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}
	}

}
