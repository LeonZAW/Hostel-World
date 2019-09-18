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
import model.Card;
import service.UserInfoService;

/**
 * Servlet implementation class InfoModify
 */
@WebServlet("/associator/info/modify")
public class InfoModifyPre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserInfoService userInfoService = EJBFactory.getServiceEJB(UserInfoService.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
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
		String name = request.getParameter("name");
		String pin = request.getParameter("pin");
		Card card = new Card();
		card.setLogin(login);
		card.setName(name);
		card.setPin(pin);
		userInfoService.modifyCard(card);
		ServletContext context = request.getServletContext();
		context.getRequestDispatcher("/associator/modifyInfoSuccess.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
