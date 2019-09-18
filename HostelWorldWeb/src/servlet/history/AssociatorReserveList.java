package servlet.history;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.Reserved;
import service.HistoryService;

/**
 * Servlet implementation class ReserveList
 */
@WebServlet("/associator/reserve/list")
public class AssociatorReserveList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HistoryService historyService = EJBFactory.getServiceEJB(HistoryService.class);

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
		List<Reserved> reserves = historyService.getAssociatorReserveList(login);
		session.setAttribute("reserves", reserves);
		ServletContext context = request.getServletContext();
		context.getRequestDispatcher("/associator/reserveList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
