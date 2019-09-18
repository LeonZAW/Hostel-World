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
 * Servlet implementation class ManageAuditDelete
 */
@WebServlet("/audit/repudiate")
public class ManageAuditRepudiate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AuditService auditService = EJBFactory.getServiceEJB(AuditService.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		auditService.repudiateAudit(id);
		response.getWriter().append("…Û≈˙ ß∞‹£°");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
