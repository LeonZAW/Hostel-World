<%@page import="java.util.List"%>
<%@page import="model.Reserved"%>
<%@page import="model.enumerate.ReserveState"%>
<%@page import="model.enumerate.IncomeState"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="用户主页-预定查看"/>
</jsp:include>
</head>


<body class="">

	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_2.jsp"%>
	<div class="content">
		<div class="header">
			<h1 class="page-title">预定查看</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>预定查看</li>
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


            <a>预定列表</a>
        </div>
        <div id="widget2container" class="block-body">
            <table class="table list">
              <tbody>
					<tr>
	                      <td>
	                          <p>预定房间</p>
	                      </td>
	                      <td>
	                          <p>预定账号</p>
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

	                  </tr>
	                  <%for (Reserved reserved: (List<Reserved>)session.getAttribute("reserves")){ %>
	                  <tr>
	                      <td>
	                          <p><%=reserved.getRoom().getRoomNo()%></p>
	                      </td>
	                      <td>
	                          <p><%=reserved.getAssociator_login()%></p>
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
