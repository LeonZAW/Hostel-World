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
	<jsp:param name="title" value="用户主页-您未激活"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=associator.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_associator_0.jsp"%>
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
					<p class="block-heading">您未激活</p>
					<div class="block-body">
						<h2>请充值账户完成激活</h2>
						<p>
							<a class="btn btn-primary btn-large" href="<%=request.getContextPath() + "/associator/activate.jsp"%>">充值并激活</a>
						</p>
					</div>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>