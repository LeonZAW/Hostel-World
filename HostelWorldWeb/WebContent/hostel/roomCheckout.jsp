<%@page import="java.text.NumberFormat"%>
<%@page import="factory.EJBFactory"%>
<%@page import="service.CardStateService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="room" type="model.Room" scope="session"></jsp:useBean>
<jsp:useBean id="income" type="model.Income" scope="session"></jsp:useBean>
<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="酒店主页-出店登记"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_2.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">出店登记</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li><a href="<%=request.getContextPath() + "/hostel/room/checkout/list"%>">出店登记列表</a></li>
			<li><span class="divider">/</span></li>
            <li>出店登记</li>
		</ul>
		<%NumberFormat nf = NumberFormat.getInstance();%>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="block">
					<p class="block-heading">欢迎使用本系统</p>
					<div class="block-body">
						<h2>审批通过后即可使用本系统</h2>
					</div>
				</div>

				<div class="well">
					<form method="post">
						<p>房间号：<%=room.getRoomNo() %>房间</p>
						<p>房间价格：<span id="price"><%=nf.format(income.getFormer_money()) %></span>元</p>
						<% if(income.getAssociator_login()!=null){ %>
							<input id="alogin" class="input-xlarge" type="text" value="<%=income.getAssociator_login()%>" name="alogin" disabled/>
							<input id="password" class="input-xlarge" type="password" value="" name="password"/>
							<button class="test btn btn-primary" type="button" onclick="test_authentation()">验证用户</button>
							<div id="card_pay" style="display:none;">
								
								<input name="accp" id="accp" class="input-xlarge" min="0" max="<%=session.getAttribute("point")%>" type="number" onchange="changeMoney()" checked value="0"/>
										使用积分（现有<%=session.getAttribute("point")%>积分）
								<label><input name="cash_radio" class="input-xlarge" type="radio" onclick="chmod(true,$(this))" checked/>使用现金</label>
								<label><input name="cash_radio" class="input-xlarge" type="radio" onclick="chmod(false,$(this))"/>不使用现金</label>
								<div id="cash">
									<button class="test btn btn-primary" type="button" onclick="cash_income()">支付</button>
								</div>
								<div id="card" style="display:none;">
									<button class="test btn btn-primary" type="button" onclick="card_income()">支付</button>
								</div>
							</div>
						<%}%>
						<button id="normal_man_pay" class="test btn btn-primary" type="button" onclick="normal_cash_income()">现金支付</button>
					</form>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>

		<script type="text/javascript">
		function changeMoney(){
			var point = $("#accp").val();
			if(isNaN(point))
				point=0;
			if(point<0)
				point=0;
			if(point><%=session.getAttribute("point")%>)
				point=<%=session.getAttribute("point")%>;
			$("#accp").val(point);
			var money = <%=income.getMoney()%> - 0.1*point;
			$("#price").text(money);
		}
		function normal_cash_income(){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/hostel/income/cash"%>",
				data : {
					rid:<%=room.getId()%>,
					fmoney:<%=income.getFormer_money()%>,
					money:$("#price").text(),
					reserve_money:<%=income.getReserveMoney()%>,
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					alert("成功付费");
					location.href = "<%=request.getContextPath()+"/hostel/room/checkout/list"%>"
				}
			});
		}
		function cash_income(){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/hostel/income/cash"%>",
				data : {
					rid:<%=room.getId()%>,
					alogin:"<%=income.getAssociator_login()%>",
					fmoney:<%=income.getFormer_money()%>,
					money:$("#price").text(),
					reserve_money:<%=income.getReserveMoney()%>
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					alert("成功付费");
					location.href = "<%=request.getContextPath()+"/hostel/room/checkout/list"%>"
				}
			});
		}

		function card_income(){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/hostel/income/card"%>",
				data : {
					rid:<%=room.getId()%>,
					alogin:"<%=income.getAssociator_login()%>",
					fmoney:<%=income.getFormer_money()%>,
					money:$("#price").text(),
					accpoint:$("#accp").val(),
					reserve_money:<%=income.getReserveMoney()%>
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					if(data=="true"){
						alert("付费成功");
						location.href = "<%=request.getContextPath()+"/hostel/room/checkout/list"%>"
					}else{
						alert("余额不足，请使用现金或充值卡的余额");
					}
				}
			});
		}

		function chmod(isAL,obj){
			if(isAL){
				console.log(isAL);
				$("#card").hide();
				$("#cash").show();
			}
			else{
				console.log(isAL);
				$("#card").show();
				$("#cash").hide();
			}
		}
		function test_authentation(){
			var login = $("#alogin");
			var pw = $("#password");
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/test/authentation"%>",
				data : {
					alogin : login.val(),
					password : pw.val()
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					if(data=="true"){
						alert("验证通过");
						$("#price").text("<%=income.getMoney()%>");
						$("#normal_man_pay").hide();
						$("#card_pay").show();
					}else{
						alert("验证失败");
						pw.val("");
					}
				}
			});
		}
	</script>
</body>
</html>