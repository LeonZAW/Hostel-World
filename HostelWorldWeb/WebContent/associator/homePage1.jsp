<%@ page import="model.enumerate.UserType"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage"
	scope="session"></jsp:useBean>
<jsp:useBean id="associator" type="model.Card" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="用户主页-欢迎"/>
</jsp:include>
</head>
<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=associator.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_associator_1.jsp"%>
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
			        <a href="#page-stats" class="block-heading" data-toggle="collapse">数据统计</a>
			        <div id="page-stats" class="block-body collapse in">

			            <div class="stat-widget-container">

			                <div class="stat-widget">
			                    <div class="stat-button">
			                        <p class="title"><%=associator.getLevel()%></p>
			                        <p class="detail">用户等级</p>
			                    </div>
			                </div>

			                <div class="stat-widget">
			                    <div class="stat-button">
			                        <p class="title"><%=session.getAttribute("discount")%>%</p>
			                        <p class="detail">折扣</p>
			                    </div>
			                </div>

			                <div class="stat-widget">
			                    <div class="stat-button">
			                        <p class="title"><%=session.getAttribute("assomoney")%></p>
			                        <p class="detail">用户消费</p>
			                    </div>
			                </div>

			                <div class="stat-widget">
			                    <div class="stat-button">
			                        <p class="title"><%=associator.getAccpoint()%></p>
			                        <p class="detail">积分</p>
			                    </div>
			                </div>


			            </div>
			        </div>
			    </div>
				<div class="block">
					<p class="block-heading">欢迎使用本系统</p>
					<div class="block-body">
						<h2>到期时间</h2>
						<p><%SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							out.println(format.format(associator.getStopDate())); %></p>
					</div>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>