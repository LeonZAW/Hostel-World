<%@page import="java.text.NumberFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="login_message" type="model.message.LoginMessage" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">
  <head>
<jsp:include page="../common/including.jsp">
    <jsp:param name="title" value="管理员主页-欢迎"/>
</jsp:include>

  </head>


  <body class=""> 
  <!--<![endif]-->
    
    <jsp:include page="../common/header.jsp">
        <jsp:param name="name" value="管理员"/>
    </jsp:include>

    <%@ include file="../common/sidebar_manager.jsp"%>
    
    <div class="content">
        
      <div class="header">
        <div class="stats">
          <p class="stat"><span class="number"><%=session.getAttribute("new_hostel_open_audit")%></span>新增客房申请</p>
          <p class="stat"><span class="number"><%=session.getAttribute("new_hostel_modify_audit")%></span>旅店信息修改申请</p>
          <p class="stat"><span class="number"><%=session.getAttribute("new_hostel_income_audit")%></span>笔待支付旅店收入</p>
        </div>

        <h1 class="page-title">主页</h1>
      </div>
        
      <ul class="breadcrumb">
        <li>主页</li>
      </ul>

        <div class="container-fluid">
            <div class="row-fluid">
                    

<div class="row-fluid">

    <div class="alert alert-info">
        <button type="button" class="close" data-dismiss="alert">×</button>
        <strong><%=login_message.message %>：</strong>欢迎来到管理员界面
    </div>
    <%NumberFormat nf = NumberFormat.getInstance();%>

    <div class="block">
        <a href="#page-stats" class="block-heading" data-toggle="collapse">数据统计</a>
        <div id="page-stats" class="block-body collapse in">

            <div class="stat-widget-container">
                <div class="stat-widget">
                    <div class="stat-button">
                        <p class="title"><%=session.getAttribute("user_num")%></p>
                        <p class="detail">用户</p>
                    </div>
                </div>

                <div class="stat-widget">
                    <div class="stat-button">
                        <p class="title"><%=session.getAttribute("hostel_num")%></p>
                        <p class="detail">旅店</p>
                    </div>
                </div>

                <div class="stat-widget">
                    <div class="stat-button">
                        <p class="title">￥<%=nf.format(session.getAttribute("world_in_num"))%></p>
                        <p class="detail">系统收入</p>
                    </div>
                </div>

                <div class="stat-widget">
                    <div class="stat-button">
                        <p class="title">￥<%=nf.format(session.getAttribute("world_out_num"))%></p>
                        <p class="detail">系统支出</p>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>


                   <%@ include file="../common/footer.jsp"%>
                    
            </div>
        </div>
    </div>
  </body>
</html>
