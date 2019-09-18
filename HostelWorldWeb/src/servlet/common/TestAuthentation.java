package servlet.common;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.EJBFactory;
import model.enumerate.UserType;
import model.message.LoginMessage;
import service.LoginService;

/**
 * Servlet implementation class TestAuthentation
 */
@WebServlet("/test/authentation")
public class TestAuthentation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static LoginService loginService = EJBFactory.getServiceEJB(LoginService.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String alogin = request.getParameter("alogin");
		String password = request.getParameter("password");
		LoginMessage loginMessage = loginService.check(alogin, password);
		System.out.println(loginMessage.userType==UserType.ASSOCIATOR);
		if (loginMessage.isauthentication&&loginMessage.userType==UserType.ASSOCIATOR) {
			response.getWriter().append("true");
		}
		else response.getWriter().append("false");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
