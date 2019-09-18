package servlet.hostel;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.Audit;
import model.message.CreditMessage;
import service.AuditService;
import service.CreditService;

/**
 * Servlet implementation class AuditHostel
 */
@WebServlet("/audit/hostel")
public class AuditHostel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AuditService auditService = EJBFactory.getServiceEJB(AuditService.class);
	CreditService creditService = EJBFactory.getServiceEJB(CreditService.class);
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
		ServletContext context = request.getServletContext();
		if(!checkCredit(request, response, session, context))
			return;
		Audit audit = buildAudit(request, session);
		
		auditService.openHostel(audit);
		context.getRequestDispatcher("/hostel/auditSuccess.jsp").forward(request, response);
	}

	private boolean checkCredit(HttpServletRequest request, HttpServletResponse response, HttpSession session, ServletContext context)
			throws ServletException, IOException {
		String credit = request.getParameter("credit");
		String password = request.getParameter("password");
		
		CreditMessage creditMessage = creditService.check(credit, password);
		if(!creditMessage.check){
			session.setAttribute("credit_message", creditMessage.message);
			context.getRequestDispatcher("/common/creditError.jsp").forward(request, response);
			return false;
		}
		return true;
	}

	private Audit buildAudit(HttpServletRequest request, HttpSession session) {
		String login = (String) session.getAttribute("login");
		String name = request.getParameter("name");
		int star = Integer.valueOf(request.getParameter("star"));
		String address = request.getParameter("address");
		String synopsis = request.getParameter("synopsis");
		String credit = request.getParameter("credit");
		String password = request.getParameter("password");
		Audit audit = new Audit();
		audit.setLogin(login);
		audit.setName(name);
		audit.setStar(star);
		audit.setAddress(address);
		audit.setSynopsis(synopsis);
		audit.setCreditId(credit);
		audit.setCreditPassword(password);
		return audit;
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
