<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="提交审批成功"/>
</jsp:include>
  </head>

  <body class=""> 
    <%@ include file="../common/header_no.jsp" %>
    <div class="row-fluid">
	    <div class="dialog">
	        <div class="block">
	            <p class="block-heading">激活成功</p>
	            <div class="block-body">
	            	<p>修改已提交，待管理员审核</p>
	            	<a href="<%= request.getContextPath()+"/user" %>" class="btn btn-default pull-right">返回</a>
	            	<div class="clearfix"></div>
	            </div>
	        </div>
	    </div>
	</div>
  </body>
</html>