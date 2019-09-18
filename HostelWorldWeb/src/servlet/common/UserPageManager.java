package servlet.common;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import factory.EJBFactory;
import model.Card;
import model.Hostel;
import model.enumerate.AssociatorState;
import model.enumerate.HostelState;
import model.enumerate.UserType;
import model.message.LoginMessage;
import service.HistoryService;
import service.LoginService;
import service.UserInfoService;

/**
 * Servlet implementation class UserPage
 */
@WebServlet("/user")
public class UserPageManager extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginService loginService = EJBFactory.getServiceEJB(LoginService.class);
	UserInfoService userInfoService = EJBFactory.getServiceEJB(UserInfoService.class);
	private static HistoryService historyService = EJBFactory.getServiceEJB(HistoryService.class);
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session==null){
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
		
		if((boolean) session.getAttribute("OL")){
			loadJSPByLogin(request, response, session);
			return;
		}
		
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		if(login==null){
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		}
				
		saveCookie(request, response, login);
		
		
		LoginMessage message = loginService.check(login, password);
		if(!message.isauthentication){
			loadJSPByErrorLoginMessage(request, response, session, message);
			return;
		}
		session.setAttribute("login", login);
		session.setAttribute("OL", true);
		session.setAttribute("login_message", message);
		loadJSPByLogin(request, response, session);
	}

	private void loadJSPByErrorLoginMessage(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, LoginMessage message) throws ServletException, IOException {
		session.setAttribute("login_message", message.message);
		ServletContext context = request.getServletContext();
		context.getRequestDispatcher("/common/loginError.jsp").forward(request, response);
	}

	private void loadJSPByLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		ServletContext context = request.getServletContext();
		String login = (String) session.getAttribute("login");
		LoginMessage loginMessage = (LoginMessage) session.getAttribute("login_message");
		
		if(loginMessage.userType==UserType.ASSOCIATOR){
			Card associator = userInfoService.getAssociator(login);
			session.setAttribute("associator", associator);
			session.setAttribute("state", AssociatorState.values()[associator.getState()]);
			session.setAttribute("discount", historyService.discount(login));
			session.setAttribute("assomoney", historyService.getAssoMoney(login));
			session.setAttribute("renttime", historyService.renttime(login));
			context.getRequestDispatcher("/associator/homePage"+associator.getState()+".jsp").forward(request, response);
		}else if(loginMessage.userType==UserType.HOSTEL){
			Hostel hostel = userInfoService.getHostel(login);
			session.setAttribute("hostel", hostel);
			session.setAttribute("state", HostelState.values()[hostel.getState()]);
			session.setAttribute("peopletime",historyService.getPeopleTime(login));
			session.setAttribute("hostelhpincome",historyService.getHostelIncome(login));
			context.getRequestDispatcher("/hostel/homePage"+hostel.getState()+".jsp").forward(request, response);
		}else {//loginMessage.userType==UserType.MANAGER
			session.setAttribute("new_hostel_open_audit", historyService.getAuditHostelOpenNum());
			session.setAttribute("new_hostel_modify_audit", historyService.getAuditHostelModifyNum());
			session.setAttribute("new_hostel_income_audit", historyService.getAuditHostelIncomeNum());
			session.setAttribute("user_num", historyService.getUserNum());
			session.setAttribute("hostel_num", historyService.getHostelNum());
			session.setAttribute("world_in_num", historyService.getHostelWorldIncome());
			session.setAttribute("world_out_num", historyService.getHosgtelWorldOut());
			context.getRequestDispatcher("/manager/homePage.jsp").forward(request, response);
		}
	}

	private void saveCookie(HttpServletRequest request, HttpServletResponse response, String login) {
		boolean cookieFound = false;
		Cookie cookie = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals("LoginCookie")) {
					cookieFound = true;
					break;
				}
			}
		}
		if(cookieFound){
			if (!login.equals(cookie.getValue())) {
				cookie.setValue(login);
			}
		}else{
			cookie = new Cookie("LoginCookie", login);
			cookie.setMaxAge(Integer.MAX_VALUE);
		}
		response.addCookie(cookie);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
