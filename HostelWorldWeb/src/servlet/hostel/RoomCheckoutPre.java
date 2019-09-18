package servlet.hostel;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.Income;
import model.Room;
import service.CardStateService;
import service.HostelService;

/**
 * Servlet implementation class RoomCheckInList
 */
@WebServlet("/hostel/room/checkout/pre")
public class RoomCheckoutPre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);
	private static CardStateService cardStateService = EJBFactory.getServiceEJB(CardStateService.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int rid = Integer.parseInt(request.getParameter("rid"));
		Income income = hostelService.getIncome(rid);
		System.out.println(income.getId());
		Room room = hostelService.getRoom(rid);
		if(income.getAssociator_login()!=null){
			int point = cardStateService.getAccPoint(income.getAssociator_login());
			session.setAttribute("point", point);
		}
		session.setAttribute("income", income);
		session.setAttribute("room", room);
		String forwardUrl = "/hostel/roomCheckout.jsp";
		/*if(state==RoomState.ONSALE){
			forwardUrl = "/hostel/roomCheckin.jsp";
			session.setAttribute("checkin_date",new Date());
		}
		else if(state==RoomState.RESERVED){
			forwardUrl = "/hostel/roomCheckinReserve.jsp";
			Reserved reserved = hostelService.getServedLogin(rid);
			session.setAttribute("reserved", reserved);
		}*/
		ServletContext context = request.getServletContext();
		context.getRequestDispatcher(forwardUrl).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
