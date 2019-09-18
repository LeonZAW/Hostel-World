package servlet.common;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import factory.EJBFactory;
import service.AuditService;

/**
 * Servlet implementation class CheckCheckinDate
 */
@WebServlet("/manager/audit/incomes/pay")
public class CheckManagerCredit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AuditService auditService = EJBFactory.getServiceEJB(AuditService.class);
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BigDecimal money = new BigDecimal(request.getParameter("money"));
		boolean test = auditService.canPay(money);
		String ajaxReturnText = test?"true":"false";
		response.getWriter().append(ajaxReturnText);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
