<%@page import="java.util.List"%>
<%@page import="model.Reserved"%>
<%@page import="model.enumerate.ReserveState"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage"
	scope="session"></jsp:useBean>
<jsp:useBean id="associator" type="model.Card" scope="session"></jsp:useBean>
<!DOCTYPE html> 
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="用户主页-预定记录"/>
</jsp:include>

</head>


<body class="">

	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=associator.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_associator_1.jsp"%>


	<div class="content">
		<div class="header">
			<h1 class="page-title">预定记录</h1>
		</div>

		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>预定记录</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">
			<%List<Reserved> reserves = (List<Reserved>)session.getAttribute("reserves");
                if(reserves.isEmpty()){%>
	            <div class="block">
					<p class="block-heading">预定信息</p>
					<div class="block-body">
						<h2>无房间预定记录</h2>
						<p>
							<a class="btn btn-primary btn-large" href="<%=request.getContextPath() + "/user"%>">返回</a>
						</p>
					</div>
				</div>
	            <%}else{%>
		<div class="block">
        <div class="block-heading">


            <a>预定记录</a>
        </div>
        <div id="widget2container" class="block-body">
            <table class="table list">
              <tbody>
					<tr>
	                      <td>
	                          <p><!-- <i class="icon-user"></i> -->预定房间</p>
	                      </td>
	                      <td>
	                          <p>所属酒店</p>
	                      </td>
	                      <td>
	                          <p>预定状态</p>
	                      </td>
	                      <td>
	                          <p>预定开始时间</p>
	                      </td>
	                      <td>
	                          <p>预定结束时间</p>
	                      </td>
	                      <td>
					      </td>
	                  </tr>
	                  <%for (Reserved reserved: reserves){ %>
	                  <tr>
	                      <td>
	                          <p><%=reserved.getRoom().getRoomNo()%></p>
	                      </td>
	                      <td>
	                          <p><%=reserved.getRoom().getHostel().getName()%></p>
	                      </td>
	                      <td>
	                          <p><%=ReserveState.getString(ReserveState.values()[reserved.getReserve_state()])%></p>
	                          
	                      </td>
	                      <td>
	                          <p><%=reserved.getStarttime()%></p>
	                      </td>
	                      <td>
	                          <p><%=reserved.getEndtime()%></p>
	                      </td>
	                      <td>
	                      		<%if(reserved.getReserve_state()==ReserveState.RESERVED.ordinal()){ %>
									<a href="<%=request.getContextPath() + "/cancel/reserve?reserveid=" + reserved.getId() %>" class="btn btn-default pull-right">取消预定</a>
								<%} %>
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
</body>
</html>