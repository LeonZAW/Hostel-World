<%@page import="java.util.List"%>
<%@page import="model.Hostel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage"
	scope="session"></jsp:useBean>
<jsp:useBean id="associator" type="model.Card" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="用户主页-酒店列表"/>
</jsp:include>
</head>


<body class="">

	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=associator.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_associator_1.jsp"%>
	<div class="content">
		<div class="header">
			<h1 class="page-title">酒店列表</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<span class="divider">/</span></li>
            <li>酒店列表</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="block">
			        <div class="block-heading">
			            <a>酒店列表</a>
			        </div>
			        <div id="widget2container" class="block-body">
			            <table class="table list">
			                <tbody>
				                <%for (Hostel hostel: (List<Hostel>)session.getAttribute("hostels")){ %>
				                <tr>
				                    <td>
				                        <p><!-- <i class="icon-user"></i> -->酒店名：<%=hostel.getName()%></p>
				                        <p>酒店星级：<%=hostel.getStar()%>星级</p>
				                        <p>酒店地址</p>
				                        <p><%=hostel.getAddress()%></p>
				                        <p>酒店简介</p>
				                        <p><%=hostel.getSynopsis()%></p>
				                        <a href="<%=request.getContextPath() + "/associator/reserve/room/list?hlogin=" + hostel.getLogin() %>" class="btn btn-primary pull-right">预定房间</a>
				                    </td>
				                </tr>
			                    <%} %>
			                </tbody>
			            </table>
			        </div>
		    	</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>
