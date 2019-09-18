package servlet.hostel;

import java.io.IOException;

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
@WebServlet("/modify/hostel")
public class ModifyHostel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AuditService auditService = EJBFactory.getServiceEJB(AuditService.class);
	private static CreditService creditService = EJBFactory.getServiceEJB(CreditService.class);
	
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
		if(!checkOldCredit(request, response, session )){
			response.getWriter().append("原银行账号密码错误");			
			return;
		}
		if(!checkCredit(request, response, session )){
			response.getWriter().append("新银行账号密码错误");			
			return;
		}
		Audit audit = buildAudit(request, session);
		
		auditService.modifyHostel(audit);
		response.getWriter().append("true");
	}

	private boolean checkCredit(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		String credit = request.getParameter("credit");
		String password = request.getParameter("password");
		
		CreditMessage creditMessage = creditService.check(credit, password);
		if(!creditMessage.check){
			session.setAttribute("credit_message", creditMessage.message);
			return false;
		}
		return true;
	}
	
	private boolean checkOldCredit(HttpServletRequest request, HttpServletResponse response, HttpSession session )
			throws ServletException, IOException {
		String credit = request.getParameter("oldCredit");
		String password = request.getParameter("oldPassword");
		System.out.println(credit+","+password);
		CreditMessage creditMessage = creditService.check(credit, password);
		return creditMessage.check;
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
