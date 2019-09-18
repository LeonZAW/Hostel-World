<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="登录"/>
</jsp:include>

  </head>

  <body class=""> 
    <%@ include file="../common/header_no.jsp" %>
    
    <div class="row-fluid">
	    <div class="dialog">
	        <div class="block">
	            <p class="block-heading">登录</p>
	            <div class="block-body">
	                <form method="post" action="<%= request.getContextPath()+"/user" %>">
	                    <label>用户名</label>
	                    <input type="text" class="span12" name='login' value='<%= request.getAttribute("cookieLogin")%>'>
	                    <label>密码</label>
	                    <input type="password" class="span12" type='password' name='password' value=''>
	                    <input type='submit' class="btn btn-primary pull-right" name='Submit' value='登录'/>
	                    <a href="<%=request.getContextPath() + "/common/signup.jsp" %>">注册</a>
	                    <div class="clearfix"></div>
	                </form>
	            </div>
	        </div>
    	</div>
	</div>

    
  </body>
</html>
