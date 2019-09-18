package servlet.common;

import java.io.IOException;
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
 * Servlet implementation class CheckCheckinDate
 */
@WebServlet("/check/checkin/date")
public class CheckCheckinDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rid = Integer.parseInt(request.getParameter("rid"));
		Date begin = null;
		Date end = null;
		try {
//			System.out.println(request.getParameter("begin"));
			begin = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("begin"));
			end = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("end"));
		} catch (ParseException e) {
			response.getWriter().append("false");
			return;
		}
		if(hostelService.canCheckin(rid,begin,end))
			response.getWriter().append("true");
		else
			response.getWriter().append("false");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
