package servlet.manager;

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
import model.Hostel;
import service.HistoryService;

/**
 * Servlet implementation class ManageAuditHostel
 */
@WebServlet("/manager/audit/income")
public class ManageAuditHostelIncome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HistoryService historyService = EJBFactory.getServiceEJB(HistoryService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		List<Hostel> hostels = historyService.getHostelAuditIncome();
		session.setAttribute("hostels", hostels);
		ServletContext context = getServletContext();
		context.getRequestDispatcher("/manager/auditIncome.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
