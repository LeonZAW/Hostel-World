<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>

<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="注册"/>
</jsp:include>
  </head>

  <body class=""> 
    
    <%@ include file="header_no.jsp" %>
    <div class="row-fluid">
	    <div class="dialog">
	        <div class="block">
	            <p class="block-heading">注册</p>
	            <div class="block-body">
	                <form id="form" method="post" action="<%= request.getContextPath()+"/register" %>">
	                    <label>用户类型</label>
	                    <select name="type" class="input-xlarge">
	                    	<option value="0">房客</option> 
							<option value="1">旅店</option> 
          				</select>
          				<label>密码</label>
	                    <input type='password' name='password' id='password' value='' class="span12">
	                    <label>密码确认</label>
	                    <input type='password' name='password2' id='password2' value='' class="span12">
	                    <a href="<%= request.getContextPath()+"/login" %>">已有账号，点击登录</a>
	                    <button class="btn btn-primary pull-right" type="button" onclick="regi()">注册!</button>
	                    <!-- <input type='button' class="btn btn-primary pull-right" name='Submit' value='注册!'/> -->
	                    <div class="clearfix"></div>
	                </form>
	            </div>
	        </div>
	    </div>
	</div>
	<script type="text/javascript">
		function regi(){
			if($("#password").val()!=$("#password2").val()){
				alert("密码和确认密码不同，请重新输入！");
				return;
			}else{
				$("#form").submit();
			}
		}
	</script>
  </body>
</html>