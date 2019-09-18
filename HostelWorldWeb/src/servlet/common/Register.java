package servlet.common;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.enumerate.UserType;
import model.message.RegisterMessage;
import service.RegisterService;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RegisterService registerService = EJBFactory.getServiceEJB(RegisterService.class);
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		int userType = Integer.parseInt(request.getParameter("type"));
		RegisterMessage registerMessage = registerService.register(password, UserType.values()[userType]);
		if (registerMessage.check) {
			System.out.println(registerMessage.login);
		}
		HttpSession session = request.getSession(true);
		ServletContext context = request.getServletContext();
		session.setAttribute("register_message", registerMessage.message);
		session.setAttribute("new_login", registerMessage.login);
		String forwardURI = "/common/signup"+(registerMessage.check?"Success":"Error")+".jsp";
		context.getRequestDispatcher(forwardURI).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
