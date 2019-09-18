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

import factory.EJBFactory;
import service.HostelService;

/**
 * Servlet implementation class RoomPayment
 */
@WebServlet("/hostel/room/payment")
public class RoomPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int roomId = 1;
//				Integer.parseInt(request.getParameter("id"));
		Date begin = null;
		Date end = null;
		try {
			begin = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("begin"));
			end = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("end"));
		} catch (ParseException e) {
			response.getWriter().append("日期格式错误");
			return;
		}
		BigDecimal price = hostelService.getPrice(roomId, begin, end);
		response.getWriter().append(price.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
