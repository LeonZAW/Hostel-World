<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="酒店主页-添加房间"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_2.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">添加房间</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>添加房间</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="block">
					<p class="block-heading">添加房间</p>
					<div class="block-body">
						<h2>按照旅店配置添加房间</h2>
					</div>
				</div>

				<div class="well">
				
					<form method="post" action="<%= request.getContextPath()+"/hostel/room/add" %>">
						<label>房间号</label>
						<input type="text" name='no' value=''  class="input-xlarge"/>
						<label>房间人数</label>
						<input type="number" name='people' value='1' class="input-xlarge"/>
						<label>房间简介</label>
						<textarea name="description" class="input-xlarge"></textarea>
						<div>
							<input type='submit' name='Submit' value='发布' class="btn btn-primary"/>
						</div>
					</form>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
</body>
</html>