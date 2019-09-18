package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class ChineseFliter
 */
@WebFilter({"/*","*.jsp"})
public class ChineseFliter implements Filter {


	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/*HttpSession session = ((HttpServletRequest) request).getSession();
	    HttpServletResponse rep = (HttpServletResponse) response;
	    HttpServletRequest req = (HttpServletRequest) request;

	    String url = req.getRequestURI();
	    if (url.contains("user/login")
	            || url.endsWith(".css")
	            || url.endsWith(".js")
	            || url.endsWith(".png")
	            || url.endsWith(".jpg")) {
	        chain.doFilter(request, response);
	    } else if ((session == null || session.getAttribute("user") == null)) {
	        rep.sendRedirect(req.getContextPath() + "/user/login);
	    } else {
	        chain.doFilter(request, response);
	    }*/
		String url = ((HttpServletRequest) request).getRequestURI();
//		System.out.println("url:"+url);
		if(url.endsWith(".css")||url.endsWith(".js")||url.endsWith(".png")||url.endsWith(".jpg")){
//			System.out.println(url);
		}
		else{
		// place your code here
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// pass the request along the filter chain
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
