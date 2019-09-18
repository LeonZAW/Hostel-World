package servlet.hostel;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.Room;
import model.message.RoomManageMessage;
import service.HostelService;

@WebServlet("/hostel/room/add")
public class RoomAddition extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}

		if (!(boolean) session.getAttribute("OL")) {
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		RoomManageMessage manageMessage = addRoom(request, session);
		String forwardURL = "/hostel/addRoom"+(manageMessage.check?"Success":"Error")+".jsp";
		session.setAttribute("addroom_message", manageMessage.message);
		request.getServletContext().getRequestDispatcher(forwardURL).forward(request, response);
		
	}

	private RoomManageMessage addRoom(HttpServletRequest request, HttpSession session) {
		String login = (String) session.getAttribute("login");
		String roomNo = request.getParameter("no");
		int limit = Integer.parseInt(request.getParameter("people"));
		String description = request.getParameter("description");
		Room room = new Room();
		room.setLogin(login);
		room.setLimitNo(limit);
		room.setRoomNo(roomNo);
		room.setDescription(description);
		RoomManageMessage manageMessage = hostelService.addRoom(room);
		return manageMessage;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
