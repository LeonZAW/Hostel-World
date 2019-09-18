<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="发布失败"/>
</jsp:include>
  </head>

  <body class=""> 
    <%@ include file="../common/header_no.jsp" %>
    <div class="row-fluid">
	    <div class="dialog">
	        <div class="block">
	            <p class="block-heading">发布房间失败</p>
	            <div class="block-body">
	            	<label>错误信息</label>
	            	<p><%=session.getAttribute("addroom_message") %></p>
	            	<a href="<%= request.getContextPath()+"/user" %>" class="btn btn-default pull-right">返回</a>
	            	<a href="<%= request.getContextPath()+"/hostel/roomManage.jsp" %>" class="btn btn-default pull-right">重新添加</a>
	            	<div class="clearfix"></div>
	            </div>
	        </div>
	    </div>
	</div>
  </body>
</html>