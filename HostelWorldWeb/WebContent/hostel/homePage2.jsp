<%@ page import="model.enumerate.UserType"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage" scope="session"></jsp:useBean>
<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="酒店主页-欢迎"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_2.jsp"%>
	<div class="content">
		<div class="header">
			<h1 class="page-title">主页</h1>
		</div>
		<ul class="breadcrumb">
			<li>主页</li>
		</ul>
		<div class="container-fluid">
			<div class="row-fluid">
		<%NumberFormat nf = NumberFormat.getInstance();%>

				<div class="alert alert-info">
					<button type="button" class="close" data-dismiss="alert">×</button>
					<strong><%=login_message.message%>：</strong>欢迎来到酒店主页
				</div>

				<div class="block">
			        <a href="#page-stats" class="block-heading" data-toggle="collapse">数据统计</a>
			        <div id="page-stats" class="block-body collapse in">

			            <div class="stat-widget-container">
			            	<div class="stat-widget">
			                    <div class="stat-button">

			                    </div>
			                </div>
			                <div class="stat-widget">
			                    <div class="stat-button">
			                        <p class="title"><%=session.getAttribute("peopletime")%></p>
			                        <p class="detail">入住人次</p>
			                    </div>
			                </div>

			                <div class="stat-widget">
			                    <div class="stat-button">
			                        <p class="title">￥<%=nf.format(session.getAttribute("hostelhpincome"))%></p>
			                        <p class="detail">收入</p>
			                    </div>
			                </div>
			            </div>
			        </div>
			    </div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>