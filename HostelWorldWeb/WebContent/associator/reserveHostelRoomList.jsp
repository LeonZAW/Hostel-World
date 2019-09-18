<%@page import="java.util.List"%>
<%@page import="model.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage"
	scope="session"></jsp:useBean>
<jsp:useBean id="associator" type="model.Card" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="用户主页-房间列表"/>
</jsp:include>
</head>


<body class="">

	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=associator.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_associator_1.jsp"%>
	<div class="content">
		<div class="header">
			<h1 class="page-title">房间列表</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<span class="divider">/</span></li>
            <li><a href="<%=request.getContextPath() + "/associator/reserve/hostel/list"%>">酒店列表</a></li>
			<li><span class="divider">/</span></li>
            <li>房间列表</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
                <%List<Room> rooms = (List<Room>)session.getAttribute("rooms");
                if(rooms.isEmpty()){%>
	            <div class="block">
					<p class="block-heading">房间信息</p>
					<div class="block-body">
						<h2>无房间可预订</h2>
						<p>
							<a class="btn btn-primary btn-large" href="<%=request.getContextPath() + "/associator/reserve/hostel/list"%>">返回</a>
						</p>
					</div>
				</div>
	            <%}else{%>
				<div class="block">
			        <div class="block-heading">
			            <a>房间列表</a>
			        </div>
			        <div id="widget2container" class="block-body">
			            <table class="table list">
			                <tbody>
				                <%for (Room room: rooms){ %>
				                <tr>
				                    <td>
				                        <p>房间号：<%=room.getRoomNo()%></p>
										<%-- <p>房间状态：<%=room.getRoomState()%></p> --%>
										<p>限住人数：<%=room.getLimitNo()%>人</p>
										<p>简介：<%=room.getDescription()%></p>
				                        <a href="<%=request.getContextPath() + "/associator/reserve/room/pre?rid=" + room.getId() %>" class="btn btn-primary pull-right">预订房间</a>
				                    </td>
				                </tr>
			                    <%} %>
			                </tbody>
			            </table>
			        </div>
		    	</div>
				<%} %>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>
