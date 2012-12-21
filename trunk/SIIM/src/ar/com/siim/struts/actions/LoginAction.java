package ar.com.siim.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.struts.DispatchActionSupport;

import ar.com.siim.dto.UsuarioDTO;
import ar.com.siim.fachada.ILoginFachada;
import ar.com.siim.struts.actions.forms.LoginForm;
import ar.com.siim.utils.Constantes;

public class LoginAction extends DispatchActionSupport {

	@SuppressWarnings("unchecked")
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoLogin";

		try {

			LoginForm loginForm = (LoginForm) form;

			String usuario = loginForm.getUsuario();
			String password = loginForm.getPassword();

			WebApplicationContext ctx = getWebApplicationContext();
			ILoginFachada loginFachada = (ILoginFachada) ctx.getBean("loginFachada");

			UsuarioDTO usrDTO = loginFachada.login(usuario, password);

			request.getSession().setAttribute(Constantes.USER_LABEL_SESSION, usrDTO);

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "errorLogin";
		}

		return mapping.findForward(strForward);
	}

	@SuppressWarnings("unchecked")
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String strForward = "exitoLogout";

		try {

			HttpSession session = request.getSession();
			session.setAttribute(Constantes.USER_LABEL_SESSION, null);
			session.invalidate();

		} catch (Exception e) {
			request.setAttribute("error", e.getMessage());
			strForward = "errorLogin";
		}

		return mapping.findForward(strForward);
	}
}
