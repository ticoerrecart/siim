package ar.com.siim.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.dto.EntidadDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.fachada.IEntidadFachada;
import ar.com.siim.fachada.ILocalidadFachada;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.struts.actions.forms.EntidadForm;
import ar.com.siim.struts.utils.Validator;
import ar.com.siim.utils.Constantes;
import ar.com.siim.utils.MyLogger;

public class EntidadAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaEntidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "cargarAltaEntidad";
		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			//IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_ENTIDAD_MENU,usuario.getRol());

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
					.getBean("localidadFachada");
			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			request.setAttribute("tiposDeEntidad", entidadFachada.getTiposDeEntidad());
			request.setAttribute("localidades", localidadFachada.getLocalidadesDTO());
			request.setAttribute("titulo", Constantes.TITULO_ALTA_ENTIDAD);
			request.setAttribute("metodo", "altaEntidad");

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaEntidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoAltaEntidad";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			EntidadForm entidadForm = (EntidadForm) form;
			EntidadDTO entidadDTO = entidadForm.getEntidadDTO();

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			entidadFachada.altaEntidad(entidadDTO);

			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_ENTIDAD);
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	public boolean validarEntidadForm(StringBuffer error, ActionForm form) {
		
		try{
			EntidadForm entidadForm = (EntidadForm) form;
			boolean ok = entidadForm.validar(error);
			boolean existe = false;
			if (ok) {
				WebApplicationContext ctx = getWebApplicationContext();
				IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
				existe = entidadFachada.existeEntidad(entidadForm.getEntidadDTO().getNombre(),
						entidadForm.getEntidadDTO().getId());
				if (existe) {
					Validator.addErrorXML(error, Constantes.EXISTE_ENTIDAD);
				}
			}
			return ok && !existe;
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}			
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarEntidadesAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoRecuperarEntidades";
		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			//IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_ENTIDAD_MENU,usuario.getRol());

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");
			List<EntidadDTO> entidades = entidadFachada.getEntidadesDTO();
			request.setAttribute("entidades", entidades);

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarEntidadAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoCargarEntidadAModificar";
		try {
			UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute(
					Constantes.USER_LABEL_SESSION);
			WebApplicationContext ctx = getWebApplicationContext();

			//IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_ENTIDAD_MENU,usuario.getRol());

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			// recupero la entidad de la B.D.
			String id = request.getParameter("id");
			EntidadDTO entidad = entidadFachada.getEntidadDTO(Long.parseLong(id));
			request.setAttribute("entidad", entidad);

			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx
					.getBean("localidadFachada");
			request.setAttribute("localidades", localidadFachada.getLocalidadesDTO());
			request.setAttribute("metodo", "modificacionEntidad");

			if (entidad.getCuit() != null) {
				if (entidad.getCuit().length() == 11) {
					String prefijoCuit = entidad.getCuit().substring(0, 2).trim();
					String nroCuit = entidad.getCuit().substring(2, 10).trim();
					String sufijoCuit = entidad.getCuit().substring(10).trim();
					request.setAttribute("prefijoCuit", prefijoCuit);
					request.setAttribute("nroCuit", nroCuit);
					request.setAttribute("sufijoCuit", sufijoCuit);
				}
			}

		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
		}
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionEntidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String strForward = "exitoModificacionEntidad";
		try {
			WebApplicationContext ctx = getWebApplicationContext();
			EntidadForm entidadForm = (EntidadForm) form;
			EntidadDTO entidad = entidadForm.getEntidadDTO();

			IEntidadFachada entidadFachada = (IEntidadFachada) ctx.getBean("entidadFachada");

			entidadFachada.modificacionEntidad(entidad);

			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_ENTIDAD);
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		return mapping.findForward(strForward);
	}

}
