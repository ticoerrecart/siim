package ar.com.siim.struts.actions;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class TilesForwardAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String page = request.getParameter("page");
		
		Enumeration<String> enumParamNames = request.getParameterNames();
		
		while (enumParamNames.hasMoreElements()) {
			
			String paramName = enumParamNames.nextElement();
			String paramValue = request.getParameter(paramName);
			request.setAttribute(paramName, paramValue);			
		}
		
		return mapping.findForward(page);
	}

}
