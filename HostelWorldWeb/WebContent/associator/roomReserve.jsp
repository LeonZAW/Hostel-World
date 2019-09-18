<%@page import="java.math.BigDecimal"%>
<%@page import="model.Card"%>
<%@page import="java.text.NumberFormat"%>
<%@ page import="model.enumerate.UserType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage" scope="session"></jsp:useBean>
<%BigDecimal money = (BigDecimal)session.getAttribute("money"); %>
<jsp:useBean id="room" type="model.Room" scope="session"></jsp:useBean>
<jsp:useBean id="associator" type="model.Card" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
	<jsp:param name="title" value="用户主页-房间预订"/>
</jsp:include>
<%@ include file="../common/including_datetime.jsp"%>
</head>


<body class="">
	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=associator.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_associator_1.jsp"%>

	<div class="content">
		<div class="header">
			<h1 class="page-title">房间预订</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>房间预订</li>
		</ul>
		<%NumberFormat nf = NumberFormat.getInstance();%>
		<div class="container-fluid">
			<div class="row-fluid">

				<%-- <div class="block">
					<p class="block-heading">欢迎使用本系统</p>
					<div class="block-body">
						<h2>充值1000以上来完成激活</h2>
						<p>
							会员卡余额：<%=associator.getMoney()%></p>
					</div>
				</div> --%>

				<div class="well">
					<form id="tab2" method="post"
						action="<%=request.getContextPath() + "/user/recharge"%>">
						<label>房间号</label> 
						<p><%=room.getRoomNo() %>房间</p>
						<label>房间价格</label> 
						<p><%=nf.format(money) %>元</p>
						<label>预订价格</label> 
						<p><span id="price"><%=money.multiply(new BigDecimal("0.1")) %></span>元/天</p>
						<!-- <label>起始时间</label>  -->
						<div class="control-group">
			                <label class="control-label">起始时间</label>
			                <div class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd">
			                    <input id="begin" name="begin" size="16" type="text" value="">
			                    <span class="add-on"><i class="icon-remove"></i></span>
								<span class="add-on"><i class="icon-th"></i></span>
			                </div>
							<input type="hidden" id="dtp_input2" value="" /><br/>
			            </div>
						<!-- <div class="span6">
							<label>起始时间</label>
									                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
									                    <input id="begin" name="begin" class="form-control" style="font-size: 18px;" type="text" value="" readonly>
									                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									                </div>
									            </div>
									            <div class="clearfix"></div> -->
						<!-- <input id="begin" name="begin"/> -->
						<!-- <label>结束时间</label>  -->
						<div class="control-group">
			                <label class="control-label">结束时间</label>
			                <div class="controls input-append date form_date" data-date="" data-date-format="yyyy-mm-dd">
			                    <input id="end" name="end" size="16" type="text" value="">
			                    <span class="add-on"><i class="icon-remove"></i></span>
								<span class="add-on"><i class="icon-th"></i></span>
			                </div>
							<input type="hidden" id="dtp_input2" value="" /><br/>
			            </div>
						<!-- <div class="span6">
							<label>结束时间</label>
									                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd">
									                    <input id="end" name="end" class="form-control" size="16" type="text" value="" readonly>
									                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
									                </div>
									            </div>
									            <div class="clearfix"></div> -->
						<!-- <input id="end" name="end"/> -->
						
						<div>
							<button id="normal_man_pay" class="test btn btn-primary" type="button" onclick="check_pay()">支付</button>
						</div>
					</form>
				</div>
			</div>
			<%@ include file="../common/footer.jsp"%>
		</div>
	</div>
	<%@ include file="../common/footer_datetime.jsp"%>
	<script type="text/javascript">

		function check_pay(){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/check/reserve/date"%>",
				data : {
					rid : <%=room.getId()%>,
					begin : $("#begin").val(),
					end :$("#end").val()
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					if(data=="true"){
						pay_reserve();
					}else{
						alert("该房间在"+$("#begin").val()+"到"+$("#end").val()+"时间段内已被占用");
						$("#begin").val("");
						$("#end").val("");
					}
				}
			});
		}
		function pay_reserve(){
			$.ajax({
				type : "POST",
				url : "<%=request.getContextPath()+"/associator/reserve/room"%>",
				data : {
					rid:<%=room.getId()%>,
					begin : $("#begin").val(),
					end :$("#end").val(),
					money :　<%=money.multiply(new BigDecimal("0.1")) %>
				},
				statusCode : {
					404 : function() {
						alert('page not found');
					}
				},
				success : function(data, textStatus) {
					if(data=="true"){
						alert("付费成功");
						location.href = "<%=request.getContextPath()+"/user"%>"
					}else{
						alert("余额不足，请使用现金或充值卡的余额");
					}
				}
			});
		}
		
	</script>
	<!-- <script type="text/javascript">
	$('.form_date').datetimepicker({
	        language:  'fr',
	        weekStart: 1,
	        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	    });
	</script> -->
</body>
</html>