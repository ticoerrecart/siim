package ar.com.siim.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.fachada.ILocalidadFachada;
//import ar.com.siim.fachada.IRolFachada;
import ar.com.siim.negocio.Localidad;
import ar.com.siim.negocio.Usuario;
import ar.com.siim.negocio.exception.AccesoDenegadoException;
import ar.com.siim.struts.actions.forms.LocalidadForm;
import ar.com.siim.struts.utils.Validator;
import ar.com.siim.utils.Constantes;

public class LocalidadAction extends ValidadorAction {

	@SuppressWarnings("unchecked")
	public ActionForward cargarLocalidadesAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoRecuperarLocalidades";
		try{
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			//IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_LOCALIDAD_MENU,usuario.getRol());
			
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			List<LocalidadDTO> localidades = localidadFachada.getLocalidadesDTO();
			request.setAttribute("localidades", localidades);
			
		} catch (Exception e) {
			strForward = "error";
			request.setAttribute("error", e.getMessage());
		}
		
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward altaLocalidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoAltaLocalidad";
		try{	
			LocalidadForm localidadForm = (LocalidadForm) form;
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);	
			WebApplicationContext ctx = getWebApplicationContext();			
			
			//IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.ALTA_LOCALIDAD_MENU,usuario.getRol());	
			
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			localidadFachada.altaLocalidad(localidadForm.getLocalidadDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_LOCALIDAD);
		
		/*} catch (AccesoDenegadoException ade) {
			request.setAttribute("error", ade.getMessage());
			strForward = "error";*/			
		} catch (Exception e) {
			strForward = "bloqueError";
			request.setAttribute("error", e.getMessage());
		}			
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward cargarLocalidadAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoCargarLocalidadAModificar";
		try{
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			//IRolFachada rolFachada = (IRolFachada) ctx.getBean("rolFachada");
			//rolFachada.verificarMenu(Constantes.MODIFICACION_LOCALIDAD_MENU,usuario.getRol());		
		
			String id = request.getParameter("id");
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			request.setAttribute("localidad", localidadFachada.getLocalidadDTOPorId(Long.valueOf(id)));
			request.setAttribute("metodo", "modificacionLocalidad");
		
		} catch (Exception e) {
			strForward = "bloqueError";
			request.setAttribute("error", e.getMessage());
		}			
		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward modificacionLocalidad(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoModificacionLocalidad";
		try{		
			LocalidadForm localidadForm = (LocalidadForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			localidadFachada.modificacionLocalidad(localidadForm.getLocalidadDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_LOCALIDAD);
		
		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
		}			
		return mapping.findForward(strForward);
	}
	
	public boolean validarLocalidadForm(StringBuffer error, ActionForm form) {
		LocalidadForm localidadForm = (LocalidadForm) form;
		WebApplicationContext ctx = getWebApplicationContext();
		ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
		boolean existe = localidadFachada.existeLocalidad(localidadForm.getLocalidadDTO());
		if (existe) {
			Validator.addErrorXML(error, Constantes.EXISTE_LOCALIDAD);
		}
		return !existe && localidadForm.validar(error);
	}	
}
