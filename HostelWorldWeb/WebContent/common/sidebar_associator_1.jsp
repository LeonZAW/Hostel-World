<%@ page language="java" pageEncoding="UTF-8"%>
<div class="sidebar-nav">
	<a href="<%=request.getContextPath() + "/associator/recharge.jsp"%>" class="nav-header"> 
		<i class="icon-briefcase"></i>充值 <!-- <span class="label label-info">+3</span> -->
	</a> 
	<a href="<%=request.getContextPath() + "/associator/info/modify/pre"%>" class="nav-header"> 
		<i class="icon-briefcase"></i>修改信息 <!-- <span class="label label-info">+3</span> -->
	</a> 
	<a href="<%=request.getContextPath() + "/associator/reserve/hostel/list"%>" class="nav-header"> 
		<i class="icon-briefcase"></i>预定酒店 <!-- <span class="label label-info">+3</span> -->
	</a> 
	<a href="#error-menu" class="nav-header collapsed" data-toggle="collapse">
		<i class="icon-exclamation-sign"></i>消费记录
		<i class="icon-chevron-up"></i>
	</a>
	<ul id="error-menu" class="nav nav-list collapse">
		<li><a href="<%=request.getContextPath() + "/associator/reserve/list"%>">预定记录</a></li>
		<li><a href="<%=request.getContextPath() + "/associator/pay/list"%>">入住记录</a></li>
	</ul>
	<a href="<%=request.getContextPath() + "/associator/suspend_1.jsp"%>" class="nav-header"> 
		<i class="icon-briefcase"></i>停用 <!-- <span class="label label-info">+3</span> -->
	</a> 
</div>