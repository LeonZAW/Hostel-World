package servlet.hostel;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.EJBFactory;
import service.HostelService;

/**
 * Servlet implementation class RoomCheckInList
 */
@WebServlet("/hostel/room/checkin/reserve")
public class RoomCheckInReserve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reserveid = Integer.parseInt(request.getParameter("reserveid"));
		hostelService.checkinByReserve(reserveid);
		System.out.println("Ìí¼Ó³É¹¦");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
