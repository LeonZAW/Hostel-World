package servlet.associator;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.message.RelinquishMessage;
import service.CardStateService;

/**
 * Servlet implementation class Recover
 */
@WebServlet("/user/suspend")
public class Suspend extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CardStateService cardStateService = EJBFactory.getServiceEJB(CardStateService.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		if(!(boolean) session.getAttribute("OL")){
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String login = (String) session.getAttribute("login");
		String credit = request.getParameter("credit");
		
		RelinquishMessage relinquishMessage = cardStateService.relinquish(login,credit);
		session.setAttribute("suspend_message", relinquishMessage.message);
		ServletContext context = request.getServletContext();
		String forwardURI = "/associator/suspend"+(relinquishMessage.check?"Success":"Error")+".jsp";
		context.getRequestDispatcher(forwardURI).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
