package servlet.hostel;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.EJBFactory;
import model.Income;
import model.enumerate.IncomeState;
import model.enumerate.PayType;
import service.HostelService;

/**
 * Servlet implementation class RoomCheckoutCash
 */
@WebServlet("/hostel/income/cash")
public class RoomCheckoutCash extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HostelService hostelService = EJBFactory.getServiceEJB(HostelService.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Income income = getIncome(request);
		hostelService.checkout(income);
	}

	private Income getIncome(HttpServletRequest request) {
		IncomeState state = IncomeState.COMPLETE;
		int rid = Integer.parseInt(request.getParameter("rid"));
		String alogin = request.getParameter("alogin");
		if(alogin != null&&alogin.equals(""))
			alogin = null;
		BigDecimal former_money = new BigDecimal(request.getParameter("fmoney"));
		BigDecimal money = new BigDecimal(request.getParameter("money"));
		BigDecimal reserveMoney = new BigDecimal(request.getParameter("reserve_money"));
		if(reserveMoney.signum()>0){
			state = IncomeState.AUDITING_RESERVE;
		}
		Income income = new Income();
		income.setReserveMoney(reserveMoney);
		income.setState(state.ordinal());
		income.setAssociator_login(alogin);
		income.setFormer_money(former_money);
		income.setMoney(money);
		income.setRid(rid);
		income.setPayType(PayType.CASH.ordinal());
		return income;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
