package ar.com.siim.struts.actions;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;

import ar.com.siim.struts.actions.forms.LocalidadDestinoForm;
import ar.com.siim.dto.ProvinciaDestinoDTO;
import ar.com.siim.struts.actions.forms.ProvinciaDestinoForm;
import ar.com.siim.negocio.exception.NegocioException;
import ar.com.siim.utils.MyLogger;
import ar.com.siim.dto.LocalidadDTO;
import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.fachada.ILocalidadFachada;
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
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
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
		
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());			
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
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
		
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "bloqueError";
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
		
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());			
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward altaProvinciaDestino(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoAltaProvinciaDestino";
		try{	
			ProvinciaDestinoForm provinciaForm = (ProvinciaDestinoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();			
						
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			localidadFachada.altaProvincia(provinciaForm.getProvinciaDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_PROVINCIA);
		
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());	
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarProvinciasDestinoAModificar(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception 
	{	
		String strForward = "exitoRecuperarProvinciasDestino";
		try{
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();			
			
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			List<ProvinciaDestinoDTO> provincias = localidadFachada.getProvinciasDTO();
			request.setAttribute("provincias", provincias);
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}
		
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarProvinciaDestinoAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoCargarProvinciaDestinoAModificar";
		try{
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();					
		
			String id = request.getParameter("id");
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			request.setAttribute("provincia", localidadFachada.getProvinciaDestinoDTOPorId(Long.valueOf(id)));
			request.setAttribute("metodo", "modificacionProvinciaDestino");
		
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward modificacionProvinciaDestino(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoModificacionProvinciaDestino";
		try{		
			ProvinciaDestinoForm provinciaForm = (ProvinciaDestinoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			
			localidadFachada.modificacionProvinciaDestino(provinciaForm.getProvinciaDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_PROVINCIA);
		
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());	
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarAltaLocalidadDestino(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoCargarAltaLocalidadDestino";
		try{
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();					
		
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			request.setAttribute("provincias", localidadFachada.getProvinciasDTO());
		
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward altaLocalidadDestino(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoAltaLocalidadDestino";
		try{	
			LocalidadDestinoForm localidadForm = (LocalidadDestinoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();			
						
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			localidadFachada.altaLocalidadDestino(localidadForm.getLocalidadDestinoDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_ALTA_LOCALIDAD);
		
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());	
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}		
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarModificacionLocalidadDestino(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoCargarModificacionLocalidadDestino";
		try{
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();					
		
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			request.setAttribute("provincias", localidadFachada.getProvinciasDTO());
		
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward cargarLocalidadDestinoAModificar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoCargarLocalidadDestinoAModificar";
		try{
			String idLocalidad = request.getParameter("idLocalidad");
			
			UsuarioDTO usuario = (UsuarioDTO)request.getSession().getAttribute(Constantes.USER_LABEL_SESSION);			
			WebApplicationContext ctx = getWebApplicationContext();				
		
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			request.setAttribute("localidadDTO", localidadFachada.getLocalidadDestinoDTOPorId(new Long(idLocalidad)));
			request.setAttribute("provincias", localidadFachada.getProvinciasDTO());		
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}	
	
	@SuppressWarnings("unchecked")
	public ActionForward modificacionLocalidadDestino(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String strForward = "exitoModificacionLocalidadDestino";
		try{		
			LocalidadDestinoForm localidadForm = (LocalidadDestinoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			
			localidadFachada.modificacionLocalidadDestino(localidadForm.getLocalidadDestinoDTO());
			request.setAttribute("exitoGrabado", Constantes.EXITO_MODIFICACION_LOCALIDAD);
		
		} catch (NegocioException ne) {
			request.setAttribute("error", ne.getMessage());	
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			request.setAttribute("error", "Error Inesperado");
			strForward = "error";
		}			
		return mapping.findForward(strForward);
	}	
	
	public boolean validarLocalidadForm(StringBuffer error, ActionForm form) {
		try{
			LocalidadForm localidadForm = (LocalidadForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			boolean existe = localidadFachada.existeLocalidad(localidadForm.getLocalidadDTO());
			if (existe) {
				Validator.addErrorXML(error, Constantes.EXISTE_LOCALIDAD);
			}
			return !existe && localidadForm.validar(error);
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}			
	}
	
	public boolean validarProvinciaForm(StringBuffer error, ActionForm form) {
		
		try{
			ProvinciaDestinoForm provinciaForm = (ProvinciaDestinoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			
			boolean existe = localidadFachada.existeProvincia(provinciaForm.getProvinciaDTO());
			if (existe) {
				Validator.addErrorXML(error, Constantes.EXISTE_PROVINCIA);
			}
			return !existe && provinciaForm.validar(error);
			
		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}			
	}
	
	public boolean validarLocalidadDestinoForm(StringBuffer error, ActionForm form) {
		
		try{
			LocalidadDestinoForm localidadForm = (LocalidadDestinoForm) form;
			WebApplicationContext ctx = getWebApplicationContext();
			ILocalidadFachada localidadFachada = (ILocalidadFachada) ctx.getBean("localidadFachada");
			
			boolean existe = localidadFachada.existeLocalidadDestino(localidadForm.getLocalidadDestinoDTO());
			if (existe) {
				Validator.addErrorXML(error, Constantes.EXISTE_LOCALIDAD_DESTINO);
			}
			return !existe && localidadForm.validar(error);

		} catch (Throwable t) {
			MyLogger.logError(t);
			Validator.addErrorXML(error, "Error Inesperado");
			return false;
		}			
	}	
}
