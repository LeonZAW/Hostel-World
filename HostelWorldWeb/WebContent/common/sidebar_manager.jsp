<%@ page language="java" pageEncoding="UTF-8"%>
<div class="sidebar-nav">
    <a href="<%=request.getContextPath() + "/manager/audit/open" %>" class="nav-header">
      <i class="icon-briefcase"></i>审批开店申请
      <!-- <span class="label label-info">+3</span> -->
    </a>
    <a href="<%=request.getContextPath() + "/manager/audit/modify" %>" class="nav-header">
      <i class="icon-briefcase"></i>审批修改申请
      <!-- <span class="label label-info">+3</span> -->
    </a>
    <a href="<%=request.getContextPath() + "/manager/audit/income" %>" class="nav-header">
      <i class="icon-briefcase"></i>收入审批
      <!-- <span class="label label-info">+3</span> -->
    </a>
    <a href="#legal-menu" class="nav-header" data-toggle="collapse"><i class="icon-legal"></i>统计<i class="icon-chevron-up"></i></a>
    <ul id="legal-menu" class="nav nav-list collapse in">
        <li ><a href="<%=request.getContextPath() + "/manager/stat/associators" %>">用户统计</a></li>
        <li ><a href="<%=request.getContextPath() + "/manager/stat/hostels" %>">客房统计</a></li>
    </ul>
</div>