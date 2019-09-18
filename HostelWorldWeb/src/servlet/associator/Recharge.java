package servlet.associator;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.message.RechargeMessage;
import service.CardStateService;

/**
 * Servlet implementation class Activate
 */
@WebServlet("/user/recharge")
public class Recharge extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private static final BigDecimal ACTIVATE_MONEY = new BigDecimal(1000);
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
		String password = request.getParameter("password");
		int money = Integer.valueOf(request.getParameter("money"));
		ServletContext context = request.getServletContext();
		RechargeMessage message = cardStateService.recharge(login, credit, password, new BigDecimal(money));
		session.setAttribute("recharge_message", message.message);
		String forwardURI = "/associator/recharge"+ (message.check?"Success":"Error")+ ".jsp";
		context.getRequestDispatcher(forwardURI).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
