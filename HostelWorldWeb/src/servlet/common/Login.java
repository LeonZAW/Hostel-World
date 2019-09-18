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

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session!=null) {
			boolean online = (boolean) session.getAttribute("OL");
			boolean logout = request.getParameter("logout")!=null;
			if (logout&&online) {
				session.setAttribute("OL", false);
	        }else if(online&&!logout){
	        	response.sendRedirect(request.getContextPath()+"/user");
	        	return;
	        }
        }else{
        	session = request.getSession(true);
        	session.setAttribute("OL", false);
        }
		
		ServletContext context = request.getServletContext();
		
		String login = (String) session.getAttribute("new_login");
		if(login==null){	
			login="";
	        Cookie[] cookies = request.getCookies();
	        if (cookies!=null) {
				for (Cookie cookie : cookies) {
		        	if (cookie.getName().equals("LoginCookie")) {
		                login=cookie.getValue();
		                break;
		            }
				}
			}
		}
        request.setAttribute("cookieLogin", login);
		context.getRequestDispatcher("/common/login.jsp").forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
