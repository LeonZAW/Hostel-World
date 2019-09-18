package servlet.hostel;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.Schedule;
import service.HostelService;

/**
 * Servlet implementation class Publish
 */
@WebServlet("/hostel/publish")
public class Publish extends HttpServlet {
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
		addSchedule(request, session);
		request.getServletContext().getRequestDispatcher("/hostel/publishSuccess.jsp").forward(request, response);
	}

	private void addSchedule(HttpServletRequest request, HttpSession session) {
		String login = (String) session.getAttribute("login");
		String roomNo = request.getParameter("no");
		BigDecimal price = new BigDecimal(request.getParameter("price"));
		int roomId = hostelService.getRoom(login, roomNo).getId();
		Date begin = null;
		Date end = null;
		try {
			begin = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("begin"));
			end = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("end"));
		} catch (ParseException e) {
			//datetime picker保证日期格式正确
			System.out.println("日期格式错误");
			return;
		}
		Schedule schedule = new Schedule();
		schedule.setRid(roomId);
		schedule.setPrice(price);
		schedule.setBegintime(begin);
		schedule.setEndtime(end);
		hostelService.addSchedule(schedule);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
