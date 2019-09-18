package servlet.hostel;

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
import model.Checkin;
import service.HostelService;

/**
 * Servlet implementation class RoomCheckInList
 */
@WebServlet("/hostel/room/checkin")
public class RoomCheckIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Checkin checkin = getCheckin(request);
		hostelService.checkin(checkin);
		System.out.println("添加成功");
	}

	private Checkin getCheckin(HttpServletRequest request) {
		int rid = Integer.parseInt(request.getParameter("rid"));
		String alogin = request.getParameter("associator_login");
		String name = request.getParameter("name");
		String pin = request.getParameter("pin");
		if (alogin.equals("")) {
			alogin=null;
		}
		if (name.equals("")) {
			name=null;
		}
		if (pin.equals("")) {
			pin=null;
		}
		Date begin = null;
		Date end = null;
		try {
			begin = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("begin"));
			end = DateFormat.getDateInstance(DateFormat.DEFAULT).parse(request.getParameter("end"));
		} catch (ParseException e) {
			System.out.println("日期格式错误");
		}
		Checkin checkin = new Checkin();
		checkin.setRid(rid);
		checkin.setAssociator_login(alogin);
		checkin.setName(name);
		checkin.setPin(pin);
		checkin.setCheckintime(begin);
		checkin.setCheckouttime(end);
		return checkin;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
