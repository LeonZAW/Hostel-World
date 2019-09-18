<%@page import="model.Card"%>
<%@ page import="model.enumerate.UserType"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage" scope="session"></jsp:useBean>
<jsp:useBean id="associator" type="model.Card" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="用户主页-修改信息"/>
</jsp:include>

</head>


<body class="">

	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=associator.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_associator_1.jsp"%>
	<%NumberFormat nf = NumberFormat.getInstance();%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">修改信息</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">修改信息</a></li>
			<li><span class="divider">/</span></li>
            <li>修改信息</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="block">
					<p class="block-heading">欢迎使用本系统</p>
					<div class="block-body">
						<h2>修改账户信息</h2>
					</div>
				</div>

				<div class="well">

					<div id="myTabContent" class="tab-content">
							<form id="tab2" method="post"
								action="<%=request.getContextPath() + "/associator/info/modify"%>">
								<label>姓名</label> 
								<input
									type='text' name='name' value='<%=associator.getName()%>' class="input-xlarge">
								<label>身份证号</label> 
								<input type='text' name='pin'
									value='<%=associator.getPin()%>' class="input-xlarge">
								<div>
									<input type='submit' name='Submit' class="btn btn-primary"
										value='修改'>
								</div>
							</form>
					</div>

				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>