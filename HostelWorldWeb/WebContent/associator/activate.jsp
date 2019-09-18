<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="model.Card"%>
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
	<jsp:param name="title" value="用户主页-激活"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=associator.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_associator_0.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">激活</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>激活</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="block">
					<p class="block-heading">欢迎使用本系统</p>
					<div class="block-body">
						<h2>充值1000以上来完成激活</h2>
						<p>
							会员卡余额：<%NumberFormat nf = NumberFormat.getInstance();%>
        								<%=nf.format(associator.getMoney())%></p>
					</div>
				</div>

				<div class="well">
					<form id="tab2" method="post"
						action="<%=request.getContextPath() + "/user/activate"%>">
						<label>充值金额</label> <input type='number' name='money' value=''
							class="input-xlarge"> <label>银行账户</label> <input
							type='text' name='credit' value='' class="input-xlarge">
						<label>账户密码</label> <input type='password' name='password'
							value='' class="input-xlarge">
						<div>
							<input type='submit' name='Submit' class="btn btn-primary"
								value='充值'>
						</div>
					</form>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>