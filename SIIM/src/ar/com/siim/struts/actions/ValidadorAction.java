package ar.com.siim.struts.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.DispatchActionSupport;

import ar.com.siim.struts.utils.Validator;

public class ValidadorAction extends DispatchActionSupport {

	public ActionForward validar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		StringBuffer error = Validator.abrirXML();
		PrintWriter out = null;
		try {
			String validador = request.getParameter("validador");
			String formulario = request.getParameter("form");
			String idFormJsp = request.getParameter("formJsp");

			ActionForm actionForm = (ActionForm) Class.forName(
					"ar.com.siim.struts.actions.forms." + formulario).newInstance(); 
			BeanUtils.populate(actionForm, request.getParameterMap());

			Class[] clases = new Class[2];
			clases[0] = StringBuffer.class;
			clases[1] = ActionForm.class;
			Method m = this.getClass().getMethod(validador, clases);
			Object[] objects = new Object[2];
			objects[0] = error;
			objects[1] = actionForm;
			m.invoke(this, objects);

			Validator.addIdJspXML(error, idFormJsp);
			Validator.cerrarXML(error);

		} catch (Exception e) {
			error = new StringBuffer(Validator.XML_HEADER);
			Validator.addErrorXML(error, "ValidadorAction :" + e.getCause() + "|" + e.getMessage());
			/*
			 * new Exception(e.getMessage()); request.setAttribute("titulo",
			 * "Error Inesperado"); request.setAttribute("mensaje",
			 * e.getMessage()); return mapping.findForward("error");
			 */
		} finally {
			try {
				out = response.getWriter();
			} catch (IOException e) {
			}
			out.write(error.toString());
			response.setContentType("text/xml");
		}

		return null;
	};

}
