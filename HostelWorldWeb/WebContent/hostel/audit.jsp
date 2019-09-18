<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="酒店主页-审批"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_0.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">审批</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>审批</li>
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
				
					<form id="tab2" method="post" action="<%=request.getContextPath() + "/audit/hostel"%>">
						<label>酒店名称</label> 
						<input type='text' name='name' value='' class="input-xlarge"> 
						<label>酒店星级</label> 
						<input id="range" type='range' name='star' min="1" max="5" value='' class="input-xlarge" oninput="rangechange()"><span id="rangeNum">3</span>星级
						<label>酒店地址</label> 
						<textarea name="address" class="input-xlarge"></textarea>
						<label>酒店简介</label> 
						<textarea name="synopsis" class="input-xlarge"></textarea>
						<label>酒店注册银行账户</label> 
						<input type='text' name='credit' value='' class="input-xlarge">
						<label>银行账户密码</label> 
						<input type='password' name='password' value='' class="input-xlarge">
						<div>
							<input type='submit' name='Submit' class="btn btn-primary" value='提交审核'>
						</div>
					</form>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	<script type="text/javascript">
		function rangechange(){ 
	      var num=$("#range"); 
	      var location=$("#rangeNum"); 
	      location.text(num.val()); 
 		}
	</script>
</body>
</html>