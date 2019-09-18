package servlet.associator;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.EJBFactory;
import service.HostelService;

/**
 * Servlet implementation class ReserveCancel
 */
@WebServlet("/cancel/reserve")
public class ReserveCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reserveid = Integer.parseInt(request.getParameter("reserveid"));
		hostelService.cancelReserve(reserveid);
		ServletContext context = request.getServletContext();
		context.getRequestDispatcher("/associator/reserveCancel.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
