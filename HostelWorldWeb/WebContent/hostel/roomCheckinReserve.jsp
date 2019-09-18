<%@ page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="room" type="model.Room" scope="session"></jsp:useBean>
<jsp:useBean id="reserved" type="model.Reserved" scope="session"></jsp:useBean>
<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="酒店主页-预定入店登记"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_2.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">预定入店登记</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
			<li><a href="<%=request.getContextPath() + "/hostel/room/checkin/list"%>">入店登记列表</a></li>
			<li><span class="divider">/</span></li>
            <li>预定入店登记</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="well">
					<label>房间号</label>
					<p><%=room.getRoomNo() %>房间</p>
					<label>房间描述</label>
					<p><%=room.getDescription() %></p>
					<label>限住人数</label>
					<p><%=room.getLimitNo() %></p>
					<div id="rentorList">
						<form id="rentor1" method="post" action="<%= request.getContextPath()+"/hostel/room/checkin" %>">
							<button class="delete btn btn-primary" type="button" onclick="delete_rentor()" style="display:none;">删除</button>
							<input type="hidden" name="rid" value="<%=room.getId()%>" class="input-xlarge">
							<label><input name="alogin" type="radio" class="input-xlarge" onclick="chmod(true,$(this))" checked disabled/>使用Hostel账户</label>
							<label><input name="alogin" type="radio" class="input-xlarge" onclick="chmod(false,$(this))" disabled/>不使用Hostel账户</label>
							<div class="haslogin">
								<label>用户名</label>
								<input name="associator_login" class="input-xlarge" type="text" disabled value="<%=reserved.getAssociator_login()%>"/>
								<label>密码</label>
								<input name="password" class="input-xlarge" type="password">
								<button class="test btn btn-primary" type="button" onclick="test_authentation($(this))">验证用户</button>
							</div>
							<div class="nologin" style="display:none;">
								<label>姓名</label>
								<input name="name" class="input-xlarge" type="text"/>
								<label>身份证</label>
								<input name="pin" class="input-xlarge" type="text"/>
							</div>
							<button class="sub btn btn-primary" type="button" onclick="add_rentor()" style="display:none;">添加</button>
						</form>
					</div>
					<%SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");%>
					<label>起始时间</label>
					<input id="begin" class="input-xlarge" type="datetime" name="begin" disabled value="<%=format.format(reserved.getStarttime())%>"/>
					<label>结束时间</label>
					<input id="end" class="input-xlarge" type="datetime" name="end" disabled value="<%=format.format(reserved.getEndtime())%>"/>
					<div>
						<button id="sub_ci" class="sub btn btn-primary" type="button" onclick="rentors_checkin()" style="display:none;">提交</button>
					</div>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>

	<script type="text/javascript">
		var count = 1;
		var limitNo = <%=room.getLimitNo() %>;
		var result;
		$(document).ready(function(){  
		     test_limit();
		});
		function test_authentation(obj){
			var hasLoginDiv = obj.parent();
			var alogin = hasLoginDiv.children(":input[name=associator_login]");
			var password = hasLoginDiv.children(":input[name=password]");
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/test/authentation"%>",
				data : {
					alogin:alogin.val(),
					password:password.val()
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					if(data=="true"){
						alert("验证通过");
						$("#sub_ci").show();
						if(count<limitNo){
							hasLoginDiv.parent().children(".add").show();
						}
					}else{
						alert("验证失败");
						password.val("");
					}
				}
			});
		}
		function chmod(isAL,obj){
			if(isAL){
				rentorForm = obj.parent().parent();
				rentorForm.children(".nologin").hide();
				rentorForm.children(".haslogin").show();
			}
			else{
				rentorForm = obj.parent().parent();
				rentorForm.children(".nologin").show();
				rentorForm.children(".haslogin").hide();
			}
		}
		function test_limit(){
			if(count==limitNo)
				$("#rentor"+count).children(".add").hide();
		}
		/* function test_not_limit(){
			if(count<limitNo)
				$("#rentor"+count).children("#add").show();
		} */
		function delete_rentor() {
			lastForm = $("#rentor" + count).remove();
			count--;
			beforeLastForm = $("#rentor" + count);
			beforeLastForm.children(".delete").show();
			beforeLastForm.children(".add").show();
			if(count==1)
				beforeLastForm.children(".delete").hide();
		}
		function add_rentor() {
			oldForm = $("#rentor" + count);
			newForm = oldForm.clone();
			++count;
			newForm.attr("id","rentor" + count);
			newForm.children(".haslogin").children(":input").val("");
			newForm.children(".nologin").children(":input").val("");
			newForm.children(".delete").show();
			newForm.children("label").children(":input[type=radio]").removeAttr("disabled");
			newForm.children(".haslogin").children(":input").removeAttr("disabled");
			oldForm.children(".delete").hide();
			oldForm.children(".add").hide();
			oldForm.append(newForm);
			test_limit();
		}
		function rentor_checkin(i) {
			if(i==1){
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()+"/hostel/room/checkin/reserve"%>",
					data : {reserveid:<%=reserved.getId()%>},
					statusCode : {
						404 : function() {
							alert('page not found');
						}
					},
					success : function(data, textStatus) {
					}
				});
			}else{
				$.ajax({
					type : "POST",
					url : "<%=request.getContextPath()+"/hostel/room/checkin"%>",
					data : $("#rentor"+i).serialize()+"&begin="+$("#begin").val()+"&end="+$("#end").val(),
					statusCode : {
						404 : function() {
							alert('page not found');
						}
					},
					success : function(data, textStatus) {
					}
				});
			}
		}
		function rentors_checkin() {
			for(var i=1;i<=count;++i){
				rentor_checkin(i);
			}
			alert("入住办理成功");
			location.href = "<%=request.getContextPath()+"/hostel/room/checkin/list"%>";
		}
	</script>
</body>
</html>