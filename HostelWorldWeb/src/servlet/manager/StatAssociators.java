package servlet.manager;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.Card;
import service.HistoryService;
import servlet.history.AssociatorComparator;

/**
 * Servlet implementation class StatHostels
 */
@WebServlet("/manager/stat/associators")
public class StatAssociators extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static HistoryService historyService = EJBFactory.getServiceEJB(HistoryService.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		List<Card> associators = historyService.getAssociatorStat();
		Collections.sort(associators,new AssociatorComparator());
		session.setAttribute("associators", associators);
		ServletContext context = getServletContext();
		context.getRequestDispatcher("/manager/associatorStatList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
