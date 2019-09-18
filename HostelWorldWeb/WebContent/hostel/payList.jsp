<%@page import="java.util.List"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="model.Income"%>
<%@page import="model.enumerate.IncomeState"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="hostel" type="model.Hostel" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="用户主页-入住消费记录"/>
</jsp:include>
</head>


<body class="">

	<jsp:include page="../common/header.jsp">
		<jsp:param name="name" value="<%=hostel.getLogin()%>"/>
	</jsp:include>
	<%@ include file="../common/sidebar_hostel_2.jsp"%>
	<div class="content">
		<div class="header">
			<h1 class="page-title">入住消费记录</h1>
		</div>
		<ul class="breadcrumb">
			<li><a href="<%=request.getContextPath() + "/user"%>">主页</a></li>
			<li><span class="divider">/</span></li>
            <li>入住消费记录</li>
		</ul>
		<%NumberFormat nf = NumberFormat.getInstance();%>
		<div class="container-fluid">
			<div class="row-fluid">
			<%List<Income> incomes = (List<Income>)session.getAttribute("incomes");
                if(incomes.isEmpty()){%>
	            <div class="block">
					<p class="block-heading">付款信息</p>
					<div class="block-body">
						<h2>无房间付款记录</h2>
						<p>
							<a class="btn btn-primary btn-large" href="<%=request.getContextPath() + "/user"%>">返回</a>
						</p>
					</div>
				</div>
	            <%}else{%>
		<div class="block">
        <div class="block-heading">


            <a>入住房间记录</a>
        </div>
        <div id="widget2container" class="block-body">
            <table class="table list">
              <tbody>
					<tr>
	                      <td>
	                          <p><!-- <i class="icon-user"></i> -->入住房间</p>
	                      </td>
	                      <td>
	                          <p>所属酒店</p>
	                      </td>
	                      <td>
	                          <p>付款状态</p>
	                      </td>
	                      <td>
	                          <p>付款用户</p>
	                      </td>
	                      <td>
	                          <p>付款金额</p>
	                      </td>
	                      <td>
	                          <p>付款时间</p>
	                      </td>

	                  </tr>
	                  <%for (Income income: incomes){ %>
	                  <tr>
	                      <td>
	                          <p><%=income.getRoom().getRoomNo()%></p>
	                      </td>
	                      <td>
	                          <p><%=income.getRoom().getHostel().getName()%></p>
	                      </td>
	                      <td>
	                          <p><%=IncomeState.getString(IncomeState.values()[income.getState()])%></p>
	                      </td>
	                      <td>
	                          <p><%=income.getAssociator_login()==null?"系统外用户":income.getAssociator_login()%></p>
	                      </td>
	                      <td>
	                          <p><%=nf.format(income.getMoney())%></p>
	                      </td>
	                      <td>
	                          <p><%=income.getTime()%></p>
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
