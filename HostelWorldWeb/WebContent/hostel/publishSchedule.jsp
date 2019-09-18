<%@page import="java.util.List"%>
<%@page import="model.Room"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="酒店主页-发布"/>
</jsp:include>
<%@ include file="../common/including_datetime.jsp"%>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_2.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">发布</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>发布</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
			<%List<Room> rooms = (List<Room>)session.getAttribute("rooms");
                if(rooms.isEmpty()){%>
	            <div class="block">
					<p class="block-heading">房间信息</p>
					<div class="block-body">
						<h2>无可发布计划的房间</h2>
						<p>
							<a class="btn btn-primary btn-large" href="<%=request.getContextPath() + "/user"%>">返回</a>
						</p>
					</div>
				</div>
	            <%}else{%>
				<div class="block">
					<p class="block-heading">发布计划</p>
					<div class="block-body">
						<h2>发布房间在下一段时间内的计划</h2>
					</div>
				</div>

				<div class="well">
					<form id="tab2" method="post" action="<%=request.getContextPath() + "/hostel/publish"%>">
						<label>房间号</label> 
						<select name="no" class="input-xlarge"> 
						<%for (Room room: rooms){ %>
							<option value="<%=room.getRoomNo()%>"><%=room.getRoomNo()%></option> 
						<%} %>
						</select>
						<label>房间价格</label> 
						<input type="number" name="price" class="input-xlarge"/>元
						
			            <div class="control-group">
			                <label class="control-label">起始时间</label>
			                <div class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd">
			                    <input id="begin" name="begin" size="16" type="text" value="">
			                    <span class="add-on"><i class="icon-remove"></i></span>
								<span class="add-on"><i class="icon-th"></i></span>
			                </div>
							<input type="hidden" id="dtp_input2" value="" /><br/>
			            </div>
			            
						<!-- <label>起始时间</label> 
						<input type="datetime" name="begin" class="input-xlarge"/>
						<label>结束时间</label> 
						<input type="datetime" name="end" class="input-xlarge"/> -->
						<div class="control-group">
			                <label class="control-label">结束时间</label>
			                <div class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd">
			                    <input id="end" name="end" size="16" type="text" value="">
			                    <span class="add-on"><i class="icon-remove"></i></span>
								<span class="add-on"><i class="icon-th"></i></span>
			                </div>
							<input type="hidden" id="dtp_input2" value="" /><br/>
			            </div>
						<div>
							<input type='submit' name='Submit' class="btn btn-primary" value='发布'/>
						</div>
					</form>
				</div>
				<%} %>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	<%@ include file="../common/footer_datetime.jsp"%>
</body>
</html>