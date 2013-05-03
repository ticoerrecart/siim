package ar.com.siim.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.ILocalidadFachada;
import ar.com.siim.fachada.IPeriodoFachada;
import ar.com.siim.fachada.ITipoProductoFachada;
import ar.com.siim.utils.MyLogger;

public class DeclaracionExtraccionAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaGuiaForestalBasica(ActionMapping mapping,
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
			request.setAttribute("productores", entidadFachada.getProductoresDTO());
			request.setAttribute("localidades",localidadFachada.getLocalidadesDTO());			
			request.setAttribute("productoTurba", tipoProductoFachada.recuperarTipoProductoDTO(1L));
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}	
}
