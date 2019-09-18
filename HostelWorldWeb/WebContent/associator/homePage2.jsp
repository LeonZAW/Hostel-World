<%-- 
	<p>您已经于<%=associator.getSuspendDate()%>挂起，</p>
	<p>您的会员将于<%=associator.getDeadDate()%>被停用，</p>
	<a href="<%=request.getContextPath() + "/associator/recover.jsp" %>">充值并恢复使用</a>
	<a href="<%=request.getContextPath() + "/associator/suspend.jsp" %>">停用</a>
 --%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="model.enumerate.UserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage"
	scope="session"></jsp:useBean>
<jsp:useBean id="associator" type="model.Card" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="用户主页-您已被挂起"/>
</jsp:include>
</head>
<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=associator.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_associator_2.jsp"%>
	<div class="content">
		<div class="header">
			<h1 class="page-title">主页</h1>
		</div>
		<ul class="breadcrumb">
			<li>主页</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="alert alert-info">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<strong><%=login_message.message%>：</strong>欢迎来到用户主页
				</div>
				<div class="block">
					<p class="block-heading">您已被挂起</p>
					<div class="block-body">
						<%SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); %>
						<h2>您已经于<%=format.format(associator.getSuspendDate())%>被挂起</h2>
						<h2>您的会员将于<%=format.format(associator.getDeadDate())%>被停用</h2>
						<p>
							<a class="btn btn-primary btn-large" href="<%=request.getContextPath() + "/associator/recover.jsp"%>">充值并激活</a>
						</p>
					</div>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>