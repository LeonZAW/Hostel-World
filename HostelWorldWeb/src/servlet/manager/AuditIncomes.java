package servlet.manager;

import java.io.IOException;

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
@WebServlet("/manager/audit/incomes")
public class AuditIncomes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AuditService auditService = EJBFactory.getServiceEJB(AuditService.class);
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hlogin = request.getParameter("hlogin");
		auditService.auditIncome(hlogin);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
