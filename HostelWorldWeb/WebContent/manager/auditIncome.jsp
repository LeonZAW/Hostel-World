<%@page import="model.Hostel"%>
<%@page import="java.util.List"%>
<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage"
	scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="管理员主页-酒店收入审批"/>
</jsp:include>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="管理员"/>
	</jsp:include>
	<%@ include file="../common/sidebar_manager.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">酒店收入审批</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>审批</li>
			<li><span class="divider">/</span></li>
            <li>酒店收入审批</li>
		</ul>
		<%NumberFormat nf = NumberFormat.getInstance();%>

		<div class="container-fluid">
			<div class="row-fluid">
			<%List<Hostel> hostels = (List<Hostel>)session.getAttribute("hostels");
                if(hostels.isEmpty()){%>
	            <div class="block">
					<p class="block-heading">审批信息</p>
					<div class="block-body">
						<h2>无需要支付的旅店</h2>
						<p>
							<a class="btn btn-primary btn-large" href="<%=request.getContextPath() + "/user"%>">返回</a>
						</p>
					</div>
				</div>
	            <%}else{%>
		<div class="block">
        <div class="block-heading">
            <a>酒店收入审批</a>
        </div>
        <div id="widget2container" class="block-body">
            <table class="table list">
              <tbody>
                    <%for (Hostel hostel: hostels){ %>
		                <tr> 
		                    <td>
		                        <p>酒店名称：<%=hostel.getName()%></p>
		                        <p>酒店星级：<%=hostel.getStar()%>星级</p>
		                        <p>酒店目前收入：<%=nf.format(hostel.getTotalIncome())%></p>
		                        <p>酒店未付收入：<%=nf.format(hostel.getNotPayIncome())%></p>
		                        <input type="button" value="支付未付款项" class="btn btn-danger pull-right" onclick='addAction("<%=hostel.getLogin()%>",<%=hostel.getNotPayIncome()%>)'/>
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
		function check_pay(hlogin,money){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/manager/audit/incomes/pay"%>",
				data : {
					money : money*0.95,
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					if(data=="true"){
						addAction(hlogin);
					}else{
						alert('系统余额不足！');
					}
				}
			});
		}
		function addAction(hlogin) {
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/manager/audit/incomes"%>",
				data : {
					hlogin:hlogin
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					alert('支付成功');
					location.href = "<%=request.getContextPath()+"/manager/audit/income"%>"
				}
			});
		}
	</script>
</body>
</html>