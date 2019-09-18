package servlet.hostel;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.Reserved;
import model.Room;
import model.enumerate.RoomState;
import service.HostelService;

/**
 * Servlet implementation class RoomCheckInList
 */
@WebServlet("/hostel/room/checkin/pre")
public class RoomCheckInPre extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		int rid = Integer.parseInt(request.getParameter("rid"));
		Room room = hostelService.getRoom(rid);
		session.setAttribute("room", room);
		RoomState state = room.getRoomState();
		String forwardUrl = "/user";
		if(state==RoomState.ONSALE){
			forwardUrl = "/hostel/roomCheckin.jsp";
			session.setAttribute("checkin_date",new Date());
		}
		else if(state==RoomState.RESERVED){
			forwardUrl = "/hostel/roomCheckinReserve.jsp";
			Reserved reserved = hostelService.getServed(rid);
			session.setAttribute("reserved", reserved);
		}
		ServletContext context = request.getServletContext();
		context.getRequestDispatcher(forwardUrl).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
