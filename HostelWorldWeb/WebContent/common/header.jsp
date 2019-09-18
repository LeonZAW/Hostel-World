<%@ page language="java" pageEncoding="UTF-8"%>
<div class="navbar">
	<div class="navbar-inner">
		<ul class="nav pull-right">
			<li id="fat-menu" class="dropdown">
				<a href="#" role="button" class="dropdown-toggle" data-toggle="dropdown"> 
					<i class="icon-user"></i><%=request.getParameter("name")%>
					<i class="icon-caret-down"></i>
				</a>

				<ul class="dropdown-menu">
					<li><a tabindex="-1" href="<%=request.getContextPath() + "/user"%>">主页</a></li>
					<li class="divider"></li>
					<li><a tabindex="-1" href="<%=request.getContextPath() + "/login?logout=teemo"%>">退出</a></li>
				</ul>
			</li>

		</ul>
		<a class="brand" href="<%=request.getContextPath() + "/login"%>"><span class="first"></span> <span class="second">Hostel World</span></a>
	</div>
</div>