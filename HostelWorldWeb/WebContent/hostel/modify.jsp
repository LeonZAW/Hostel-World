<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="酒店主页-修改信息"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_2.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">修改信息</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>修改信息</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="block">
					<p class="block-heading">欢迎使用本系统</p>
					<div class="block-body">
						<h2>审批通过后即可使用本系统</h2>
					</div>
				</div>

				<div class="well">
					<button class="edit btn btn-primary pull-right" type="button" onclick="edit()">编辑</button>
					<form id="form" method="post" action="<%=request.getContextPath() + "/modify/hostel"%>">
						<label>酒店名称</label> 
						<p class="shw"><%=hostel.getName() %></p>
						<input class="hidn input-xlarge" type="text" id='name' name='name' value='<%=hostel.getName() %>' style="display:none;"/>
						<label>酒店星级</label> 
						<p class="shw"><%=hostel.getStar() %></p>
						<input type="range" class="hidn input-xlarge" id="star"name="star" min="1" max="5" value="" style="display:none;" oninput="rangechange()"/><span id="rangeNum">3</span>星级
						<label>酒店地址</label> 
						<p class="shw"><%=hostel.getAddress() %></p>
						<textarea class="hidn input-xlarge" id="address"name="address" style="display:none;"><%=hostel.getAddress() %></textarea>
						<label>酒店简介</label> 
						<p class="shw"><%=hostel.getSynopsis() %></p>
						<textarea class="hidn input-xlarge" id="synopsis"name="synopsis" style="display:none;"><%=hostel.getSynopsis() %></textarea>
						
						<p>
							原酒店注册银行账户：<%=hostel.getCreditId() %>
						</p>
						<label>原银行账户密码</label> 
						<p id="oldCredit" class="hidn input-xlarge" style="display:none;">
							<input id="oldPassword" class="input-xlarge" type='password' name='password' value=''>
						</p>
						<div class="hidn" style="display:none;">
							<label>新酒店注册银行账户</label> 
							<input id="newCredit" class="input-xlarge" type='text' name='credit' value=''>
							<label>新银行账户密码</label> 
							<input id="newPassword" class="input-xlarge" type='password' name='password' value=''>
						</div>
						<div class="hidn" style="display:none;">
							<button class="delete btn btn-primary" type="button" onclick="test_credit()">提交修改</button>
							<button class="btn btn-primary" type="button" onclick="unedit()">取消编辑</button>
						</div>
					</form>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	<script type="text/javascript">
		function edit(){
			$(".hidn").show();
			$(".shw").hide();
		}
		function unedit(){
			$(".shw").show();
			$(".hidn").hide();
		}
		function rangechange(){ 
	      var num=$("#star"); 
	      var location=$("#rangeNum"); 
	      location.text(num.val()); 
 		}
		function test_credit(){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/modify/hostel"%>",
				data : $("#form").serialize()+"&oldCredit="+"<%=hostel.getCreditId() %>"+"&oldPassword="+$("#oldPassword").val(),
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					if(data=="true"){
						location.href = "<%=request.getContextPath()+"/hostel/modifySave.jsp"%>"
					}
					else{
						alert(data)
						$("#oldPassword").val("");
						$("#newCredit").val("");
						$("#newPassword").val("");
					}
				}
			});
		}
		
	</script>
</body>
</html>

