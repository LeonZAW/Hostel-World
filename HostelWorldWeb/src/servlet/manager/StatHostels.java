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
import model.Hostel;
import service.HistoryService;
import servlet.history.HostelComparator;

/**
 * Servlet implementation class StatHostels
 */
@WebServlet("/manager/stat/hostels")
public class StatHostels extends HttpServlet {
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
		List<Hostel> hostels = historyService.getHostelStat();
		Collections.sort(hostels, new HostelComparator());
		session.setAttribute("hostels", hostels);
		ServletContext context = getServletContext();
		context.getRequestDispatcher("/manager/hostelStatList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TOdDO Auto-generated method stub
		doGet(request, response);
	}

}
