package servlet.hostel;

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
import model.Room;
import service.HostelService;

/**
 * Servlet implementation class Publish
 */
@WebServlet("/hostel/publishSchedule")
public class PublishSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		if (!(boolean) session.getAttribute("OL")) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		String login = (String) session.getAttribute("login");
		List<Room> rooms = hostelService.getOffScheduleRooms(login);
		session.setAttribute("rooms", rooms);
		ServletContext context = getServletContext();
		context.getRequestDispatcher("/hostel/publishSchedule.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
