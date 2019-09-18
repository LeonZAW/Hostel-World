<%@ page language="java" pageEncoding="UTF-8"%>
<div class="sidebar-nav">
	<a href="<%=request.getContextPath() + "/hostel/modify.jsp"%>" class="nav-header"> 
		<i class="icon-briefcase"></i>修改信息 <!-- <span class="label label-info">+3</span> -->
	</a> 
	<a href="<%=request.getContextPath() + "/hostel/room/checkin/list"%>" class="nav-header"> 
		<i class="icon-briefcase"></i>入店登记
	</a> 
	<a href="<%=request.getContextPath() + "/hostel/room/checkout/list"%>" class="nav-header"> 
		<i class="icon-briefcase"></i>出店登记
	</a> 
	<a href="<%=request.getContextPath() + "/hostel/roomManage.jsp"%>" class="nav-header"> 
		<i class="icon-briefcase"></i>添加房间 <!-- <span class="label label-info">+3</span> -->
	</a> 
	<a href="<%=request.getContextPath() + "/hostel/publishSchedule"%>" class="nav-header"> 
		<i class="icon-briefcase"></i>发布计划
	</a> 
	<a href="#error-menu" class="nav-header collapsed" data-toggle="collapse">
		<i class="icon-exclamation-sign"></i>收入记录
		<i class="icon-chevron-up"></i>
	</a>
	<ul id="error-menu" class="nav nav-list collapse">
		<li><a href="<%=request.getContextPath() + "/hostel/reserve/history"%>">预定记录</a></li>
		<li><a href="<%=request.getContextPath() + "/hostel/pay/list"%>">退房记录</a></li>
	</ul>
</div> 