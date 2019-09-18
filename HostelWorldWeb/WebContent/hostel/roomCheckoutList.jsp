<%@page import="java.util.List"%>
<%@page import="model.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="用户主页-出店登记列表"/>
</jsp:include>
</head>


<body class="">

	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_2.jsp"%>
	<div class="content">
		<div class="header">
			<h1 class="page-title">出店登记列表</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>出店登记列表</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
			<%List<Room> rooms = (List<Room>)session.getAttribute("rooms");
                if(rooms.isEmpty()){%>
	            <div class="block">
					<p class="block-heading">房间信息</p>
					<div class="block-body">
						<h2>无房间可办理退房</h2>
						<p>
							<a class="btn btn-primary btn-large" href="<%=request.getContextPath() + "/user"%>">返回</a>
						</p>
					</div>
				</div>
	            <%}else{%>
		<div class="block">
        <div class="block-heading">


            <a>出店登记列表</a>
        </div>
        <div id="widget2container" class="block-body">
            <table class="table list">
            	<tbody>
	                <%for (Room room: rooms){ %>
	                <tr>
	                    <td>
							<div>
								<p>房间号：<%=room.getRoomNo()%></p>
								<p>限住人数：<%=room.getLimitNo()%></p>
								<p>简介：<%=room.getDescription()%></p>
								<a href="<%=request.getContextPath() + "/hostel/room/checkout/pre?rid=" + room.getId() %>" class="btn btn-primary pull-right">付费退房</a>
							</div>
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
