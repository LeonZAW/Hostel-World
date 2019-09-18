<%@page import="model.Audit"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage"
	scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="管理员主页-审批酒店信息修改"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="管理员"/>
	</jsp:include>
	<%@ include file="../common/sidebar_manager.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">审批酒店信息修改</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>审批</li>
			<li><span class="divider">/</span></li>
            <li>酒店信息修改审批</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
			<%List<Audit> audits = (List<Audit>) session.getAttribute("audits");
                if(audits.isEmpty()){%>
	            <div class="block">
					<p class="block-heading">审批信息</p>
					<div class="block-body">
						<h2>无需要审批的酒店信息</h2>
						<p>
							<a class="btn btn-primary btn-large" href="<%=request.getContextPath() + "/user"%>">返回</a>
						</p>
					</div>
				</div>
	            <%}else{%>
		<div class="block">
        <div class="block-heading">
            <a>审批酒店信息修改</a>
        </div>
        <div id="widget2container" class="block-body">
            <table class="table list">
              <tbody>
                    <% for (Audit audit : audits) {%>
	                <tr>
	                    <td>
	                    	<h4>酒店用户名：<%=audit.getLogin()%></h4>
	                    	<hr/>
	                    	<p>酒店原名称：<%=audit.getHostel().getName()%></p>
	                        <p>酒店原星级：<%=audit.getHostel().getStar()%>星级</p>
	                        <p>酒店原地址</p>
	                        <p><%=audit.getHostel().getAddress()%></p>
	                        <p>酒店原简介</p>
	                        <p><%=audit.getHostel().getSynopsis()%></p>
	                    	<hr/>
	                    	<p>酒店名称：<%=audit.getName()%></p>
	                        <p>酒店星级：<%=audit.getStar()%>星级</p>
	                        <p>酒店地址</p>
	                        <p><%=audit.getAddress()%></p>
	                        <p>酒店简介</p>
	                        <p><%=audit.getSynopsis()%></p>
	                    	<form method="post">
								<input type="hidden" name="id" value="<%=audit.getId()%>">
								<input type="button" value="拒绝" class="btn btn-danger pull-right" onclick='addAction(<%=audit.getId()%>,"repudiate")'/>
								<input type="button" value="通过" class="btn btn-primary pull-right" onclick='addAction(<%=audit.getId()%>,"pass")'/>
							</form>
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

	<script type="text/javascript">
		
		function addAction(id,type) {
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/audit/"%>"+type,
				data : {
					id : id
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					alert('成功');
					location.href = "<%=request.getContextPath()+"/manager/audit/modify"%>"
				}
			});
		}
		function auditAction(type) {
			//瓜皮方法 没法用 瓜皮
			$("#audit").action = "<%=request.getContextPath()+"/login"%>";
			$("#audit").submit();
		}
	</script>
</body>
</html>