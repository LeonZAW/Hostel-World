<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="注册失败"/>
</jsp:include>
  </head>

  <body class=""> 
    <%@ include file="header_no.jsp" %>
    <div class="row-fluid">
	    <div class="dialog">
	        <div class="block">
	            <p class="block-heading">注册失败</p>
	            <div class="block-body">
	            	<label>注册信息</label>
	            	<p><%=session.getAttribute("register_message") %></p>
	            	<a href="<%= request.getContextPath()+"/login" %>" class="btn btn-default pull-right" style="margin-left:20px;">返回</a>
	            	<a href="<%= request.getContextPath()+"/common/signup.jsp" %>" class="btn btn-primary pull-right">重新注册</a>
	            	<div class="clearfix"></div>
	            </div>
	        </div>
	    </div>
	</div>
  </body>
</html>