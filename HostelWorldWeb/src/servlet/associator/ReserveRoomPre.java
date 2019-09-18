package servlet.associator;

import java.io.IOException;
import java.math.BigDecimal;

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
 * Servlet implementation class ReserveRoomPre
 */
@WebServlet("/associator/reserve/room/pre")
public class ReserveRoomPre extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		int rid = Integer.parseInt(request.getParameter("rid"));
		Room room = hostelService.getRoom(rid);
		BigDecimal money = hostelService.getMoney(rid);
		session.setAttribute("room", room);
		session.setAttribute("money", money);
		ServletContext context = request.getServletContext();
		context.getRequestDispatcher("/associator/roomReserve.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
