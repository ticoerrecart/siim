package ar.com.siim.filtro;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.com.siim.utils.Constantes;

public class LoginFilter implements Filter {

	private FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String metodo = req.getParameter("metodo");

		//int param = Integer.parseInt((req.getParameter("param")==null)?"0":req.getParameter("param"));

		try {
			if (session.getAttribute(Constantes.USER_LABEL_SESSION) != null
					|| (metodo != null && metodo.equals("login"))) {

				chain.doFilter(request, response);

			} else {
				RequestDispatcher dispatcher = filterConfig.getServletContext()
						.getRequestDispatcher("/jsp/login.jsp");
				dispatcher.forward(request, response);
			}

		} catch (Exception eie) {
			System.out.println(eie.getMessage());
		}
	}

	public void destroy() {

		this.filterConfig = null;

	}

}
