<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="注册成功"/>
</jsp:include>
  </head>

  <body class=""> 
    <%@ include file="header_no.jsp" %>
    <div class="row-fluid">
	    <div class="dialog">
	        <div class="block">
	            <p class="block-heading">注册</p>
	            <div class="block-body">
	            	<label>注册信息</label>
	            	<p><%=session.getAttribute("register_message") %></p>
	            	<label>账号</label>
	            	<p>您的账号为：<%=session.getAttribute("new_login") %>，请保存好</p>
	            	<a href="<%= request.getContextPath()+"/login" %>" class="btn btn-primary pull-right">去登录</a>
	            	<div class="clearfix"></div>
	            </div>
	        </div>
	    </div>
	</div>
  </body>
</html>