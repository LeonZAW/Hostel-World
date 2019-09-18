package servlet.associator;

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
import model.Reserved;
import model.enumerate.ReserveState;
import service.HostelService;

/**
 * Servlet implementation class ReserveRoom
 */
@WebServlet("/associator/reserve/room")
public class ReserveRoom extends HttpServlet {
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
		
		if(!(boolean) session.getAttribute("OL")){
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		String login = (String) session.getAttribute("login");
		BigDecimal moneyPerDay = new BigDecimal(request.getParameter("money"));
		Reserved reserved = getReserved(request, login);
		if(hostelService.canReserveForMoney(reserved,moneyPerDay)){
			hostelService.setReserved(reserved,moneyPerDay);
			response.getWriter().append("true");
		}else
			response.getWriter().append("false");
	}

	private Reserved getReserved(HttpServletRequest request, String login) {
		int rid = Integer.parseInt(request.getParameter("rid"));
		Date begin = null;
		Date end = null;
		try {
			begin = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("begin"));
			end = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("end"));
		} catch (ParseException e) {
		}
		ReserveState state = ReserveState.RESERVED;
		Reserved reserved = new Reserved();
		reserved.setAssociator_login(login);
		reserved.setRid(rid);
		reserved.setReserve_state(state.ordinal());
		reserved.setStarttime(begin);
		reserved.setEndtime(end);
		return reserved;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
